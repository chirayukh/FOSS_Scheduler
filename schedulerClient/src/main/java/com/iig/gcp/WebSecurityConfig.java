package com.iig.gcp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Value( "${parent.front.micro.services}" )
	private String parent_micro_services;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable();

		http.authorizeRequests().antMatchers("/", "/login", "/error").permitAll()

		.antMatchers("/admin/*").hasAnyAuthority("ADMIN")
		.antMatchers("/schedular/*").hasAnyAuthority("ADMIN")
		.antMatchers("/scheduler/*").hasAnyAuthority("ADMIN")
		.antMatchers("/schedule/*").hasAnyAuthority("ADMIN")

		.and().exceptionHandling().accessDeniedPage("/accessDenied").and().formLogin().loginPage("http://"+parent_micro_services);
		
		http.logout().logoutUrl("/logout").logoutSuccessUrl("http://"+parent_micro_services).invalidateHttpSession(true);
		}

	/*
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder)
	 */
	/**
	 * @param builder
	 */
	@Override
	public void configure(AuthenticationManagerBuilder builder) throws Exception {
		builder.authenticationProvider(new CustomAuthenticationProvider());
	}

	/*
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.WebSecurity)
	 */
	/**
	 * @param web
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/assets/**");
	}

	/*
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#authenticationManagerBean()
	 */
	/**
	 * @return AuthenticationManager
	 */
	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}