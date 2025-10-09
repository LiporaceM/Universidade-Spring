package br.edu.ibmec.DTO;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class AlunoDTO {
    private int matricula;
    private String nome;
    private LocalDate dataNascimento;
    private boolean matriculaAtiva;
    private EstadoCivilDTO estadoCivil;
    private int cursoId;
    private List<String> telefones;
}
