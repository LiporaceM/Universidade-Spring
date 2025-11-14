package br.edu.ibmec.calculator;

import br.edu.ibmec.entity.Aluno;

import java.math.BigDecimal;

public interface TuitionCalculator {
    
    BigDecimal calculateTuition(Aluno aluno);
    
    String getDescription();
}
