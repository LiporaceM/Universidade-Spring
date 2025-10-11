package br.edu.ibmec.service;

import br.edu.ibmec.entity.Disciplina;
import br.edu.ibmec.exception.ValidationException;
import br.edu.ibmec.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplinaService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    private void validateDisciplina(Disciplina disciplina) {
        if (disciplina.getCodigo() < 1 || disciplina.getCodigo() > 99) {
            throw new ValidationException("Código da disciplina deve estar entre 1 e 99");
        }
        if (disciplina.getNome() == null || disciplina.getNome().length() < 1 || disciplina.getNome().length() > 20) {
            throw new ValidationException("Nome da disciplina deve ter entre 1 e 20 caracteres");
        }
    }

    public Disciplina create(Disciplina disciplina) {
        validateDisciplina(disciplina);
        return disciplinaRepository.save(disciplina);
    }

    public List<Disciplina> findAll() {
        return disciplinaRepository.findAll();
    }

    public Optional<Disciplina> findById(long id) {
        return disciplinaRepository.findById(id);
    }

    public Disciplina update(long id, Disciplina disciplinaDetails) {
        validateDisciplina(disciplinaDetails);
        Optional<Disciplina> optionalDisciplina = disciplinaRepository.findById(id);
        if (optionalDisciplina.isPresent()) {
            Disciplina disciplina = optionalDisciplina.get();
            disciplina.setNome(disciplinaDetails.getNome());
            disciplina.setCodigo(disciplinaDetails.getCodigo());
            disciplina.setCurso(disciplinaDetails.getCurso());
            return disciplinaRepository.save(disciplina);
        }
        return null;
    }

    public void delete(long id) {
        disciplinaRepository.deleteById(id);
    }
}