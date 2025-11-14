package br.edu.ibmec.calculator;

import br.edu.ibmec.entity.Aluno;

import java.math.BigDecimal;

public abstract class TuitionDecorator implements TuitionCalculator {
    protected TuitionCalculator wrappedCalculator;
    
    public TuitionDecorator(TuitionCalculator wrappedCalculator) {
        this.wrappedCalculator = wrappedCalculator;
    }
    
    @Override
    public BigDecimal calculateTuition(Aluno aluno) {
        return wrappedCalculator.calculateTuition(aluno);
    }
}
