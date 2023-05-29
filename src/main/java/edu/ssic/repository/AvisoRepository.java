package edu.ssic.repository;

import edu.ssic.entity.Aviso;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AvisoRepository implements PanacheRepository<Aviso> {
}
