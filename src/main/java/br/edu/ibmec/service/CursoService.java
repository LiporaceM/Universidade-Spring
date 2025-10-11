package br.edu.ibmec.service;

import br.edu.ibmec.entity.Curso;
import br.edu.ibmec.exception.ValidationException;
import br.edu.ibmec.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    private void validateCurso(Curso curso) {
        if (curso.getCodigo() < 1 || curso.getCodigo() > 99) {
            throw new ValidationException("Código do curso deve estar entre 1 e 99");
        }
        if (curso.getNome() == null || curso.getNome().length() < 1 || curso.getNome().length() > 20) {
            throw new ValidationException("Nome do curso deve ter entre 1 e 20 caracteres");
        }
    }

    public Curso create(Curso curso) {
        validateCurso(curso);
        return cursoRepository.save(curso);
    }

    public List<Curso> findAll() {
        return cursoRepository.findAll();
    }

    public Optional<Curso> findById(long id) {
        return cursoRepository.findById(id);
    }

    public Curso update(long id, Curso cursoDetails) {
        validateCurso(cursoDetails);
        Optional<Curso> optionalCurso = cursoRepository.findById(id);
        if (optionalCurso.isPresent()) {
            Curso curso = optionalCurso.get();
            curso.setNome(cursoDetails.getNome());
            curso.setCodigo(cursoDetails.getCodigo());
            return cursoRepository.save(curso);
        }
        return null;
    }

    public void delete(long id) {
        cursoRepository.deleteById(id);
    }
}