package com.github.cumtfc.srs.application;

import com.github.cumtfc.srs.bind.CurrentUserMethodArgumentResolver;
import com.github.cumtfc.srs.dao.SysUserRepository;
import com.github.cumtfc.srs.po.user.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author 冯楚
 * @date 2018/6/8-19:12
 */
@SpringBootApplication(scanBasePackages = {"com.github.cumtfc.srs.controller", "com.github.cumtfc.srs.service",
    "com.github.cumtfc.srs.component"})
@EntityScan(basePackages = "com.github.cumtfc.srs.po")
@EnableJpaRepositories(basePackages = "com.github.cumtfc.srs.dao")
@EnableConfigurationProperties
public class DemoApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins("http://localhost:4200")
            .allowCredentials(true).maxAge(3600);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(currentUserMethodArgumentResolver());
    }

    @Bean
    CurrentUserMethodArgumentResolver currentUserMethodArgumentResolver(){
        return new CurrentUserMethodArgumentResolver();
    }


    @Configuration
    public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        @Bean
        public PasswordEncoder passwordEncoder() {
            return NoOpPasswordEncoder.getInstance();
        }

        @Bean
        UserDetailsService customUserService() {
            return new CustomUserServiceImpl();
        }

        @Bean
        LoginUrlAuthenticationEntryPoint loginUrlAuthenticationEntryPoint() {
            return new LoginUrlAuthenticationEntryPoint();
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(customUserService());
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                .anyRequest().authenticated()
                .and().formLogin().successForwardUrl("/auth/login_success").permitAll().and()
                .logout().deleteCookies().logoutSuccessUrl("/auth/logout_success").permitAll()
                .and().exceptionHandling().authenticationEntryPoint(loginUrlAuthenticationEntryPoint())
                .and().cors().and().csrf().disable();
        }
    }


    public class CustomUserServiceImpl implements UserDetailsService {
        @Autowired
        SysUserRepository userRepository;
        @Override
        public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
            SysUser user = userRepository.findByUsername(s);
            if (user == null) {
                throw new UsernameNotFoundException("用户名不存在");
            }
            return user;
        }
    }

    public class LoginUrlAuthenticationEntryPoint implements AuthenticationEntryPoint {

        @Override
        public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
            if (request.getMethod().equals("OPTIONS")) {
                response.setStatus(200);
            } else {
                response.setStatus(401);
            }
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();

            String sb = "{\"status\":\"error\",\"msg\":\""
                + "未登陆!"
                + "\"}";
            out.write(sb);
            out.flush();
            out.close();
        }
    }

}
