package com.odk.assistantticket.config;



import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.http.HttpMethod.*;


@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserDetailsService userDetailsService;

//JwtFilter jwtFilter
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
                httpSecurity
                        .authorizeHttpRequests(auth ->{
                            auth.requestMatchers(POST,"/inscription", "/connexion").permitAll();
                            auth.requestMatchers(POST,"/categorie").hasRole("ADMIN");
                            auth.requestMatchers(POST,"/ticket").hasRole("ADMIN");
                            auth.anyRequest().authenticated();
                                }
                        )
                        .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                        .csrf(AbstractHttpConfigurer::disable)
                        .httpBasic(httpBasic -> httpBasic.realmName("AssistantTicket")
                        );

                return httpSecurity.build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

   @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder);
        return daoAuthenticationProvider;
    }


}








/*public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(authorize ->
                            authorize
                                    .requestMatchers(POST, "/inscription").permitAll()
                                    .requestMatchers(POST, "/connexion").permitAll()
                                    .requestMatchers(POST,"/categorie").hasRole("ADMINISTRATEUR")
//                                //.requestMatchers(POST,"/priorite").permitAll()
//                                .requestMatchers(POST,"/ticket").permitAll()
//                                .requestMatchers(POST,"/notification").permitAll()
//                                .requestMatchers(GET,"/notification").permitAll()
                                    .requestMatchers(POST,"/basedeconnaissance").hasRole("ADMINISTRATEUR")
//                                .requestMatchers(GET,"/ticket").permitAll()
//                                .requestMatchers(GET,"/priorite").permitAll()
//                                .requestMatchers(GET, "/categorie/**").permitAll()
                                    .anyRequest().authenticated()
            )
            .sessionManagement(httpSecuritySessionManagementConfigurer ->
                    httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            //.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
            .httpBasic(Customizer.withDefaults());
    return httpSecurity.build();
}
*/







