// package com.example.be.security;

// import java.util.Date;

// import org.springframework.security.core.Authentication;
// import org.springframework.stereotype.Component;

// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.SignatureAlgorithm;

// @Component
// public class JwtTokenProvider {
//     private final String secretKey = "12345"; // Khóa bí mật JWT

//     public String generateToken(Authentication authentication) {
//         String username = authentication.getName(); // Lấy tên người dùng từ Authentication
//         return Jwts.builder()
//                 .setSubject(username) // Truyền chuỗi tên người dùng vào setSubject
//                 .setIssuedAt(new Date())
//                 .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 ngày
//                 .signWith(SignatureAlgorithm.HS512, secretKey)
//                 .compact();
//     }

//     public String getUsernameFromToken(String token) {
//         return Jwts.parser()
//                 .setSigningKey(secretKey)
//                 .parseClaimsJws(token)
//                 .getBody()
//                 .getSubject();
//     }
// }
