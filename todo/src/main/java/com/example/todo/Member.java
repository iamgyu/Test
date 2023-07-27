package com.example.todo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Table(name = "members")
@Entity
@NoArgsConstructor
public class Member implements UserDetails{
    @Id // primary key 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT 설정(id값이 null일 경우 자동 생성)
    @Column(name = "pk")    // 컬럼 지정
    private int pk;

    @NotNull
    @Column(unique = true, name = "id")
    private String id;

    @NotNull
    @Column(name = "password")
    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return id;
    }

    @Override
    public String getPassword() {
        return password;
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
