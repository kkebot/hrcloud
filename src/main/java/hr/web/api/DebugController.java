package hr.web.api;

import hr.data.employee.EmployeeRepository;
import hr.data.organization.DepartmentRepository;
import hr.data.organization.PositionRepository;
import hr.data.wage.PayScaleRepository;
import hr.domain.employee.Employee;
import hr.domain.organization.Department;
import hr.domain.organization.Position;
import hr.domain.wage.PayScale;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api/debug")
public class DebugController {

    private DepartmentRepository departmentRepo;
    private PositionRepository positionRepo;
    private PayScaleRepository payScaleRepo;
    private EmployeeRepository employeeRepo;

    public DebugController(DepartmentRepository departmentRepo, PositionRepository positionRepo, PayScaleRepository payScaleRepo, EmployeeRepository employeeRepo) {
        this.departmentRepo = departmentRepo;
        this.positionRepo = positionRepo;
        this.payScaleRepo = payScaleRepo;
        this.employeeRepo = employeeRepo;
    }

    @GetMapping("/departments")
    public Collection<Department> getDepartments() {
        return departmentRepo.findByStatusIsTrue();
    }

    @GetMapping("/positions")
    public Collection<Position> getPositions() {
        return positionRepo.findByStatusIsTrue();
    }

    @GetMapping("/scales")
    public Collection<PayScale> getPayScales() {
        return payScaleRepo.findAll();
    }

    @GetMapping("/employees")
    public Collection<Employee> getEmployees() {
        return employeeRepo.findByStatusIsTrue();
    }
}
