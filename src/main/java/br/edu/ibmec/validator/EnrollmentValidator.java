package br.edu.ibmec.validator;

import br.edu.ibmec.entity.Aluno;
import br.edu.ibmec.entity.Turma;

public interface EnrollmentValidator {
    
    void validate(Aluno aluno, Turma turma) throws IllegalArgumentException;
    
    void setNextValidator(EnrollmentValidator nextValidator);
    
    void executeChain(Aluno aluno, Turma turma) throws IllegalArgumentException;
}
