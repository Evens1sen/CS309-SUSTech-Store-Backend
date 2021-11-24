package com.project.store.filter;

import cn.dev33.satoken.stp.StpUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (!StpUtil.isLogin()) {
            response.setStatus(403);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
