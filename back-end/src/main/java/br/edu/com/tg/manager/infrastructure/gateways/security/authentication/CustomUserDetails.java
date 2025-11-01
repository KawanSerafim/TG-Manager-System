package br.edu.com.tg.manager.infrastructure.gateways.security.authentication;

import br.edu.com.tg.manager.core.domain.enums.UserAccountStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {
    private final String username;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;
    private final UserAccountStatus accountStatus;

    public CustomUserDetails(
            String username,
            String password,
            Collection<? extends GrantedAuthority> authorities,
            UserAccountStatus accountStatus
    ) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.accountStatus = accountStatus;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities != null ? authorities : Collections.emptySet();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        if(this.accountStatus == null){
            return false;
        }

        return this.accountStatus == UserAccountStatus.ACTIVE;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        if(this.accountStatus == null){
            return false;
        }

        return this.accountStatus == UserAccountStatus.ACTIVE;
    }
}