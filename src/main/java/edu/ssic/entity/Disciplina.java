package edu.ssic.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "disciplina")
public class Disciplina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "codigo", nullable = false, length = 8)
    private String codigo;

    @Column(name = "nome", nullable = false, length = 55)
    private String nome;

    @ManyToMany(mappedBy = "disciplinaList", fetch = FetchType.LAZY)
    private List<Usuario> usuarioList = new ArrayList<>();

    @OneToMany(mappedBy = "disciplina", fetch = FetchType.LAZY)
    private List<Aviso> avisoList = new ArrayList<>();
}
