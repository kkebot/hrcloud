package hr.domain.wage;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.MappedSuperclass;

@Data
@NoArgsConstructor
@MappedSuperclass
public class WageTerms {
    private double salary;

    private double bonus;

    private double allowance;

    private double overtimePay;

    private double backPay;

    private double payRaise;

    private double payCut; // Negative

    private double earnings;

    void compoundAdd(WageTerms wageTerms) {
        salary += wageTerms.salary;
        bonus += wageTerms.bonus;
        allowance += wageTerms.allowance;
        overtimePay += wageTerms.overtimePay;
        backPay += wageTerms.backPay;
        payRaise += wageTerms.payRaise;
        payCut += wageTerms.payCut;
        earnings += wageTerms.earnings;
    }

    public void assign(WageTerms wageTerms) {
        salary = wageTerms.salary;
        bonus = wageTerms.bonus;
        allowance = wageTerms.allowance;
        overtimePay = wageTerms.overtimePay;
        backPay = wageTerms.backPay;
        payRaise = wageTerms.payRaise;
        payCut = wageTerms.payCut;
        earnings = wageTerms.earnings;
    }

    public void sumUp() {
        earnings = salary + bonus + allowance + overtimePay + backPay + payRaise + payCut;
    }

    public void clear() {
        salary = bonus = allowance = overtimePay = backPay = payRaise = payCut = 0.0;
    }

}
