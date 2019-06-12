package hr.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

// TODO Not tested.
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        String index = "forward:/index.html";

//        registry.addViewController("/organization").setViewName(index);
        Arrays.asList(
                "/organization/**",
                "/employee/**",
                "/wage/**",
                "/login",
                "/notification/**",
                "/unauthorized",
                "/404").forEach(path -> registry.addViewController(path).setViewName(index));
    }
}
