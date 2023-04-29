package edu.ssic.service;

import edu.ssic.entity.Disciplina;
import edu.ssic.repository.DisciplinaRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class DisciplinaService {
    @Inject
    DisciplinaRepository disciplinaRepository;

    public List<Disciplina> retrieveAll() {
        return disciplinaRepository.findAll().list();
    }

    public Disciplina retrieve(Long id) {
        return disciplinaRepository.findById(id);
    }

    public void create(Disciplina disciplina) {
        if (disciplinaRepository.findAll().stream().anyMatch(d -> d.getCodigo().equals(disciplina.getCodigo()))) {
            throw new IllegalArgumentException("Disciplina " + disciplina.getCodigo() + " already exists");
        }
        disciplinaRepository.persist(disciplina);
    }

    public void update(Disciplina disciplina) {
        if (disciplina.getId() == null) {
            throw new IllegalArgumentException("Disciplina id cannot be null");
        }
        Disciplina disciplinaToUpdate = retrieve(disciplina.getId());
        if (disciplinaToUpdate == null) {
            throw new IllegalArgumentException("Disciplina " + disciplina.getId() + " not found");
        }
        disciplinaToUpdate.setCodigo(disciplina.getCodigo());
        disciplinaToUpdate.setNome(disciplina.getNome());
        disciplinaRepository.persist(disciplinaToUpdate);
    }

    public void delete(Long id) {
        Disciplina disciplina = retrieve(id);
        if (disciplina != null) {
            disciplinaRepository.delete(disciplina);
        } else {
            throw new IllegalArgumentException("Disciplina " + id + " not found");
        }
    }
}
