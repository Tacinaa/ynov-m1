package org.example.todolistback.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.todolistback.model.User;
import org.example.todolistback.repository.UserRepository;
import org.example.todolistback.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getServletPath();
        if (path.startsWith("/auth/login") || path.startsWith("/auth/register")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = extractToken(request);
        System.out.println("📦 Authorization header: " + request.getHeader("Authorization"));

        if (token != null && jwtUtil.validateToken(token)) {
            System.out.println("✅ Token JWT validé");

            String username = jwtUtil.extractUsername(token);
            System.out.println("🔓 Utilisateur extrait du token : " + username);

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            System.out.println("✅ UserDetails chargé : " + userDetails.getUsername());
            System.out.println("✅ Authorities : " + userDetails.getAuthorities());

            UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(auth);
            System.out.println("🔐 Authentification injectée dans SecurityContextHolder");
        }

        Authentication currentAuth = SecurityContextHolder.getContext().getAuthentication();

        filterChain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request) {
        String bearer = request.getHeader("Authorization");
        if (bearer != null && bearer.startsWith("Bearer ")) {
            return bearer.substring(7);
        }
        return null;
    }
}