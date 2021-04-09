package javaee.hw8.demo.config;

import javaee.hw8.demo.repository.ClientRepository;
import javaee.hw8.demo.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private final ClientRepository clientRepository;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new ClientService(clientRepository));
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        http
                .cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/books/liked/**").hasAnyRole("client")
                .antMatchers("/books/create").hasAnyRole("admin")
                .antMatchers("/","/books/**", "/authors/**", "/registration").permitAll()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .permitAll()
                .and()
                .logout().permitAll();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService(){
        return new ClientService(clientRepository);
    }

}
