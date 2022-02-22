package com.gft.controleStartersGFT.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private ImplementsUserDetailsService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.csrf().disable().authorizeRequests()
			.antMatchers(HttpMethod.GET, "/").permitAll()
			.antMatchers(HttpMethod.GET, "/grupo").hasRole("ADMIN")
			.antMatchers(HttpMethod.GET, "/grupo/novo").hasRole("ADMIN")
			.antMatchers(HttpMethod.POST, "/grupo/novo").hasRole("ADMIN")
			.antMatchers(HttpMethod.GET, "/grupo/editar").hasRole("ADMIN")
			.antMatchers(HttpMethod.GET, "/grupo/excluir").hasRole("ADMIN")
			.antMatchers(HttpMethod.GET, "/modulo").hasRole("ADMIN")
			.antMatchers(HttpMethod.GET, "/modulo/novo").hasRole("ADMIN")
			.antMatchers(HttpMethod.POST, "/modulo/novo").hasRole("ADMIN")
			.antMatchers(HttpMethod.GET, "/modulo/editar").hasRole("ADMIN")
			.antMatchers(HttpMethod.GET, "/modulo/excluir").hasRole("ADMIN")
			.antMatchers(HttpMethod.GET, "/programaStart").hasRole("ADMIN")
			.antMatchers(HttpMethod.GET, "/programaStart/novo").hasRole("ADMIN")
			.antMatchers(HttpMethod.POST, "/programaStart/novo").hasRole("ADMIN")
			.antMatchers(HttpMethod.GET, "/programaStart/editar").hasRole("ADMIN")
			.antMatchers(HttpMethod.GET, "/programaStart/excluir").hasRole("ADMIN")
			.antMatchers(HttpMethod.GET, "/projeto").hasRole("ADMIN")
			.antMatchers(HttpMethod.GET, "/projeto/novo").hasRole("ADMIN")
			.antMatchers(HttpMethod.POST, "/projeto/novo").hasRole("ADMIN")
			.antMatchers(HttpMethod.GET, "/projeto/editar").hasRole("ADMIN")
			.antMatchers(HttpMethod.GET, "/projeto/excluir").hasRole("ADMIN")
			.antMatchers(HttpMethod.GET, "/scrumMaster").hasRole("ADMIN")
			.antMatchers(HttpMethod.GET, "/scrumMaster/novo").hasRole("ADMIN")
			.antMatchers(HttpMethod.POST, "/scrumMaster/novo").hasRole("ADMIN")
			.antMatchers(HttpMethod.GET, "/scrumMaster/editar").hasRole("ADMIN")
			.antMatchers(HttpMethod.GET, "/scrumMaster/excluir").hasRole("ADMIN")
			.antMatchers(HttpMethod.GET, "/starter").hasRole("ADMIN")
			.antMatchers(HttpMethod.GET, "/starter/novo").hasRole("ADMIN")
			.antMatchers(HttpMethod.POST, "/starter/novo").hasRole("ADMIN")
			.antMatchers(HttpMethod.GET, "/starter/editar").hasRole("ADMIN")
			.antMatchers(HttpMethod.GET, "/starter/excluir").hasRole("ADMIN")
			.antMatchers(HttpMethod.GET, "/tecnologia").hasRole("ADMIN")
			.antMatchers(HttpMethod.GET, "/tecnologia/novo").hasRole("ADMIN")
			.antMatchers(HttpMethod.POST, "/tecnologia/novo").hasRole("ADMIN")
			.antMatchers(HttpMethod.GET, "/tecnologia/editar").hasRole("ADMIN")
			.antMatchers(HttpMethod.GET, "/tecnologia/excluir").hasRole("ADMIN")
			.anyRequest().authenticated()
			.and().formLogin().permitAll().and().exceptionHandling().accessDeniedPage("/home")
			.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService)
			.passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception{
		web.ignoring().antMatchers();
	}
}
