package br.edu.ibmec.calculator;

import br.edu.ibmec.entity.Aluno;

import java.math.BigDecimal;

public class PerDisciplineFeeDecorator extends TuitionDecorator {
    
    private BigDecimal feePerDiscipline;
    
    public PerDisciplineFeeDecorator(TuitionCalculator wrappedCalculator, BigDecimal feePerDiscipline) {
        super(wrappedCalculator);
        this.feePerDiscipline = feePerDiscipline != null ? feePerDiscipline : new BigDecimal("50.00");
    }
    
    @Override
    public BigDecimal calculateTuition(Aluno aluno) {
        BigDecimal baseTuition = wrappedCalculator.calculateTuition(aluno);
        
        if (aluno != null && aluno.getInscricoes() != null && !aluno.getInscricoes().isEmpty()) {
            int enrollmentCount = aluno.getInscricoes().size();
            BigDecimal additionalFee = feePerDiscipline.multiply(new BigDecimal(enrollmentCount));
            return baseTuition.add(additionalFee);
        }
        
        return baseTuition;
    }
    
    @Override
    public String getDescription() {
        return "Per Discipline Fee Decorator";
    }
}
