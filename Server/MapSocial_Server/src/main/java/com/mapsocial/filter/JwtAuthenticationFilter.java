package com.mapsocial.filter;

import com.mapsocial.constant.JwtConstants;
import com.mapsocial.domain.User;
import com.mapsocial.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.cache.EhCacheBasedUserCache;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yue.gan on 2017/10/30.
 */
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    private UserDetailsService userDetailsService;
    private UserCache userCache;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager,
                                   UserDetailsService userDetailsService,
                                   UserCache userCache) {
        super(authenticationManager);
        this.userDetailsService = userDetailsService;
        this.userCache = userCache;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(JwtConstants.TOKEN_HEADER_PARAM_NAME);
        if (header == null || !header.startsWith(JwtConstants.TOKEN_HEADER_PARAM_HEAD)) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String header = request.getHeader(JwtConstants.TOKEN_HEADER_PARAM_NAME);
        if (header != null) {
            String username = JwtUtils.parseToken(header).getBody().getSubject();
            if (username != null) {
                User user = null;
                if (userCache != null) {
                    user = (User) userCache.getUserFromCache(username);
                }

                if (user == null) {
                    user = (User) userDetailsService.loadUserByUsername(username);
                    if (user == null) return null;
                    userCache.putUserInCache(user);
                }

                return new UsernamePasswordAuthenticationToken(username, null, user.getAuthorities());
            }
        }
        return null;
    }
}
