package hr.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "sample")
public class SampleUserSettings {

    private List<SampleUser> users = new ArrayList<>();

    @Getter
    @Setter
    public static class SampleUser {
        private String username;
        private String password;
        private String authority;

        @JsonIgnore
        public Collection<GrantedAuthority> getAuthorities() {
            return Collections.singletonList(new SimpleGrantedAuthority(authority));
        }
    }
}
