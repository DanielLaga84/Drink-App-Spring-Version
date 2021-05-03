package javer.drinkappspringversion.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder() //checking
                .username("user").password("user").roles("USER").build();

        UserDetails admin = User.withDefaultPasswordEncoder() //checking
                .username("admin").password("admin").roles("ADMIN").build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and().authorizeRequests()
         .antMatchers(HttpMethod.GET, "/user/**").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/admin/**").hasRole("ADMIN")
                .antMatchers("/*").permitAll()
                .and()
                .formLogin().loginPage("/login").permitAll()
                .and()
                .logout().permitAll();
    }
}
