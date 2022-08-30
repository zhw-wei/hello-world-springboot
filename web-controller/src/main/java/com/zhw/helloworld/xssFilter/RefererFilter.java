package com.zhw.helloworld.xssFilter;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 防止跨站请求伪造解决办法之——过滤referer
 */
@Slf4j
public class RefererFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        // 链接来源地址
        String referer = req.getHeader("referer");
        if (referer != null && !referer.contains(req.getServerName())) {
            // 如果 链接地址来自其他网站,不让继续访问
            log.warn("==========>> 跨站点请求，请求失败\t {}", referer);
        } else {
            chain.doFilter(req, res);
        }
    }

    @Override
    public void destroy() {

    }
}
