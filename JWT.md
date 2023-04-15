**config/WebSecurityConfig.java**
--------------------------
securityFilterChain() function: [This function is used to create a SecurityFilterChain bean, which is a collection of Servlet Filters that are responsible for implementing various aspects of Spring Security](https://docs.spring.io/spring-security/reference/servlet/configuration/java.html). [The SecurityFilterChain bean is registered with the springSecurityFilterChain filter, which is the main entry point for Spring Security](https://docs.spring.io/spring-security/reference/servlet/configuration/java.html).

[The securityFilterChain function takes an HttpSecurity parameter, which is a builder for configuring web-based security for specific HTTP requests](https://docs.spring.io/spring-security/reference/servlet/configuration/java.html). [By using this parameter, you can customize the security behavior for different URLs, authentication mechanisms, authorization rules, CSRF protection, and more](https://docs.spring.io/spring-security/reference/servlet/configuration/java.html).

In this code, you are using the securityFilterChain function to configure the following security features:

-   Disable CSRF protection (not recommended for production)
-   Allow all requests to /hello without authentication
-   Allow all other requests with any authentication
-   Add your custom jwtFilter before the UsernamePasswordAuthenticationFilter class

**service/JWTFilter.java**
------------------
The `doFilterInternal` method is a protected method in the `OncePerRequestFilter` class, which is an abstract class in Java that provides a base implementation for creating servlet filters that need to be invoked only once per request. This method is called by the servlet container every time a request passes through the filter chain.

In the context of the provided code snippet, the `doFilterInternal` method is responsible for implementing the logic of the JWT filter. It performs the following steps:

1.  Retrieves the "Authorization" header from the incoming HTTP request using `request.getHeader("Authorization")`.

2.  Extracts the JWT token from the "Authorization" header by removing the "Bearer " prefix, if present, using `authHeader.substring(7)`.

3.  Calls the `jwtService.extractUsername(token)` method to extract the username from the JWT token.

4.  Based on the path of the request (`path` variable), it checks if it starts with "/hello2" or not using `path.startsWith("/hello2")`.

5.  If the request path starts with "/hello2", it checks if the extracted username is equal to "truc". If yes, it sets an attribute "pass" with the value "testing" in the request using `request.setAttribute("pass", "testing")`, and then calls `filterChain.doFilter(request, response)` to allow the request to continue processing.

6.  If the extracted username is not equal to "truc", it throws an `UnsupportedOperationException` with the message "Not supported yet.".

7.  If the request path does not start with "/hello2", it checks if the extracted username is not null. If yes, it calls `filterChain.doFilter(request, response)` to allow the request to continue processing.

8.  If the extracted username is null, it throws an `UnsupportedOperationException` with the message "Not supported yet.".

**service/JWTService.java**
-------------------
1.  The `jwtService` class is annotated with `@Component`, indicating that it is a Spring bean and can be automatically scanned and instantiated by the Spring container.

2.  The `jwtService` class contains various methods for generating, validating, and extracting information from JWTs:

-   `extractUsername(String token)`: This method takes a JWT token as input and uses the `extractClaim` method with a `Claims::getSubject` function reference to extract and return the subject (i.e., username) from the JWT's claims.

-   `extractExpiration(String token)`: This method takes a JWT token as input and uses the `extractClaim` method with a `Claims::getExpiration` function reference to extract and return the expiration date from the JWT's claims.

-   `extractClaim(String token, Function<Claims, T> claimsResolver)`: This method takes a JWT token and a `Function` that maps from `Claims` to a desired type `T`. It uses the `extractAllClaims` method to parse the JWT token and extract the claims, and then applies the given function to the claims to return the desired value.

-   `extractAllClaims(String token)`: This private method takes a JWT token as input, uses the `parserBuilder` to build a JWT parser with a signing key obtained from the `getSignKey` method, and then parses the JWT token to obtain and return the claims (i.e., payload) of the JWT.

-   `isTokenExpired(String token)`: This private method takes a JWT token as input and uses the `extractExpiration` method to obtain the expiration date of the token. It then compares the expiration date with the current date to determine if the token has expired, and returns a boolean value accordingly.

-   `validateToken(String token, UserDetails userDetails)`: This method takes a JWT token and a `UserDetails` object (presumably representing the authenticated user) as input. It uses the `extractUsername` and `isTokenExpired` methods to extract the username from the token and check if the token has expired, respectively. It then compares the extracted username with the username in the `UserDetails` object and returns a boolean value indicating if the token is valid.

-   `generateToken(String userName)`: This method takes a username as input and generates a new JWT token by calling the `createToken` method with an empty map of claims and the provided username. It then returns the generated JWT token.

-   `createToken(Map<String, Object> claims, String userName)`: This private method takes a map of claims and a username as input, and uses the `Jwts.builder` to construct a new JWT by setting the claims, subject (i.e., username), issued-at date, expiration date, and signing key obtained from the `getSignKey` method. It then returns the generated JWT token.

-   `getSignKey()`: This private method decodes the `SECRET` string, which presumably contains a Base64-encoded secret key for signing JWTs, using the `Decoders.BASE64.decode` method. It then uses the `Keys.hmacShaKeyFor` method to convert the decoded key into a `Key` object suitable for use with the HMAC-SHA256 signing algorithm, and returns the `Key` object.
