package br.edu.ibmec.Controller;

import br.edu.ibmec.entity.Inscricao;
import br.edu.ibmec.service.InscricaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/inscricoes")
@Tag(name = "Inscrições", description = "API for managing enrollments")
public class InscricaoController {

    @Autowired
    private InscricaoService inscricaoService;

    @PostMapping
    @Operation(summary = "Create a new enrollment")
    public ResponseEntity<Inscricao> create(@RequestBody Inscricao inscricao) {
        return new ResponseEntity<>(inscricaoService.create(inscricao), HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get all enrollments")
    public ResponseEntity<List<Inscricao>> findAll() {
        return new ResponseEntity<>(inscricaoService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an enrollment by ID")
    public ResponseEntity<Inscricao> findById(@PathVariable long id) {
        Optional<Inscricao> optionalInscricao = inscricaoService.findById(id);
        return optionalInscricao.map(inscricao -> new ResponseEntity<>(inscricao, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an enrollment")
    public ResponseEntity<Inscricao> update(@PathVariable long id, @RequestBody Inscricao inscricaoDetails) {
        Inscricao updatedInscricao = inscricaoService.update(id, inscricaoDetails);
        if (updatedInscricao != null) {
            return new ResponseEntity<>(updatedInscricao, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an enrollment")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        inscricaoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
