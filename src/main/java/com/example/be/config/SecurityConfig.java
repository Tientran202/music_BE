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
                        .requestMatchers(HttpMethod.GET, "/api/auth/getidtest").permitAll()
                        .requestMatchers(HttpMethod.PATCH, "/api/auth/updatePass").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/music/img2/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/music/getIndexMusicArtist/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/music/getIndexMusicByMusicIdForAdmin/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/genre/getAllGenre").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/admin/reportedUser").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/admin/reportedMusic").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/admin/requestToBecomeArtist").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/admin/hiddenUser").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/admin/findAllArtist").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/admin/findAllUser").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/admin/hiddenMusic").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/admin/getAllHiddenMusic").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/admin/hiddenReportMusic").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/admin/acceptRequestArtist").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/admin/cancelHiddenMusic").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/genre/getGenreAndMusic/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/music/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/music/searchMusic").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/music/artist-songs/{artistId}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/music/downloadAudioMusicByMusicId/{musicId}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/music/getMusicByPlaylistId").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/playlist/searchPlaylist").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/playlist/createPlaylist").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/playlist/getPlaylistByArtistIdLimit").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/playlist/getPlaylistByArtistId").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/playlist/getPlaylistByUserIdLimit").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/playlist/getPlaylistByUserId").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/playlist/getRecentlyMusic").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/playlist/getPlaylistByUserIdAddMusic").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/playlist/getIndexPlaylist/{id}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/user/popularArtist").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/user/searchArtist").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/user/getIndexArtist").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/user/getUserIdByAccountId").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/user/getFlowingByUserId").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/user/getIndexUser").permitAll()
                        .requestMatchers(HttpMethod.PATCH, "/api/user/requestBecomeArtist").permitAll()
                        .requestMatchers(HttpMethod.PATCH, "/api/user/updateProfile").permitAll()
                        .requestMatchers(HttpMethod.PATCH, "/api/user/updateProfileUser").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/user/getNumberOfArtist").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/user/getNumberOfUser").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/music/getNewMusic").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/music/upload-music").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/music/getMusicByAlbumId").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/music/getSuggestedMusicResponse").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/music/getSuggestedMusicForAdminResponse").permitAll()
                        .requestMatchers(HttpMethod.PATCH, "/api/music/updateAlbumIdforMusic").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/music/testupload").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/music/musicForAlbumByArtist/{artistId}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/music/getMusicByArtistId").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/music/getMusicByArtistIdForCreateAlbum").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/music/getAllMusicByArtistId").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/music/getAllMusicCByArtistId").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/music/getAllMusicUnconfirmed").permitAll()
                        .requestMatchers(HttpMethod.PATCH, "/api/music/confirmationMusicById").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/music/addToPlaylist").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/music/reportMusic").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/music/getNumberOfMusic").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/album/getTopAlbum").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/album/getIndexAlbum/{id}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/album/createAlbum").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/album/getSearchAlbum").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/album/getAlbumByArtistIdlimit").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/album/getAlbumByArtistId").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/album/getAllAlbum").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/album/getNumberOfAlbum").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/auth/refresh-token").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/listening-history/recently-music").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/follow/followArtist").permitAll()
                        .requestMatchers(HttpMethod.PATCH, "/api/follow/unFlowArtist").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/follow/getFollow").permitAll()
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
        configuration.setAllowedMethods(Arrays.asList("GET", "PATCH", "POST", "PUT", "DELETE", "OPTIONS"));
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
