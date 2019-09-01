package com.epam.training.sportsbetting.web.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@ComponentScan("com.epam.training.sportsbetting.config")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
			.dataSource(dataSource).usersByUsernameQuery("select email,password,enabled from user where email=?")
			.authoritiesByUsernameQuery("select email,role from user where email=?").passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
		        .csrf().disable()
		        .authorizeRequests()
		        	.antMatchers("/rest-api/**").permitAll()
		        	.antMatchers("/resources/**").permitAll()
	                .antMatchers("/login").permitAll()
	                .antMatchers("/", "/**").authenticated()
	                .and()
                
	            .formLogin()
		            .loginPage("/login")
		            .loginProcessingUrl("/perform_login")
		            .defaultSuccessUrl("/home", true) 
			        .failureUrl("/login?error=true")
			        .and()
			        
                .logout()
	                .logoutUrl("/logout")
	                .deleteCookies("JSESSIONID");
    }
}