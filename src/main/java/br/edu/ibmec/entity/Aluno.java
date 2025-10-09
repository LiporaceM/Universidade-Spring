package br.edu.ibmec.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "alunos")
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private int matricula;

    private String nome;

    private LocalDate dataNascimento;

    private int idade;

    private boolean matriculaAtiva;

    @Enumerated(EnumType.STRING)
    private EstadoCivil estadoCivil;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @ElementCollection
    @CollectionTable(name = "aluno_telefones", joinColumns = @JoinColumn(name = "aluno_id"))
    @Column(name = "telefone")
    private List<String> telefones = new ArrayList<>();

    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Inscricao> inscricoes = new ArrayList<>();

    public void addInscricao(Inscricao inscricao) {
        inscricoes.add(inscricao);
        inscricao.setAluno(this);
    }

    public void removeInscricao(Inscricao inscricao) {
        inscricoes.remove(inscricao);
        inscricao.setAluno(null);
    }
}
