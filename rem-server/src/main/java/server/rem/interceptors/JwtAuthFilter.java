package server.rem.interceptors;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.*;
import lombok.NonNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import server.rem.enums.JWTAlgorithm;
import server.rem.repositories.UserRepository;
import server.rem.utils.JWT;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    @Value("${jwt.secret}")
    private String secret;
    
    private final HandlerExceptionResolver resolver;
    private final UserRepository userRepository;

    @Autowired
    public JwtAuthFilter(@Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver, UserRepository userRepository) {
        this.resolver = resolver;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws IOException {
        try {
            String authHeader = request.getHeader("Authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }
            JWT jwt = new JWT(secret, JWTAlgorithm.HS256);
            String token = authHeader.substring(7);
            Map<String, Object> decoded = jwt.verify(token);
            String userId = decoded.get("userId").toString();
            if (userId != null) {
                request.setAttribute("userId", userId);
                userRepository.findById(userId).ifPresent(user -> {
                    Authentication auth = new UsernamePasswordAuthenticationToken(user, null, List.of());
                    SecurityContextHolder.getContext().setAuthentication(auth);
                });
            }
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            resolver.resolveException(request, response, null, e);
        }
    }
}