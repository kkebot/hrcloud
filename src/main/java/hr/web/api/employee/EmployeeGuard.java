package hr.web.api.employee;

import hr.domain.employee.Authority;
import hr.domain.employee.Employee;
import hr.web.api.exception.Forbidden;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public class EmployeeGuard {

    public static Long validateSelfOnly(String employeeId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        if (!authentication.getAuthorities().contains(Authority.ADMIN.granted) && !employeeId.equals(user.getUsername()))
            throw new Forbidden();

        return Long.valueOf(employeeId);
    }

    public static void validateSelfOnly(Long employeeId) {
        validateSelfOnly(String.valueOf(employeeId));
    }

    public static void validateSelfOnly(Employee employee) {
        validateSelfOnly(employee.getId());
    }


}
