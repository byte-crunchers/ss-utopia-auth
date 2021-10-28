package com.smoothstack.Auth;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.smoothstack.Auth.model.LoginViewModel;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class AuthApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	int randomServerPort;

	@Test
	public void testInvalidLogin() throws URISyntaxException {
		final String baseUrl = "http://localhost:" + randomServerPort + "/login";
		URI uri = new URI(baseUrl);

		HttpHeaders headers = new HttpHeaders();

		ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.POST, new HttpEntity<>(headers),
				String.class);

		Assert.assertEquals(HttpStatus.UNAUTHORIZED, result.getStatusCode());
	}

	@Test
	public void testValidLogin() throws URISyntaxException {
		final String baseUrl = "http://localhost:" + randomServerPort + "/login";
		URI uri = new URI(baseUrl);

		LoginViewModel credentials = new LoginViewModel("dan", "dan123", "admin");
		HttpHeaders headers = new HttpHeaders();
		headers.set("X-COM-PERSIST", "true");
		HttpEntity<LoginViewModel> request = new HttpEntity<>(credentials, headers);

		ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);

		Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
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
	 * 
	 * 
	 * 
	 */
}
