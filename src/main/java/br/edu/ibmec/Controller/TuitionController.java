package br.edu.ibmec.Controller;

import br.edu.ibmec.service.TuitionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/alunos")
@Tag(name = "Mensalidade", description = "API for calculating student tuition")
public class TuitionController {
    
    @Autowired
    private TuitionService tuitionService;
    
    @GetMapping("/{id}/mensalidade")
    @Operation(summary = "Get the monthly tuition for a student")
    public ResponseEntity<TuitionResponse> getTuition(@PathVariable(name = "id") Long alunoId) {
        BigDecimal basicTuition = tuitionService.calculateBasicTuition(alunoId);
        return new ResponseEntity<>(
            new TuitionResponse(basicTuition, "Basic tuition calculated"),
            HttpStatus.OK
        );
    }
    
    @GetMapping("/{id}/mensalidade/com-bolsa")
    @Operation(summary = "Get the monthly tuition for a student with scholarship")
    public ResponseEntity<TuitionResponse> getTuitionWithScholarship(
            @PathVariable(name = "id") Long alunoId,
            @RequestParam(name = "desconto", required = false) BigDecimal discountPercentage) {
        BigDecimal tuition = tuitionService.calculateTuitionWithScholarship(alunoId, discountPercentage);
        return new ResponseEntity<>(
            new TuitionResponse(tuition, "Tuition calculated with scholarship discount"),
            HttpStatus.OK
        );
    }
    
    @GetMapping("/{id}/mensalidade/customizada")
    @Operation(summary = "Get the monthly tuition for a student with custom parameters")
    public ResponseEntity<TuitionResponse> getCustomTuition(
            @PathVariable(name = "id") Long alunoId,
            @RequestParam(name = "taxa-disciplina", required = false) BigDecimal disciplineFee,
            @RequestParam(name = "desconto", required = false) BigDecimal discountPercentage) {
        BigDecimal tuition = tuitionService.calculateCustomTuition(alunoId, disciplineFee, discountPercentage);
        return new ResponseEntity<>(
            new TuitionResponse(tuition, "Custom tuition calculated"),
            HttpStatus.OK
        );
    }
    
    @GetMapping("/{id}/mensalidade/detalhado")
    @Operation(summary = "Get detailed tuition breakdown for a student")
    public ResponseEntity<TuitionService.TuitionBreakdown> getTuitionBreakdown(
            @PathVariable(name = "id") Long alunoId,
            @RequestParam(name = "desconto", required = false) BigDecimal discountPercentage) {
        TuitionService.TuitionBreakdown breakdown = tuitionService.getTuitionBreakdown(alunoId, discountPercentage);
        return new ResponseEntity<>(breakdown, HttpStatus.OK);
    }
    
    public static class TuitionResponse {
        public BigDecimal tuition;
        public String message;
        
        public TuitionResponse(BigDecimal tuition, String message) {
            this.tuition = tuition;
            this.message = message;
        }
        
        public BigDecimal getTuition() { return tuition; }
        public String getMessage() { return message; }
    }
}
