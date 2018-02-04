package com.example.demo;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	  @Override
	  public void configure(HttpSecurity http) throws Exception {
	      http.authorizeRequests()
	          .antMatchers("/demo/**").hasRole("USER")//USER role can access /users/**
//	          .antMatchers("/demo/**").hasRole("ADMIN")//ADMIN role can access /admin/**
	          .anyRequest().authenticated()//any other request just need authentication
	          .and()
	          .formLogin();//enable form login

	  }

	  @Override
	  public void configure(AuthenticationManagerBuilder builder)
	          throws Exception {
	      builder.inMemoryAuthentication()
//	             .withUser("admin").password("123").roles("ADMIN")
//	             .and()
	             .withUser("user").password("123").roles("USER");
	  }

}
