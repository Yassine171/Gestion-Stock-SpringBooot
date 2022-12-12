package gestion_stock.gestionStockapp.configuration;

import gestion_stock.gestionStockapp.service.authentication.ApplicationUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@EnableWebSecurity
@Configuration
public class WebSecurityConfiguration {

    @Autowired
    private ApplicationUserDetailService applicationUserDetailsService;
    @Autowired
    private ApplicationRequestFilter applicationRequestFilter;

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(applicationUserDetailsService).passwordEncoder(passwordEncoder());

    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {


                 http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/**/authenticate",
                        "/**/utilisateurs/create",
                        "/**/entreprises/**",
                        "/v2/api-docs",
                        "/swagger-resources",
                        "/swagger-resources/**",
                        "/swagger-ui.html",
                        "/webjars/**",
                        "/configuration/ui",
                        "/configuration/security",
                        "/swagger-ui/**",
                        "/swagger-ui/index.html",
                        "/v3/api-docs/**")
                .permitAll()
          .anyRequest().authenticated().// tous les autres url doit avoir un token valide
                and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // sans etat
        // par defaut une api restfull on garde pas le status client
        ;
        http.addFilterBefore(applicationRequestFilter, UsernamePasswordAuthenticationFilter.class);
        //car c'est une application local
        //  super.configure(http);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    //NoOpPasswordEncoder.getInstance() ou bien "{noop}" pour dire a spring boot que le mot de passe n'est pas crypté
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    public static BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    @Bean
    public HandlerMappingIntrospector mvcHandlerMappingIntrospector() {
        return new HandlerMappingIntrospector();
    }

}
