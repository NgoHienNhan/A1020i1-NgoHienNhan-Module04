package vn.codegym.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("Ledung.ht17@gmail.com").password(this.passwordEncoder().encode("123")).roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                    /* antMatchers(...).permitAll() Cai dat URL pattern khong can xac thuc */
                    .authorizeRequests().antMatchers("/", "/login", "/logout").permitAll()

                    /* hasRole() cài đặt quyền cho URL pattern tương ứng. */
                    .antMatchers("/create-blog", "/edit-blog/**", "/delete-blog").hasRole("ADMIN");

                    /* tất cả request ngoài những URL pattern trên sẽ bắt buộc xác thực - login */
//                    .anyRequest().authenticated();

        http.authorizeRequests().and().formLogin()//
                // Submit URL của trang login
                .loginPage("/login")//
                .defaultSuccessUrl("/")//
                .failureUrl("/loginFail")//
                // Cấu hình cho Logout Page.
                .and().logout().deleteCookies("JSESSIONID").logoutUrl("/logout").logoutSuccessUrl("/");

        /* cấu hình remember me*/
        http.authorizeRequests().and().rememberMe()
//                            /* câu hình nơi lưu thông tin remember me và thời gian lưu cookie tại client*/
                .tokenRepository(this.persistentTokenRepository()).tokenValiditySeconds(60*60*10);


    }

    /* Cấu hình nơi lưu thông tin remember me*/
    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        /* dang cấu hình lưu tại memory của server */
        InMemoryTokenRepositoryImpl memoryTokenRepository = new InMemoryTokenRepositoryImpl();
        return memoryTokenRepository;
    }
}
