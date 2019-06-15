package pl.motooto.webapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/logout_success").setViewName("logout_success");
        registry.addViewController("/register").setViewName("register");
        registry.addViewController("/register_success").setViewName("register_success");
        registry.addViewController("/register_fail").setViewName("register_fail");
        registry.addViewController("/add_advert").setViewName("add_advert");
        registry.addViewController("/search").setViewName("search");
        registry.addViewController("/advertisement").setViewName("advertisement");
        registry.addViewController("/advertisement_not_found").setViewName("advertisement_not_found");
    }
}
