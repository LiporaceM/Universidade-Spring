package br.edu.ibmec.validator;

import br.edu.ibmec.entity.Aluno;
import br.edu.ibmec.entity.Turma;

public class TurmaCapacityValidator extends AbstractEnrollmentValidator {
    
    @Override
    public void validate(Aluno aluno, Turma turma) throws IllegalArgumentException {
        if (turma == null) {
            throw new IllegalArgumentException("Turma não pode ser nula");
        }
        
        if (turma.getCapacidade() <= 0) {
            throw new IllegalArgumentException(
                String.format("Turma %d possui capacidade inválida", turma.getCodigo())
            );
        }
        
        if (turma.getInscricoes() != null && turma.getInscricoes().size() >= turma.getCapacidade()) {
            throw new IllegalArgumentException(
                String.format(
                    "Turma %d já atingiu sua capacidade máxima de %d alunos",
                    turma.getCodigo(),
                    turma.getCapacidade()
                )
            );
        }
    }
}
