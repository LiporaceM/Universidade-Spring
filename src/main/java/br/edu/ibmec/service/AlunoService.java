package br.edu.ibmec.service;

import br.edu.ibmec.DTO.AlunoDTO;
import br.edu.ibmec.entity.Aluno;
import br.edu.ibmec.exception.ValidationException;
import br.edu.ibmec.factory.AlunoFactory;
import br.edu.ibmec.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;
    
    @Autowired
    private AlunoFactory alunoFactory;

    private void validateAluno(Aluno aluno) {
        if (aluno.getMatricula() < 1 || aluno.getMatricula() > 99) {
            throw new ValidationException("Matrícula do aluno deve estar entre 1 e 99");
        }
        if (aluno.getNome() == null || aluno.getNome().length() < 1 || aluno.getNome().length() > 20) {
            throw new ValidationException("Nome do aluno deve ter entre 1 e 20 caracteres");
        }
    }

    public Aluno create(AlunoDTO alunoDTO) {
        Aluno aluno = alunoFactory.createAlunoFromDTO(alunoDTO);
        validateAluno(aluno);
        return alunoRepository.save(aluno);
    }

    public Aluno createFromEntity(Aluno aluno) {
        validateAluno(aluno);
        return alunoRepository.save(aluno);
    }

    public List<Aluno> findAll() {
        return alunoRepository.findAll();
    }

    public Optional<Aluno> findById(long id) {
        return alunoRepository.findById(id);
    }

    public Aluno update(long id, Aluno alunoDetails) {
        validateAluno(alunoDetails);
        Optional<Aluno> optionalAluno = alunoRepository.findById(id);
        if (optionalAluno.isPresent()) {
            Aluno aluno = optionalAluno.get();
            aluno.setNome(alunoDetails.getNome());
            aluno.setMatricula(alunoDetails.getMatricula());
            aluno.setDataNascimento(alunoDetails.getDataNascimento());
            aluno.setMatriculaAtiva(alunoDetails.isMatriculaAtiva());
            aluno.setEstadoCivil(alunoDetails.getEstadoCivil());
            aluno.setCurso(alunoDetails.getCurso());
            aluno.setTelefones(alunoDetails.getTelefones());
            return alunoRepository.save(aluno);
        }
        return null;
    }

    public void delete(long id) {
        alunoRepository.deleteById(id);
    }
}