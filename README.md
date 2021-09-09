# ss-utopia-auth

Auth - Authentication server

SampleMicroservice - Mock REST API with JWT security

---

### Backend structure:

![backend structure](https://user-images.githubusercontent.com/87076278/132757004-6ece1fee-49f0-4b27-8210-9d946f25df22.png)


### JWT Security:

Login:
1. User submits login credentials to the Auth server.
2. Auth server validates credentials against database.
3. Auth server genereates a JWT & sends it back to the front end via authorization header.
4. JWT is saved in local storage in the browser.

Authorization:
1. User requests a web page with secure resources.
2. JWT is sent to backend via authorization header.
3. Gateway routes the request to the appropriate microservice.
4. Microservice validates the JWT's signature & the user's authorities.
5. Microservice either returns 200 OK, or 403 Forbidden.


---

### To secure a microservice with JWT authorization, add the following changes:

- Using SampleMicroservice as an example:

1. Enable SSL
```
	application.properties:
		copy server.ssl lines

	copy resources/bootsecurity.p12 (self signed certificate)
```

2. Add pom.xml dependencies for security:
```
        <!-- Security -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>
        <dependency>
            <groupId>com.auth0</groupId>
            <artifactId>java-jwt</artifactId>
            <version>3.4.1</version>
        </dependency>
```

3. Copy classes from packages:
```
	model:
		User
	service:
		DbInit
		UserRepository
	security:
		UserPrincipal
		UserPrincipalDetailsService
		JwtProperties
		JwtAuthorizationFilter
		SecurityConfiguration
```

4. Configure access rules via antMatchers in SecurityConfiguration.java

---

### Testing via Postman:


1. Generate JWT:
```
	POST https://localhost:8443/login

	send credentials via body > raw:
	{
	    "username":"admin",
	    "password":"admin123"
	}

	receive JWT via authorization header
	copy it without "Bearer" prefix
```

2. Use JWT to access endpoint:
```
	GET https://localhost:8443/utopia/test

	without token - returns 403 forbidden

	with token - returns 200 OK & page content

```

