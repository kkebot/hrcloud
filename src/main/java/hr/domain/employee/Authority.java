package hr.domain.employee;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum Authority {
    USER,
    ADMIN;

    public GrantedAuthority granted = new SimpleGrantedAuthority(name());
}
