package com.example.audiobook_backend.service.impl.utils;


import com.example.audiobook_backend.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author cly<br />
 * @date: 2023/12/16 11:25<br/>
 * @description: <br/>
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    //这个返回值根据前端字段的名称变化而变化
    @Override
    public String getUsername() {
        return user.getEmail();
    }

    //账户是否未失效
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //是否不被锁定
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //证书是否未过期
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //是否可用
    @Override
    public boolean isEnabled() {
        return true;
    }
}
