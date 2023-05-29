package edu.ssic.controller;

import edu.ssic.entity.Aviso;
import edu.ssic.service.AvisoService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import java.util.List;

@Path("/aviso")
public class AvisoController {
    @Inject
    AvisoService avisoService;

    @GET
    @Path("/{id}")
    public Aviso retrieve(Long id) {
        return avisoService.retrieve(id);
    }

    @GET
    @Path("/disciplina/{id}")
    public List<Aviso> retrieveByDisciplina(Long id) {
        return avisoService.retrieveAll(id);
    }

    @POST
    @Transactional
    public Aviso create(Aviso aviso) {
        try {
            avisoService.create(aviso);
            return aviso;
        } catch (IllegalArgumentException e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @PUT
    @Transactional
    public Aviso update(Aviso aviso) {
        try {
            avisoService.update(aviso);
            return aviso;
        } catch (IllegalArgumentException e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public String delete(Long id) {
        try {
            avisoService.delete(id);
            return "Aviso " +id + " deleted";
        } catch (IllegalArgumentException e) {
            throw new BadRequestException(e.getMessage());
        }
    }
}
