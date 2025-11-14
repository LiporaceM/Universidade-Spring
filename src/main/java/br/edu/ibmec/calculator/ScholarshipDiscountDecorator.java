package br.edu.ibmec.calculator;

import br.edu.ibmec.entity.Aluno;

import java.math.BigDecimal;

public class ScholarshipDiscountDecorator extends TuitionDecorator {
    
    private BigDecimal discountPercentage;
    
    public ScholarshipDiscountDecorator(TuitionCalculator wrappedCalculator, BigDecimal discountPercentage) {
        super(wrappedCalculator);
        this.discountPercentage = discountPercentage != null ? discountPercentage : new BigDecimal("10");
    }
    
    @Override
    public BigDecimal calculateTuition(Aluno aluno) {
        BigDecimal currentTuition = wrappedCalculator.calculateTuition(aluno);
        
        BigDecimal discountFactor = new BigDecimal("100").subtract(discountPercentage);
        BigDecimal discountedTuition = currentTuition.multiply(discountFactor).divide(new BigDecimal("100"));
        
        return discountedTuition.max(BigDecimal.ZERO);
    }
    
    @Override
    public String getDescription() {
        return "Scholarship Discount Decorator (" + discountPercentage + "%)";
    }
}
