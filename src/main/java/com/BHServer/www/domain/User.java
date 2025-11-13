package com.BHServer.www.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username; // 로그인 ID

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)   // ? ordinal 절대 금지!!
    @Column(nullable = false)
    private UserRole role;

    public static User of(String username, String encodedPassword) {
        return User.builder()
                .username(username)
                .password(encodedPassword)
                .role(UserRole.UNAUTHENTIC_USER)
                .build();
    }

    // ==============================
    // UserDetails 구현부
    // ==============================
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 권한은 문자열 하나면 충분하므로 이렇게 매핑
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override public String getUsername() { return username; }
    @Override public String getPassword() { return password; }

    // 계정 상태 ? 기본값은 true
    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}