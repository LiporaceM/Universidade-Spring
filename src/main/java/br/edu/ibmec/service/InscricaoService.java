package br.edu.ibmec.service;

import br.edu.ibmec.entity.Inscricao;
import br.edu.ibmec.exception.ValidationException;
import br.edu.ibmec.repository.InscricaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InscricaoService {

    @Autowired
    private InscricaoRepository inscricaoRepository;

    private void validateInscricao(Inscricao inscricao) {
        if (inscricao.getTurma() == null) {
            throw new ValidationException("Inscrição deve estar associada a uma turma");
        }
        if (inscricao.getTurma().getCodigo() < 1 || inscricao.getTurma().getCodigo() > 999) {
            throw new ValidationException("Código da turma na inscrição deve ser válido");
        }
        if (inscricao.getTurma().getAno() < 1900 || inscricao.getTurma().getAno() > 2024) {
            throw new ValidationException("Ano da turma na inscrição deve ser válido");
        }
    }
    
    public Inscricao create(Inscricao inscricao) {
        validateInscricao(inscricao);
        return inscricaoRepository.save(inscricao);
    }

    public List<Inscricao> findAll() {
        return inscricaoRepository.findAll();
    }

    public Optional<Inscricao> findById(long id) {
        return inscricaoRepository.findById(id);
    }

    public Inscricao update(long id, Inscricao inscricaoDetails) {
        validateInscricao(inscricaoDetails);
        Optional<Inscricao> optionalInscricao = inscricaoRepository.findById(id);
        if (optionalInscricao.isPresent()) {
            Inscricao inscricao = optionalInscricao.get();
            inscricao.setAvaliacao1(inscricaoDetails.getAvaliacao1());
            inscricao.setAvaliacao2(inscricaoDetails.getAvaliacao2());
            inscricao.setNumFaltas(inscricaoDetails.getNumFaltas());
            inscricao.setSituacao(inscricaoDetails.getSituacao());
            inscricao.setAluno(inscricaoDetails.getAluno());
            inscricao.setTurma(inscricaoDetails.getTurma());
            return inscricaoRepository.save(inscricao);
        }
        return null;
    }

    public void delete(long id) {
        inscricaoRepository.deleteById(id);
    }
}