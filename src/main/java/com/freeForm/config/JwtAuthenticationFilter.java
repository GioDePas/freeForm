package com.freeForm.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private static final String AUTHORIZATION = "Authorization";
    private static final String REFRESH_TOKEN = "Refresh-Token";
    private final JwtService jWtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain
    ) throws ServletException, IOException {

        final String authorizationHeader = request.getHeader(AUTHORIZATION);
        String jwt = null;
        String userEmail = null;
        String refreshToken = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            if (!jWtService.isTokenExpired(jwt)) {
                userEmail = jWtService.extractUsername(jwt);
            } else {
                refreshToken = request.getHeader(REFRESH_TOKEN);
                userEmail = jWtService.extractUsername(refreshToken);
            }
        }

        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);

            if (jWtService.isTokenValid(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            } else if (refreshToken != null) {

                Optional<UsernamePasswordAuthenticationToken> authenticationToken = jWtService
                        .authenticationRefreshToken(refreshToken)
                        .map(token -> (UsernamePasswordAuthenticationToken) token);

                authenticationToken.ifPresent(token -> {
                    SecurityContextHolder.getContext().setAuthentication(token);
                    response.setHeader(AUTHORIZATION, "Bearer " + jWtService.generateToken(userDetails));
                });
            }
        }

        filterChain.doFilter(request, response);
    }
}
