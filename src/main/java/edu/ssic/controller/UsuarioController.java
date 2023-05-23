package edu.ssic.controller;


import edu.ssic.entity.Usuario;
import edu.ssic.service.UsuarioService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.List;

@Path("/usuario")
public class UsuarioController {

    @Inject
    UsuarioService usuarioService;

    @GET
    public List<Usuario> retrieveAll() {
        List<Usuario> usuario = new ArrayList<>();
        try {
            usuario = usuarioService.retrieveAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuario;
    }

    @GET
    @Path("/{id}")
    public Usuario retrieve(Long id) {
        Usuario usuario = new Usuario();
        try {
            usuario = usuarioService.retrieve(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuario;
    }

    @POST
    @Transactional
    public Object create(Usuario usuario) {
        try {
            usuarioService.create(usuario);
            return usuario;
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @PUT
    @Transactional
    public Usuario update(Usuario usuario) {
        try {
            usuarioService.update(usuario);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuario;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public String delete(Long id) {
        try {
            usuarioService.delete(id);
            return "Usuario excluída com sucesso!";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
