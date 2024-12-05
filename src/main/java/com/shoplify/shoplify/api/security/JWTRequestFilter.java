package com.shoplify.shoplify.api.security;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.shoplify.shoplify.models.LocalUser;
import com.shoplify.shoplify.models.dao.UserDAO;
import com.shoplify.shoplify.service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@Component
public class JWTRequestFilter extends OncePerRequestFilter {

    private JWTService jwtService;
    private UserDAO userDAO;

    public JWTRequestFilter(JWTService jwtService, UserDAO userDAO) {
        this.userDAO = userDAO;
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        if(authorization != null && authorization.startsWith("Bearer ")) {
            String token = authorization.substring(7);
            try{
                String username = jwtService.getUsername(token);
                Optional<LocalUser> opUser = userDAO.findByUsername(username);
                if(opUser.isPresent()) {
                    LocalUser localUser = opUser.get();
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            localUser,null, new ArrayList<>()
                    );
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }


            }catch(JWTDecodeException ex){}
        }
        filterChain.doFilter(request, response);
    }
}
