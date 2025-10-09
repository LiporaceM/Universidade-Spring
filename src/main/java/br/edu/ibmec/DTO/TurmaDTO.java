package br.edu.ibmec.DTO;

import lombok.Data;

@Data
public class TurmaDTO {
    private int codigo;
    private int ano;
    private int semestre;
    private int disciplinaId;
}
