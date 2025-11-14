package br.edu.ibmec.service;

import br.edu.ibmec.calculator.BaseTuitionCalculator;
import br.edu.ibmec.calculator.PerDisciplineFeeDecorator;
import br.edu.ibmec.calculator.ScholarshipDiscountDecorator;
import br.edu.ibmec.calculator.TuitionCalculator;
import br.edu.ibmec.entity.Aluno;
import br.edu.ibmec.exception.ValidationException;
import br.edu.ibmec.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TuitionService {
    
    @Autowired
    private AlunoRepository alunoRepository;
    
    private static final BigDecimal DEFAULT_DISCIPLINE_FEE = new BigDecimal("50.00");
    private static final BigDecimal DEFAULT_SCHOLARSHIP_DISCOUNT = new BigDecimal("0");
    
    public BigDecimal calculateBasicTuition(Long alunoId) {
        Aluno aluno = alunoRepository.findById(alunoId)
            .orElseThrow(() -> new ValidationException(
                String.format("Aluno com ID %d não encontrado", alunoId)
            ));
        
        TuitionCalculator calculator = new BaseTuitionCalculator();
        calculator = new PerDisciplineFeeDecorator(calculator, DEFAULT_DISCIPLINE_FEE);
        
        return calculator.calculateTuition(aluno);
    }
    
    public BigDecimal calculateTuitionWithScholarship(Long alunoId, BigDecimal discountPercentage) {
        if (discountPercentage == null) {
            discountPercentage = DEFAULT_SCHOLARSHIP_DISCOUNT;
        }
        
        Aluno aluno = alunoRepository.findById(alunoId)
            .orElseThrow(() -> new ValidationException(
                String.format("Aluno com ID %d não encontrado", alunoId)
            ));
        
        TuitionCalculator calculator = new BaseTuitionCalculator();
        calculator = new PerDisciplineFeeDecorator(calculator, DEFAULT_DISCIPLINE_FEE);
        calculator = new ScholarshipDiscountDecorator(calculator, discountPercentage);
        
        return calculator.calculateTuition(aluno);
    }
    
    public BigDecimal calculateCustomTuition(Long alunoId, BigDecimal disciplineFee, BigDecimal discountPercentage) {
        Aluno aluno = alunoRepository.findById(alunoId)
            .orElseThrow(() -> new ValidationException(
                String.format("Aluno com ID %d não encontrado", alunoId)
            ));
        
        TuitionCalculator calculator = new BaseTuitionCalculator();
        
        if (disciplineFee != null && disciplineFee.compareTo(BigDecimal.ZERO) > 0) {
            calculator = new PerDisciplineFeeDecorator(calculator, disciplineFee);
        }
        
        if (discountPercentage != null && discountPercentage.compareTo(BigDecimal.ZERO) > 0) {
            calculator = new ScholarshipDiscountDecorator(calculator, discountPercentage);
        }
        
        return calculator.calculateTuition(aluno);
    }
    
    public TuitionBreakdown getTuitionBreakdown(Long alunoId, BigDecimal discountPercentage) {
        Aluno aluno = alunoRepository.findById(alunoId)
            .orElseThrow(() -> new ValidationException(
                String.format("Aluno com ID %d não encontrado", alunoId)
            ));
        
        BaseTuitionCalculator baseCalculator = new BaseTuitionCalculator();
        BigDecimal baseTuition = baseCalculator.calculateTuition(aluno);
        
        PerDisciplineFeeDecorator feeCalculator = new PerDisciplineFeeDecorator(baseCalculator, DEFAULT_DISCIPLINE_FEE);
        BigDecimal withFees = feeCalculator.calculateTuition(aluno);
        BigDecimal totalFees = withFees.subtract(baseTuition);
        
        BigDecimal finalTuition = withFees;
        BigDecimal discountAmount = BigDecimal.ZERO;
        
        if (discountPercentage != null && discountPercentage.compareTo(BigDecimal.ZERO) > 0) {
            ScholarshipDiscountDecorator discountCalculator = new ScholarshipDiscountDecorator(feeCalculator, discountPercentage);
            finalTuition = discountCalculator.calculateTuition(aluno);
            discountAmount = withFees.subtract(finalTuition);
        }
        
        return new TuitionBreakdown(
            aluno.getNome(),
            baseTuition,
            totalFees,
            discountAmount,
            finalTuition,
            aluno.getInscricoes().size()
        );
    }
    
    public static class TuitionBreakdown {
        public String studentName;
        public BigDecimal baseTuition;
        public BigDecimal disciplineFees;
        public BigDecimal discountAmount;
        public BigDecimal finalTuition;
        public int enrollmentCount;
        
        public TuitionBreakdown(String studentName, BigDecimal baseTuition, BigDecimal disciplineFees,
                               BigDecimal discountAmount, BigDecimal finalTuition, int enrollmentCount) {
            this.studentName = studentName;
            this.baseTuition = baseTuition;
            this.disciplineFees = disciplineFees;
            this.discountAmount = discountAmount;
            this.finalTuition = finalTuition;
            this.enrollmentCount = enrollmentCount;
        }
        
        public String getStudentName() { return studentName; }
        public BigDecimal getBaseTuition() { return baseTuition; }
        public BigDecimal getDisciplineFees() { return disciplineFees; }
        public BigDecimal getDiscountAmount() { return discountAmount; }
        public BigDecimal getFinalTuition() { return finalTuition; }
        public int getEnrollmentCount() { return enrollmentCount; }
    }
}
