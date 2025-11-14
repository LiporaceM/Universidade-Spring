package br.edu.ibmec.validator;

import br.edu.ibmec.entity.Aluno;
import br.edu.ibmec.entity.Turma;

public class DisciplinaBelongsToCursoValidator extends AbstractEnrollmentValidator {
    
    @Override
    public void validate(Aluno aluno, Turma turma) throws IllegalArgumentException {
        if (turma == null || turma.getDisciplina() == null) {
            throw new IllegalArgumentException("Turma ou Disciplina não pode ser nula");
        }
        
        if (aluno.getCurso() == null) {
            throw new IllegalArgumentException(
                String.format("Aluno com matrícula %d não possui um curso associado", aluno.getMatricula())
            );
        }
        
        if (!turma.getDisciplina().getCurso().getId().equals(aluno.getCurso().getId())) {
            throw new IllegalArgumentException(
                String.format(
                    "A disciplina '%s' não pertence ao curso '%s' do aluno",
                    turma.getDisciplina().getNome(),
                    aluno.getCurso().getNome()
                )
            );
        }
    }
}
