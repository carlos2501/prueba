package com.grupo5.proyecto.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * SecurityConfig contiene la configuración del módulo Spring Security.
 * @author grupo5Eduka (Java Andalucía EOI)
 *
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // Se inyectan los servicios UserDetailsService y AuthenticationSuccessHandler
    @Autowired
    UserDetailsService userDetailsService;
//    @Autowired
//    AuthenticationSuccessHandler authenticationSuccessHandler;


    /**
     * Configura la autenticación
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    /**
     * Configura la autorización
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/estudiante").hasRole("STUDENT")
                .antMatchers("/docente").hasRole("TEACHER")
                .antMatchers("/creaciongrupos").hasAnyRole("TEACHER")
                .antMatchers("/ajustes").hasAnyRole("ADMIN","STUDENT","TEACHER")
                .antMatchers("/juegos").hasAnyRole("ADMIN","STUDENT","TEACHER")
                .anyRequest().permitAll();


        http.formLogin()
                .loginPage("/login")
                .permitAll()
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/index")
                .failureUrl("/login?error=true");

        http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/").deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
        .and()
        .rememberMe().key("uniqueAndSecret");

        // Introducimos esto para poder conocer los usuarios que se encuentran registrados en tiempo real
        http.sessionManagement().maximumSessions(1).sessionRegistry(sessionRegistry());

    }

    /**
     * sessionRegistry y httpSessionEventPublisher son beans que nos ayudan a conocer qué usuarios
     * se encuentran conectados en tiempo real
     * @return
     */

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    public ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {
        return new ServletListenerRegistrationBean<HttpSessionEventPublisher>(new HttpSessionEventPublisher()); }

    /**
     * Método para obtener un hash de la contraseña con Argon2
     * @return hash del password
     */
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new Argon2PasswordEncoder();
    }
}
