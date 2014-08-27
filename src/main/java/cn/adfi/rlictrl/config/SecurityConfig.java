package cn.adfi.rlictrl.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired // set in-memory authentication 
	public void configureGlobal(UserDetailsService userDetailsService , AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(userDetailsService);
	}
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests() //set resources permitted.
				.antMatchers("/header*").permitAll()
				.antMatchers("/footer*").permitAll()
				.antMatchers("/index*").permitAll()
				.antMatchers("/partials/**").permitAll()
				.antMatchers("/lib/**").permitAll()
				.antMatchers("/js/**").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()  //set login page url.
				.loginPage("/login")
				.defaultSuccessUrl("/")
				.permitAll()
				.and()
			.rememberMe().
				
				and()
			.logout()     //set log out perimitted for all user.
				.permitAll();
	}
}