package br.edu.ibmec.service;

import br.edu.ibmec.entity.Inscricao;
import br.edu.ibmec.repository.InscricaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InscricaoService {

    @Autowired
    private InscricaoRepository inscricaoRepository;

    public Inscricao create(Inscricao inscricao) {
        return inscricaoRepository.save(inscricao);
    }

    public List<Inscricao> findAll() {
        return inscricaoRepository.findAll();
    }

    public Optional<Inscricao> findById(long id) {
        return inscricaoRepository.findById(id);
    }

    public Inscricao update(long id, Inscricao inscricaoDetails) {
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
