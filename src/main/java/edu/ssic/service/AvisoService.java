package edu.ssic.service;

import edu.ssic.entity.Aviso;
import edu.ssic.entity.Disciplina;
import edu.ssic.repository.AvisoRepository;
import edu.ssic.repository.DisciplinaRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class AvisoService {
    @Inject
    AvisoRepository avisoRepository;

    @Inject
    DisciplinaRepository disciplinaRepository;

    public List<Aviso> retrieveAll(Long idDisciplina) {
        List<Aviso> avisoList;
        if (idDisciplina == null) {
            avisoList = avisoRepository.findAll().list();
            return avisoList.stream().peek(aviso -> {
                aviso.getDisciplina().setUsuarioList(null);
                aviso.getDisciplina().setAvisoList(null);
            }).toList();
        } else if (disciplinaRepository.findById(idDisciplina) == null) {
            throw new IllegalArgumentException("Disciplina " + idDisciplina + " not found");
        } else {
            avisoList = avisoRepository.find("disciplina.id", idDisciplina).list();
            return avisoList.stream().peek(aviso -> {
                aviso.getDisciplina().setUsuarioList(null);
                aviso.getDisciplina().setAvisoList(null);
            }).toList();
        }
    }

    public Aviso retrieve(Long id) {
        if (id == null)
            throw new IllegalArgumentException("Aviso id cannot be null");
        Aviso aviso = avisoRepository.findById(id);
        aviso.getDisciplina().setUsuarioList(null);
        aviso.getDisciplina().setAvisoList(null);
        return aviso;
    }

    public void create(Aviso aviso) {
        Long idDisciplina = aviso.getDisciplina().getId();
        if (idDisciplina == null)
            throw new IllegalArgumentException("Disciplina id cannot be null");
        Disciplina disciplina = disciplinaRepository.findById(idDisciplina);
        if (disciplina == null) {
            throw new IllegalArgumentException("Disciplina " + idDisciplina + " not found");
        }
        avisoRepository.persist(aviso);
    }

    public void update(Aviso aviso) {
        Long idDisciplina = aviso.getDisciplina().getId();
        if (idDisciplina == null)
            throw new IllegalArgumentException("Disciplina id cannot be null");
        if (disciplinaRepository.findById(idDisciplina) == null) {
            throw new IllegalArgumentException("Disciplina " + idDisciplina + " not found");
        }
        if (aviso.getId() == null) {
            throw new IllegalArgumentException("Aviso id cannot be null");
        }
        Aviso avisoToUpdate = retrieve(aviso.getId());
        if (avisoToUpdate == null) {
            throw new IllegalArgumentException("Aviso " + aviso.getId() + " not found");
        }
        avisoToUpdate.setTitulo(aviso.getTitulo());
        avisoToUpdate.setDescricao(aviso.getDescricao());
        avisoToUpdate.setDisciplina(disciplinaRepository.findById(idDisciplina));
        avisoToUpdate.setDataReferente(aviso.getDataReferente());
        avisoRepository.persist(avisoToUpdate);
    }

    public void delete(Long id) {
        Aviso aviso = retrieve(id);
        if (aviso != null) {
            avisoRepository.delete(aviso);
        } else {
            throw new IllegalArgumentException("Aviso " + id + " not found");
        }
    }
}
