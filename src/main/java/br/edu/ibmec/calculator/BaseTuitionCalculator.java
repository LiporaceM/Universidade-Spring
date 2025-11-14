package br.edu.ibmec.calculator;

import br.edu.ibmec.entity.Aluno;

import java.math.BigDecimal;

public class BaseTuitionCalculator implements TuitionCalculator {
    
    private static final BigDecimal DEFAULT_BASE_TUITION = new BigDecimal("1000.00");
    
    @Override
    public BigDecimal calculateTuition(Aluno aluno) {
        if (aluno == null || aluno.getCurso() == null) {
            return DEFAULT_BASE_TUITION;
        }
        
        if (aluno.getCurso().getBaseTuition() != null && 
            aluno.getCurso().getBaseTuition().compareTo(BigDecimal.ZERO) > 0) {
            return aluno.getCurso().getBaseTuition();
        }
        
        return DEFAULT_BASE_TUITION;
    }
    
    @Override
    public String getDescription() {
        return "Base Tuition Calculator";
    }
}
