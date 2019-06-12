package hr.security;

import hr.domain.employee.Authority;
import hr.web.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserService userService;

    private SimpleUrlAuthenticationFailureHandler simpleUrlAuthFailHandler = new SimpleUrlAuthenticationFailureHandler();
    private RESTAuthEntryPoint restAuthEntryPoint = new RESTAuthEntryPoint();
    private RESTSavedReqAwareAuthSuccHandler restSavedReqAwareAuthSuccHandler = new RESTSavedReqAwareAuthSuccHandler();
    private RESTAccessDeniedHandler restAccessDeniedHandler = new RESTAccessDeniedHandler();

    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Here comes the URL configurations
        http.authorizeRequests()
                .antMatchers("/api/employee/update", "/api/employee/password")
                .hasAnyAuthority(Authority.USER.name(), Authority.ADMIN.name())
                .antMatchers("/api/notify/**", "/api/rule/**")
                .hasAuthority(Authority.ADMIN.name())
                .antMatchers(HttpMethod.POST, "/api/**")
                .hasAuthority(Authority.ADMIN.name())
                .antMatchers("/api/**")
                .hasAnyAuthority(Authority.USER.name(), Authority.ADMIN.name())
                .antMatchers("/", "/auth/login", "/**").permitAll();

        http.formLogin()
                .successHandler(restSavedReqAwareAuthSuccHandler)
                .failureHandler(simpleUrlAuthFailHandler)
                .loginProcessingUrl("/auth/login")
                .and().csrf().disable()// 403 forbidden for angular dev server
                .cors() // "Access-Control-Allow-Origins" for Angular dev server
                .and()
                .headers().frameOptions().sameOrigin()// for h2-console
                .and().exceptionHandling()
                .authenticationEntryPoint(restAuthEntryPoint)
                .accessDeniedHandler(restAccessDeniedHandler);

        // TODO Is an RESTful logout needed? Remove JSESSIONID
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        //1.添加CORS配置信息
        CorsConfiguration config = new CorsConfiguration();
        //放行哪些原始域
        config.addAllowedOrigin("*");
        //是否发送Cookie信息
        config.setAllowCredentials(true);
        //放行哪些原始域(请求方式)
        config.addAllowedMethod("*");
        //放行哪些原始域(头部信息)
        config.addAllowedHeader("*");

        //2.添加映射路径
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);
        return configSource;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        UserDetailsManagerConfigurer configurer = auth.inMemoryAuthentication();
//        for (SampleUserSettings.SampleUser sampleUser: sampleUserSettings.getUsers()) {
//            log.debug("User: {} with role: {}", sampleUser.getUsername(), sampleUser.getRole().name());
//            configurer.withUser(sampleUser.getUsername())
//                    .password("{noop}" + sampleUser.getPassword())
//                    .roles(sampleUser.getRole().name());
////            log.debug("User \"{}\" with password \"{}\" and role \"{}\" added",
////                    sampleUser.getUsername(), sampleUser.getPassword(), sampleUser.getRole());
//        }

        auth.userDetailsService(userService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
}
