package co.edu.glm.mahubforo.infrastructure.security;

import co.edu.glm.mahubforo.repository.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    private TokenService tokenService;
    private IUserRepository userRepository;

    @Autowired
    public SecurityFilter(IUserRepository userRepository, TokenService tokenService) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {


        var authHeader = request.getHeader("Authorization");
        if (authHeader != null) {
            var token = authHeader.replace("Bearer ", "");
            try {
                var email = tokenService.getSubject(token);
                if (email != null) {
                    var user = userRepository.findByCorreoElectronico(email);
                    if (user.isPresent()) {
                        var authentication = new UsernamePasswordAuthenticationToken(
                                user.get(), null, user.get().getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());;
            }
        }
        filterChain.doFilter(request, response);
    }
}
