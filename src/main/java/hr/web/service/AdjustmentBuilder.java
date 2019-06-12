package hr.web.service;

import hr.domain.employee.Adjustment;
import hr.domain.employee.Employee;
import hr.domain.organization.Position;
import hr.domain.wage.PayScale;
import lombok.Getter;

import java.util.Date;

public class AdjustmentBuilder {
    @Getter
    private Adjustment adjustment = new Adjustment();

    private Position initialPosition;
    private PayScale initialPayScale;

    public AdjustmentBuilder(Employee employee) {
        adjustment.setEmployee(employee);
        adjustment.setTo((initialPosition = employee.getMainPosition()));
        adjustment.setAfter((initialPayScale = employee.getScale()));
        adjustment.setEffectiveOn(new Date());
    }

    private boolean isUnchanged(Position to) {
        if (initialPosition != null && to != null)
            return initialPosition.getId().equals(to.getId());
        else
            return initialPosition == to;
    }

    private boolean isUnchanged(PayScale after) {
        if (initialPayScale != null && after != null)
            return initialPayScale.getId().equals(after.getId());
        else
            return initialPayScale == after;
    }

    public boolean isUnchanged() {
        return isUnchanged(adjustment.getTo()) && isUnchanged(adjustment.getAfter());
    }

    public void appoint(Position to) {
        adjustment.setTo(to);

        if (isUnchanged(to))
            return;
        // Position has been changed.
        if (initialPosition != null && initialPosition.getExpectedCount() == initialPosition.getEmployees().size() - 1)
            initialPosition.setLastNormal(new Date());

        adjustment.getEmployee().setMainPosition(to);
        if (to != null && to.getExpectedCount() == to.getEmployees().size() + 1)
            to.setLastNormal(new Date());
    }

    public void change(PayScale after) {
        adjustment.setAfter(after);
        adjustment.getEmployee().setScale(after);
    }

    public void discharge() {
        appoint(null);
        change(null);
        adjustment.getEmployee().setStatus(false);
    }

    public void describe(String description) {
        adjustment.setDescription(description);
    }

    public void since(Date date) {
        adjustment.setEffectiveOn(date);
    }
}
