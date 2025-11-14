package br.edu.ibmec.service;

import br.edu.ibmec.entity.Professor;
import br.edu.ibmec.exception.ValidationException;
import br.edu.ibmec.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    private void validateProfessor(Professor professor) {
        if (professor.getNome() == null || professor.getNome().trim().isEmpty()) {
            throw new ValidationException("Nome do professor é obrigatório");
        }
    }

    public Professor create(Professor professor) {
        validateProfessor(professor);
        return professorRepository.save(professor);
    }

    public List<Professor> findAll() {
        return professorRepository.findAll();
    }

    public Optional<Professor> findById(long id) {
        return professorRepository.findById((long) id);
    }

    public Professor update(long id, Professor professorDetails) {
        validateProfessor(professorDetails);
        Optional<Professor> optionalProfessor = professorRepository.findById((long) id);
        if (optionalProfessor.isPresent()) {
            Professor professor = optionalProfessor.get();
            professor.setNome(professorDetails.getNome());
            professor.setEmail(professorDetails.getEmail());
            professor.setDepartamento(professorDetails.getDepartamento());
            return professorRepository.save(professor);
        }
        return null;
    }

    public void delete(long id) {
        professorRepository.deleteById((long) id);
    }
}
