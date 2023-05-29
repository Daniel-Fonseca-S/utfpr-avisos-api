package edu.ssic.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "ra", nullable = false, length = 7)
    private String ra;

    @Column(name = "nome", nullable = false, length = 35)
    private String nome;

    @Column(name = "email", nullable = false, length = 60)
    private String email;

    @Column(name = "senha", nullable = false, length = 20)
    private String senha;

    @Column(name = "tipo", nullable = false)
    private Integer tipo;

    @ManyToMany//(mappedBy = "usuarioList", fetch = FetchType.LAZY)
    private List<Disciplina> disciplinaList = new ArrayList<>();

}
