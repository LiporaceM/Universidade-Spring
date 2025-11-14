package br.edu.ibmec.validator;

import br.edu.ibmec.entity.Aluno;
import br.edu.ibmec.entity.Turma;

public class MatriculaAtivaValidator extends AbstractEnrollmentValidator {
    
    @Override
    public void validate(Aluno aluno, Turma turma) throws IllegalArgumentException {
        if (aluno == null) {
            throw new IllegalArgumentException("Aluno não pode ser nulo");
        }
        
        if (!aluno.isMatriculaAtiva()) {
            throw new IllegalArgumentException(
                String.format("Aluno com matrícula %d não possui inscrição ativa", aluno.getMatricula())
            );
        }
    }
}
