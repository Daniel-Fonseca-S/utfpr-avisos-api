package edu.ssic.controller;


import edu.ssic.entity.Usuario;
import edu.ssic.service.UsuarioService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

@Path("/usuario")
public class UsuarioController {

    @Inject
    UsuarioService usuarioService;

    @POST
    @Path("/login")
    public Usuario retrieve(Usuario usuario) {
        return usuarioService.retrieve(usuario.getRa(), usuario.getSenha());
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
    @Transactional
    public String delete(Usuario usuario) {
        try {
            usuarioService.delete(usuario.getEmail(), usuario.getSenha());
            return "Usuario " + usuario.getEmail() + " deleted";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
