package com.dev.oauth;

import java.util.Collections;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.webjars.WebJarVersionLocator;

@SpringBootApplication
@RestController
public class OauthApplication {

	@GetMapping("/user")
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
        return Collections.singletonMap("name", principal.getAttribute("name"));
    }

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		// @formatter:off
		http
			.authorizeHttpRequests(auth -> auth
				.requestMatchers("/", "/index.html", "/error", "/webjars/**").permitAll()
				.anyRequest().authenticated()
			)
			.exceptionHandling(e -> e
				.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
			)
			.csrf(csrf -> csrf
				.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
			)
			.logout(logout -> logout
				.logoutUrl("/logout").permitAll()
				.logoutSuccessUrl("/").permitAll()
			)
			.oauth2Login(oauth -> {}
			);
		// @formatter:on
		return http.build();
	}

	public static void main(String[] args) {
		//String fullPath = new WebJarVersionLocator().fullPath("js-cookie", "js.cookie.js");
		//System.out.printf("fullPath of js-cookie:%s%n", fullPath);
		//fullPath of js-cookie:META-INF/resources/webjars/js-cookie/3.0.1/js.cookie.js
		SpringApplication.run(OauthApplication.class, args);
	}

}
