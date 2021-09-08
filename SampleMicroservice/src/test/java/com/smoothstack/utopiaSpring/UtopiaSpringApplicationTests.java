
package com.smoothstack.utopiaSpring;

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

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class UtopiaSpringApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	int randomServerPort;

	@Test
	public void testJwtUnauthorizedGet() throws URISyntaxException {
		final String baseUrl = "http://localhost:" + randomServerPort + "/utopia/test";
		URI uri = new URI(baseUrl);

		HttpHeaders headers = new HttpHeaders();

		ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity<>(headers),
				String.class);

		Assert.assertEquals(HttpStatus.FORBIDDEN, result.getStatusCode());
	}

	@Test
	public void testJwtAuthorizedGet() throws URISyntaxException {
		final String baseUrl = "http://localhost:" + randomServerPort + "/utopia/test";
		URI uri = new URI(baseUrl);

		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization",
				"Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTYzMDgxMjcxNX0.Y0kHwrGeQ75TUCArJhtolOiILXY_bGFQqOi5ocdQycm1t5X5ZzBuMej-vxoi4mQMlBlVICLN1Nyi81GZhL7EZg");

		ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity<>(headers),
				String.class);

		Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
	}
	
}
