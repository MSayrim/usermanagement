package com.example.usermanagement.entity;

import com.example.usermanagement.enums.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@Table
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity implements Serializable, UserDetails {

    private String name;
    private String surname;
    private String username;
    private String telephone;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        GrantedAuthority grantedAuthority = new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return role.toString();
            }
        };
        List auths = new ArrayList();
        auths.add(grantedAuthority);
        return auths;
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
        return this.getActive();
    }
}
