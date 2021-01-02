package cn.huat.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * ClassName: SecurityConfig <br/>
 * Description: <br/>
 * date: 2020/11/25 13:48<br/>
 *
 * @author suhd<br />
 */
@Configuration

public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(password());
    }

    @Bean
    PasswordEncoder password() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/user/hello")
                .loginProcessingUrl("/user/login")
                .defaultSuccessUrl("/").permitAll()
                .and().authorizeRequests()
                .antMatchers("/user/hello", "/user/login","/static/**").permitAll()
                .antMatchers("/user/index", "/","/sfile/**","/user/SendFileIndex","/enreSFile/**","/EnreRFile/**","/notice/**")
                .hasAnyRole("normal,subAdministrator,administrator")
                .antMatchers("/system/**", "/user/manager").hasAnyRole("administrator")

                .and()
                .csrf().disable();
//                .rememberMe()
//                .tokenRepository(persistentTokenRepository())
//                .tokenValiditySeconds(3600)
//                .userDetailsService(userDetailsService);

        // 注销
        http.logout().logoutUrl("/user/logout")
                .logoutSuccessUrl("/user/hello")
                .permitAll();
        // 无权访问页  403
        http.exceptionHandling().accessDeniedPage("/user/unAuth");

    }
}
