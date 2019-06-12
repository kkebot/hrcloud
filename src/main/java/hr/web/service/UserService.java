package hr.web.service;

import hr.data.employee.EmployeeRepository;
import hr.domain.employee.Employee;
import hr.security.SampleUserSettings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class UserService implements UserDetailsService {

    private EmployeeRepository employeeRepo;
    private SampleUserSettings sampleUserSettings;
    private PasswordEncoder passwordEncoder = NoOpPasswordEncoder.getInstance();

    public UserService(EmployeeRepository employeeRepo, SampleUserSettings sampleUserSettings) {
        this.employeeRepo = employeeRepo;
        this.sampleUserSettings = sampleUserSettings;
    }

    @Transactional // https://stackoverflow.com/a/32276916 org.hibernate.LazyInitializationException: could not initialize proxy - no Session
    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        SampleUserSettings.SampleUser user = sampleUserSettings
                .getUsers().stream()
                .filter(u -> username.equals(u.getUsername()))
                .findFirst().orElse(null);


        if (user == null) {
            Employee employee = employeeRepo
                    .findById(Long.valueOf(username))
                    .orElseThrow(() -> new UsernameNotFoundException("id="+username));

            return new User(employee.getId().toString(),
                    passwordEncoder.encode(employee.getPassword()),
                    employee.getStatus(),
                    employee.getStatus(),
                    employee.getStatus(),
                    employee.getStatus(),
                    employee.getAuthorities());

        } else {
            return new User(username, passwordEncoder.encode(user.getPassword()), user.getAuthorities());
        }
    }
}
