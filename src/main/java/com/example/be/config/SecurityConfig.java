package com.example.be.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.be.jwt.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .build();
    }

    // @Bean
    // public JwtAuthenticationFilter jwtAuthenticationFilter() {
    // return new JwtAuthenticationFilter(); // Filter sẽ được Spring quản lý tại
    // đây
    // }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @SuppressWarnings("removal")
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors().and().authorizeHttpRequests(
                request -> request.requestMatchers(HttpMethod.POST, "/api/auth/register").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/auth/registration-confirmation").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/auth/img/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/music/img2/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/music/getIndexMusicArtist/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/genre/getAllGenre").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/admin/reportedUser").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/admin/reportedMusic").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/admin/hiddenMusic").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/admin/requestToBecomeArtist").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/admin/hiddenUser").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/admin/findAllArtist").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/admin/findAllUser").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/genre/getGenreAndMusic/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/music/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/music/searchMusic").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/user/popularArtist").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/user/searchArtist").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/music/getNewMusic").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/album/getTopAlbum").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/auth/refresh-token").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/listening-history/recently-music").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/user/profile").authenticated()
                        .anyRequest().authenticated())
                // .addFilterBefore(new JwtAuthenticationFilter(),
                // UsernamePasswordAuthenticationFilter.class);
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); // Sử dụng bean
                                                                                                       // jwtAuthenticationFilter

        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        return httpSecurity.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:8081")); // Địa chỉ FE của bạn
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser(User.withUsername("user")
                        .password("{noop}password") // Dùng password không mã hóa (chỉ dùng cho ví dụ)
                        .roles("USER"));
    }

}
