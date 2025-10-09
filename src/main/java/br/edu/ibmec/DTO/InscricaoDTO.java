package br.edu.ibmec.DTO;

import br.edu.ibmec.entity.Situacao;
import lombok.Data;

@Data
public class InscricaoDTO {
    private float avaliacao1;
    private float avaliacao2;
    private int numFaltas;
    private Situacao situacao;
    private long alunoId;
    private long turmaId;
}
