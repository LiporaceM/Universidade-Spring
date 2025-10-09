package br.edu.ibmec.service;

import br.edu.ibmec.entity.Disciplina;
import br.edu.ibmec.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplinaService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    public Disciplina create(Disciplina disciplina) {
        return disciplinaRepository.save(disciplina);
    }

    public List<Disciplina> findAll() {
        return disciplinaRepository.findAll();
    }

    public Optional<Disciplina> findById(long id) {
        return disciplinaRepository.findById(id);
    }

    public Disciplina update(long id, Disciplina disciplinaDetails) {
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
