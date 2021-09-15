package com.smoothstack.Auth.security;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smoothstack.Auth.model.LoginViewModel;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private AuthenticationManager authenticationManager;

	public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	/*
	 * Trigger when we issue POST request to /login We also need to pass in
	 * {"username":"dan", "password":"dan123"} in the request body
	 */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		// Grab credentials and map them to login viewmodel
		LoginViewModel credentials = null;
		try {
			String input = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
			System.out.println("Received login attempt: " + input);

			ObjectMapper mapper = new ObjectMapper();
			credentials = mapper.readValue(input, LoginViewModel.class);
		} catch (IOException e) {
//			e.printStackTrace();
			System.out.println("Could not read credentials.");
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return null;
		}

		// Create login token
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				credentials.getUsername(), credentials.getPassword(), new ArrayList<>());

		// Authenticate user
		Authentication auth = null;
		try {
			auth = authenticationManager.authenticate(authenticationToken);
			response.addHeader("Access-Control-Expose-Headers", "Authorization");
		} catch (Exception ex) {
			System.out.println("Invalid credentials.");
			// return 401 Unauthorized status
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}

		return auth;
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		// Grab principal
		UserPrincipal principal = (UserPrincipal) authResult.getPrincipal();

		// Create JWT Token
		String token = JWT.create().withSubject(principal.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
				.sign(HMAC512(JwtProperties.SECRET.getBytes()));

		System.out.println("JWT: " + token);

		// Add token in response
		response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + token);
	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
}
