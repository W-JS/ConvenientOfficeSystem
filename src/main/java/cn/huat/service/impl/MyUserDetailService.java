package cn.huat.service.impl;


import cn.huat.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: MyUserDetailService <br/>
 * Description: <br/>
 * date: 2020/11/24 19:11<br/>
 *
 * @author suhd<br />
 */
@Service("userDetailsService")
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 调用userMapper中的方法，根据用户名去查询数据库
        cn.huat.pojo.User users = userMapper.selectByPrimaryKey(Integer.valueOf(username));
        // 判断
        if (users == null) {
            // 数据库中没有用户名，认证失败
            throw new UsernameNotFoundException("用户名不存在!");
        }
        // 授权
        List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_" + users.getAccessauthority());
        return new User(String.valueOf(users.getAccount()),new BCryptPasswordEncoder().encode(users.getPassword()),auths);
    }

}
