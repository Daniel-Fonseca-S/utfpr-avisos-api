package edu.ssic.service;


import edu.ssic.entity.Usuario;
import edu.ssic.repository.UsuarioRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class UsuarioService {

    @Inject
    UsuarioRepository usuarioRepository;

    public List<Usuario> retrieveAll(){return usuarioRepository.findAll().list();}

    public Usuario retrieve(long id){return usuarioRepository.findById(id);}


    public void create(Usuario usuario){
        if (usuarioRepository.findAll().stream().anyMatch(d -> d.getRa().equals(usuario.getRa()))){
            throw new IllegalArgumentException("Ra"+ usuario.getRa() + " já existente");
        }
        usuarioRepository.persist(usuario);
    }

    public void update(Usuario usuario) {
        if (usuario.getId() == null){
            throw new IllegalArgumentException("Usuario id cannot be null");
        }
        Usuario usuarioToUpdate = retrieve(usuario.getId());
        if (usuarioToUpdate == null) {
            throw new IllegalArgumentException("Usuario " + usuario.getId() + " not found");
        }
        usuarioToUpdate.setRa(usuario.getRa());
        usuarioToUpdate.setNome(usuario.getNome());
        usuarioToUpdate.setTipo(usuario.getTipo());
        usuarioToUpdate.setSenha(usuario.getSenha());
        usuarioToUpdate.setEmail(usuario.getEmail());
        usuarioRepository.persist(usuarioToUpdate);
    }

    public void delete(Long id) {
        Usuario usuario = retrieve(id);
        if (usuario != null) {
            usuarioRepository.delete(usuario);
        } else {
            throw new IllegalArgumentException("Usuario " + id + " not found");
        }
    }
}
