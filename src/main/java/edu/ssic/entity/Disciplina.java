package edu.ssic.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@ToString
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
}
