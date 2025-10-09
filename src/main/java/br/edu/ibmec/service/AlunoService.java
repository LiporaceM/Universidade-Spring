package br.edu.ibmec.service;

import br.edu.ibmec.entity.Aluno;
import br.edu.ibmec.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public Aluno create(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    public List<Aluno> findAll() {
        return alunoRepository.findAll();
    }

    public Optional<Aluno> findById(long id) {
        return alunoRepository.findById(id);
    }

    public Aluno update(long id, Aluno alunoDetails) {
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
