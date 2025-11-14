package br.edu.ibmec.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "professores")
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private String email;

    private String departamento;

    @OneToMany(mappedBy = "professor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Turma> turmas = new ArrayList<>();

    public void addTurma(Turma turma) {
        turmas.add(turma);
        turma.setProfessor(this);
    }

    public void removeTurma(Turma turma) {
        turmas.remove(turma);
        turma.setProfessor(null);
    }
}
