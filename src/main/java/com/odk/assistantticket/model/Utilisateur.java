package com.odk.assistantticket.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Builder
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
//
public class Utilisateur implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String password;
    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE})
    private Role role;


   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
       return (Collections.singletonList(new SimpleGrantedAuthority("ROLE_"+this.role.getLibelle())));
   }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
