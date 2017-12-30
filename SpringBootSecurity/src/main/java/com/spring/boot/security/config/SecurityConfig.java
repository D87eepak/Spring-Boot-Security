package com.spring.boot.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.spring.boot.security.repository.UserRepository;

@EnableJpaRepositories(basePackageClasses=UserRepository.class)
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	UserDetailsService userDetailService; 
	@Override
	public void configure(HttpSecurity httpSecurity) throws Exception{
		
		httpSecurity.headers().cacheControl().disable();

		httpSecurity
		.csrf().disable()
		.authorizeRequests().antMatchers("/")
		.authenticated()
		.anyRequest().permitAll()
		.and().formLogin().permitAll();
	}

	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception{
		/*auth.inMemoryAuthentication().withUser("user")
		.password("user").roles("USER");*/
		auth.userDetailsService(userDetailService)
		.passwordEncoder(passwordEncoder());
		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new PasswordEncoder() {
	        @Override public String encode(CharSequence cs) {
	            return cs.toString();
	        }
	        @Override public boolean matches(CharSequence cs, String string) {
	            return true;
	        }
	    };
	}
}
