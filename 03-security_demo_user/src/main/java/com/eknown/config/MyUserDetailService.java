package com.eknown.config;

import com.eknown.model.beans.entity.User;
import com.eknown.model.dao.UserDAO;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;

/**
 * 自定义用户角色和权限绑定配置
 *
 * 一旦@Configuration生效，这个类就会自动替换默认的UserDetailService
 * @author eknown
 * @version 1.0
 * @since 2019/5/30 10:30
 */
@Configuration
public class MyUserDetailService implements UserDetailsService {

    @Resource
    private UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userDAO.findByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }

        Collection<GrantedAuthority> authorities = new ArrayList<>(); // 暂时不添加权限

        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), authorities);
    }
}
