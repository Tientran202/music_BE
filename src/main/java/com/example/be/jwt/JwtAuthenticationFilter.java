package com.example.be.jwt;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.IOException;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Value("${jwt.secretKey}")
    private String SECRET_KEY;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    //dùng xác thực những lệnh insert update là của ngừuoi dùng nào
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException, java.io.IOException {
        // Lấy token từ header Authorization
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7); // Lấy phần token sau "Bearer "
            try {
                // Kiểm tra xem token có hết hạn hay không
                if (jwtTokenUtil.isTokenExpired(token)) {
                    // Nếu token hết hạn, trả về lỗi 401 và thông báo cho FE về yêu cầu reset
                    // access_token
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.setContentType("application/json");
                    response.getWriter().write(
                            "{\"error\": \"access_token_expired\", \"message\": \"Your access token has expired. Please refresh it.\"}");
                    return; // Dừng ở đây để không tiếp tục chuỗi filter
                }

                // Giải mã token và lấy thông tin người dùng (username)
                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes())) // Sử dụng secret key
                        .build()
                        .parseClaimsJws(token)
                        .getBody();

                String username = claims.getSubject();

                if (username != null) {
                    // Nếu token hợp lệ, thiết lập thông tin người dùng vào context
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            username, null, Collections.emptyList());
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }

            } catch (Exception e) {
                // Xử lý trường hợp lỗi khi giải mã token (kể cả trường hợp token hết hạn)
                System.out.println("Invalid or expired token");
            }
        }
        // Tiếp tục xử lý request trong filter chain
        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7); // Tách ra phần token
        }
        return null;
    }

    private boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes())).build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            // Token không hợp lệ
            return false;
        }
    }
}
