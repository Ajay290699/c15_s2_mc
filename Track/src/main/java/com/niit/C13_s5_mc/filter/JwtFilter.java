package com.niit.C13_s5_mc.filter;

import io.jsonwebtoken.Jwts;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String authHeader = httpServletRequest.getHeader("Authorization");
        System.out.println("AuthHeader = "+authHeader);
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        if (authHeader == null || !authHeader.startsWith("Bearer")){
            //token is missing or invalid token
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            servletOutputStream.print("missing or invalid token");
            servletOutputStream.close();
        }else {
            String  jwtToken = authHeader.substring(7);
            System.out.println("jwtToken = "+jwtToken);
            String firstName = Jwts.parser().setSigningKey("securityKey").parseClaimsJws(jwtToken).getBody().getSubject();
            httpServletRequest.setAttribute("fisrtName",firstName);
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
