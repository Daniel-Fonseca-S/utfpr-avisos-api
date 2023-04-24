package edu.ssic.controller;

import edu.ssic.entity.Disciplina;
import edu.ssic.service.DisciplinaService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.List;

@Path("/disciplina")
public class DisciplinaController {
    @Inject
    DisciplinaService disciplinaService;

    @GET
    public List<Disciplina> retrieveAll() {
        List<Disciplina> disciplinas = new ArrayList<>();
        try {
            disciplinas = disciplinaService.retrieveAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return disciplinas;
    }

    @GET
    @Path("/{id}")
    public Disciplina retrieve(Long id) {
        Disciplina disciplina = new Disciplina();
        try {
            disciplina = disciplinaService.retrieve(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return disciplina;
    }

    @POST
    @Transactional
    public Object create(Disciplina disciplina) {
        try {
            disciplinaService.create(disciplina);
            return disciplina;
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @PUT
    @Transactional
    public Disciplina update(Disciplina disciplina) {
        try {
            disciplinaService.update(disciplina);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return disciplina;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public String delete(Long id) {
        try {
            disciplinaService.delete(id);
            return "Disciplina excluída com sucesso!";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
