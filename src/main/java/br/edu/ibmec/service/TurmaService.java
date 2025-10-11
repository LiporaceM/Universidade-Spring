package br.edu.ibmec.service;

import br.edu.ibmec.entity.Turma;
import br.edu.ibmec.exception.ValidationException;
import br.edu.ibmec.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;

    private void validateTurma(Turma turma) {
        if (turma.getCodigo() < 1 || turma.getCodigo() > 99) {
            throw new ValidationException("Código da turma deve estar entre 1 e 99");
        }
        if (turma.getAno() < 1900 || turma.getAno() > 2024) {
            throw new ValidationException("Ano da turma deve estar entre 1900 e 2024");
        }
    }

    public Turma create(Turma turma) {
        validateTurma(turma);
        return turmaRepository.save(turma);
    }

    public List<Turma> findAll() {
        return turmaRepository.findAll();
    }

    public Optional<Turma> findById(long id) {
        return turmaRepository.findById(id);
    }

    public Turma update(long id, Turma turmaDetails) {
        validateTurma(turmaDetails);
        Optional<Turma> optionalTurma = turmaRepository.findById(id);
        if (optionalTurma.isPresent()) {
            Turma turma = optionalTurma.get();
            turma.setCodigo(turmaDetails.getCodigo());
            turma.setAno(turmaDetails.getAno());
            turma.setSemestre(turmaDetails.getSemestre());
            turma.setDisciplina(turmaDetails.getDisciplina());
            return turmaRepository.save(turma);
        }
        return null;
    }

    public void delete(long id) {
        turmaRepository.deleteById(id);
    }
}