
package com.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import com.common.login.service.MemberService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private MemberService memberService;

	@Override
	public void configure(WebSecurity web) throws Exception {
		// 해당요청 경로에 따라서는 인증제외
		web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
	}

	// 암호화

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override 
	protected void configure(HttpSecurity http) throws Exception {
 
		//웹경로에 따른 권한 지정 
		http.authorizeRequests()
			.antMatchers("/member/**").authenticated()
			.antMatchers("/admin/**").authenticated()
			.antMatchers("/**").permitAll();
 
		//로그인 페이지 설정 
		http.formLogin().loginPage("/login").defaultSuccessUrl("/").permitAll();
 
		//로그아웃 페이지 설정 
		http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")) .logoutSuccessUrl("/login").invalidateHttpSession(true);
 
		//권한 없는 사용자가 접근시에 이동경로  
		http.exceptionHandling() .accessDeniedPage("/denied");
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
	}

}