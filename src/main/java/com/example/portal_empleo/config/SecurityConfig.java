package com.example.portal_empleo.config;


import com.example.portal_empleo.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {

            http
                    .csrf().disable()
                    .authorizeRequests().antMatchers("/**.js","/**.css","/**.ico","/candidate/register", "/company/register").permitAll()
                    .antMatchers("/candidate/**").hasAuthority("CANDIDATE")
                    .antMatchers("/company/**").hasAuthority("COMPANY")
                    .anyRequest().authenticated()
                    .and()
                    .formLogin().loginPage("/login").loginProcessingUrl("/logincheck").defaultSuccessUrl("/inicio").usernameParameter("username").passwordParameter("password")
                    .permitAll()
                    .and()
                    .logout() .invalidateHttpSession(true)
                    .clearAuthentication(true) .permitAll();
        }

        @Autowired
        public UsuarioService usuarioService;

        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
            auth.userDetailsService(usuarioService).passwordEncoder(new BCryptPasswordEncoder());
        }



}
