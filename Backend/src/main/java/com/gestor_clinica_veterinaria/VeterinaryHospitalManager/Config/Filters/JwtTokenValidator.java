package com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Config.Filters;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.gestor_clinica_veterinaria.VeterinaryHospitalManager.Config.Jwt.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;

@Slf4j
public class JwtTokenValidator extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;

    public JwtTokenValidator(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        String jwtToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        log.debug("Received request with Authorization header: {}", jwtToken);

        if (jwtToken != null && jwtToken.startsWith("Bearer ")) {
            jwtToken = jwtToken.substring(7);
            log.debug("Extracted JWT token: {}", jwtToken);

            try {
                DecodedJWT decodedJWT = jwtUtils.validateToken(jwtToken);
                log.debug("JWT token validated successfully");

                String username = jwtUtils.extractUsername(decodedJWT);
                log.debug("Extracted username from token: {}", username);
                String stringAuthorities = jwtUtils.getSpecificClaim(decodedJWT, "authorities").asString();
                log.debug("Extracted authorities string from token: {}", stringAuthorities);

                Collection<? extends GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(stringAuthorities);
                log.debug("Parsed authorities from string: {}", authorities);

                SecurityContext securityContext = SecurityContextHolder.getContext();
                Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);
                securityContext.setAuthentication(authentication);
                SecurityContextHolder.setContext(securityContext);
                log.debug("Set authentication in SecurityContext");
            } catch (Exception e) {
                log.error("Error validating JWT token", e);
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        } else {
            log.debug("No JWT token found in request headers");
        }

        filterChain.doFilter(request, response);
    }
}
