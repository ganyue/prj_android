package com.mapsocial.security;

import com.mapsocial.filter.JwtAuthenticationFilter;
import com.mapsocial.filter.JwtLoginFilter;
import net.sf.ehcache.Ehcache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.cache.EhCacheBasedUserCache;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by yue.gan on 2017/10/24.
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private UserCache userCache;

    @Bean
    public EhCacheBasedUserCache ehCacheBasedUserCache () {
        CacheManager cacheManager = applicationContext.getBean(CacheManager.class);
        EhCacheBasedUserCache ehCacheBasedUserCache = new EhCacheBasedUserCache();
        ehCacheBasedUserCache.setCache((Ehcache) cacheManager.getCache("users").getNativeCache());
        return ehCacheBasedUserCache;
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        authenticationProvider.setUserCache(userCache);
        return authenticationProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeRequests().antMatchers(HttpMethod.POST, "/register").permitAll()
                .antMatchers(HttpMethod.GET, "/test").permitAll()
                .antMatchers("/admins/**").hasRole("ADMIN")
                .antMatchers("/users/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .addFilter(new JwtLoginFilter(authenticationManager()))
                .addFilter(new JwtAuthenticationFilter(authenticationManager(), userDetailsService, userCache));
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
        auth.authenticationProvider(authenticationProvider());
    }
}
