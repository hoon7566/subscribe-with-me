package me.hoon.subscribewithme.infrastructure.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.hoon.subscribewithme.domain.users.UserService;
import me.hoon.subscribewithme.domain.users.Users;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


@RequiredArgsConstructor
@Slf4j
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final UserService userService;
    private final Environment env;

    public AuthenticationFilter(AuthenticationManager authenticationManager, UserService userService , Environment env){
        super(authenticationManager);
        this.userService=userService;
        this.env = env;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            RequestLogin requestLogin = new ObjectMapper().readValue(request.getInputStream(),RequestLogin.class);


            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(
                            requestLogin.getUserEmail(),
                            requestLogin.getUserPassword(),
                            new ArrayList<>()
                    );
        return getAuthenticationManager().authenticate(token);

        } catch (IOException e) {
            throw new RuntimeException("user not found");
        }
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        log.info("success login===========");
        super.successfulAuthentication(request, response, chain, authResult);
    }

    @Data
    static class RequestLogin{
        private String userEmail;
        private String userPassword;

    }
}
