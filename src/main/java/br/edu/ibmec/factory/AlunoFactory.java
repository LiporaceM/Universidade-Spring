package br.edu.ibmec.factory;

import br.edu.ibmec.DTO.AlunoDTO;
import br.edu.ibmec.entity.Aluno;
import br.edu.ibmec.entity.Curso;
import br.edu.ibmec.entity.EstadoCivil;
import br.edu.ibmec.exception.ValidationException;
import br.edu.ibmec.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class AlunoFactory {
    
    @Autowired
    private CursoRepository cursoRepository;
    
    public Aluno createAlunoFromDTO(AlunoDTO alunoDTO) {
        validateAlunoDTO(alunoDTO);
        
        Aluno aluno = new Aluno();
        aluno.setMatricula(alunoDTO.getMatricula());
        aluno.setNome(alunoDTO.getNome());
        aluno.setDataNascimento(alunoDTO.getDataNascimento());
        aluno.setTelefones(alunoDTO.getTelefones());
        
        aluno.setMatriculaAtiva(true);
        
        if (alunoDTO.getCursoId() > 0) {
            Curso curso = cursoRepository.findById((long) alunoDTO.getCursoId())
                .orElseThrow(() -> new ValidationException(
                    String.format("Curso com ID %d não encontrado", alunoDTO.getCursoId())
                ));
            aluno.setCurso(curso);
        } else {
            throw new ValidationException("ID do curso deve ser fornecido e válido");
        }
        
        if (alunoDTO.getEstadoCivil() != null) {
            aluno.setEstadoCivil(EstadoCivil.valueOf(alunoDTO.getEstadoCivil().toString()));
        }
        
        if (alunoDTO.getDataNascimento() != null) {
            aluno.setIdade(calculateAge(alunoDTO.getDataNascimento()));
        }
        
        return aluno;
    }
    
    private void validateAlunoDTO(AlunoDTO alunoDTO) {
        if (alunoDTO == null) {
            throw new ValidationException("AlunoDTO não pode ser nulo");
        }
        
        if (alunoDTO.getMatricula() < 1 || alunoDTO.getMatricula() > 99) {
            throw new ValidationException("Matrícula do aluno deve estar entre 1 e 99");
        }
        
        if (alunoDTO.getNome() == null || alunoDTO.getNome().trim().isEmpty() || 
            alunoDTO.getNome().length() > 20) {
            throw new ValidationException("Nome do aluno deve ter entre 1 e 20 caracteres");
        }
        
        if (alunoDTO.getDataNascimento() == null) {
            throw new ValidationException("Data de nascimento é obrigatória");
        }
        
        if (alunoDTO.getDataNascimento().isAfter(LocalDate.now())) {
            throw new ValidationException("Data de nascimento não pode ser no futuro");
        }
    }
    
    private int calculateAge(LocalDate birthDate) {
        LocalDate today = LocalDate.now();
        return today.getYear() - birthDate.getYear() - 
               (today.getMonthValue() < birthDate.getMonthValue() || 
                (today.getMonthValue() == birthDate.getMonthValue() && 
                 today.getDayOfMonth() < birthDate.getDayOfMonth()) ? 1 : 0);
    }
}
