//package com.example.hw8.config;
//
//import com.example.hw8.repository.ClientRepository;
//import com.example.hw8.service.ClientService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.factory.PasswordEncoderFactories;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//
//@Configuration
//@RequiredArgsConstructor
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private final ClientRepository clientRepository;
//
//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(new ClientService(clientRepository));
//    }
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//
//        http
//                .authorizeRequests()
//                .antMatchers("/books").hasAnyRole("client")
//                .antMatchers("/", "/authors/**", "/registration").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .defaultSuccessUrl("/")
//                .permitAll()
//                .and()
//                .logout().permitAll();
//    }
//
//    @Bean
//    @Override
//    protected  UserDetailsService userDetailsService(){
//        return new ClientService(clientRepository);
//    }
//
////    @Autowired
////    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
////
////        auth.inMemoryAuthentication()
////                .withUser("client").password("client").roles("USER")
////                .and()
////                .withUser("admin").password("password").roles("ADMIN");
////    }
//
//}