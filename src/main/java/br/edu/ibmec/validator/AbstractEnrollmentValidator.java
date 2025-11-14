package br.edu.ibmec.validator;

import br.edu.ibmec.entity.Aluno;
import br.edu.ibmec.entity.Turma;

public abstract class AbstractEnrollmentValidator implements EnrollmentValidator {
    protected EnrollmentValidator nextValidator;

    @Override
    public void setNextValidator(EnrollmentValidator nextValidator) {
        this.nextValidator = nextValidator;
    }

    @Override
    public void executeChain(Aluno aluno, Turma turma) throws IllegalArgumentException {
        validate(aluno, turma);
        
        if (nextValidator != null) {
            nextValidator.executeChain(aluno, turma);
        }
    }
}
