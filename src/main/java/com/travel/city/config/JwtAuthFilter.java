package com.travel.city.config;

import lombok.RequiredArgsConstructor;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 본 메서드는 Servlet 요청을 중간에 가로채 JWT 인증을 하는 필터이다.
 */
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

    @RequiredArgsConstructor
    public class JwtAuthFilter extends GenericFilterBean {

        private final JwtTokenProvider jwtTokenProvider;

        @Override
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
            // 헤더에서 JWT 를 받아옵니다.
            String token = jwtTokenProvider.resolveToken((HttpServletRequest) servletRequest);
            // 토큰이 유효한지 체크
            if(token != null && jwtTokenProvider.validateToken(token)){
                // 토큰이 유효하면 토큰으로부터 유저 정보를 받아옵니다.
                Authentication authentication = jwtTokenProvider.getAuthentication(token);
                // SecurityContext 에 Authentication 객체를 저장합니다.
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

            filterChain.doFilter(servletRequest, servletResponse);
        }
    }