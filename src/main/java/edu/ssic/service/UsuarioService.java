package edu.ssic.service;

import edu.ssic.entity.Usuario;
import edu.ssic.repository.UsuarioRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.stream.Collectors;

@ApplicationScoped
public class UsuarioService {
    @Inject
    UsuarioRepository usuarioRepository;

    public Usuario retrieve(String ra, String senha) {
        try {
            if (usuarioRepository.find("ra", ra).firstResult().getSenha().equals(senha)) {
                Usuario usuario = usuarioRepository.find("ra", ra).firstResult();
                usuario.setDisciplinaList(
                        usuario.getDisciplinaList().stream().peek(d -> {
                            d.setUsuarioList(null);
                            d.setAvisoList(d.getAvisoList().stream().peek(a -> a.setDisciplina(null)).collect(Collectors.toList()));
                        }).collect(Collectors.toList())
                );
                return usuario;
            } else {
                throw new IllegalArgumentException("Senha incorreta");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Usuario " + ra + " not found");
        }
    }

    public void create(Usuario usuario) {
        if (usuarioRepository.findAll().stream().anyMatch(d -> d.getRa().equals(usuario.getRa()))) {
            throw new IllegalArgumentException("Usuario " + usuario.getRa() + " already exists");
        }
        if (usuarioRepository.findAll().stream().anyMatch(d -> d.getEmail().equals(usuario.getEmail()))) {
            throw new IllegalArgumentException("Usuario " + usuario.getEmail() + " already exists");
        }
        try {
            usuarioRepository.persist(usuario);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Usuario usuario) {
        if (usuario.getId() == null) {
            throw new IllegalArgumentException("Usuario id cannot be null");
        }
        Usuario usuarioToUpdate = retrieve(usuario.getRa(), usuario.getSenha());
        if (usuarioToUpdate == null) {
            throw new IllegalArgumentException("Usuario " + usuario.getRa() + " not found");
        }
        usuarioToUpdate.setNome(usuario.getNome());
        usuarioToUpdate.setRa(usuario.getRa());
        usuarioToUpdate.setEmail(usuario.getEmail());
        usuarioToUpdate.setSenha(usuario.getSenha());
        usuarioToUpdate.setDisciplinaList(usuario.getDisciplinaList());

        delete(usuario.getEmail(), usuario.getSenha());
        usuarioRepository.persist(usuarioToUpdate);
    }

    public void delete(String email, String senha) {
        Usuario usuario = retrieve(email, senha);
        if (usuario != null) {
            usuarioRepository.delete(usuario);
        } else {
            throw new IllegalArgumentException("Usuario " + email + " not found");
        }
    }
}
