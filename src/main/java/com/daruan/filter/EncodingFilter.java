package com.daruan.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by ruanlinyu on 2016/12/28.
 */
public class EncodingFilter implements Filter {
    private String charset = "UTF-8";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String charset = filterConfig.getInitParameter("charset");
        if(charset != null && !charset.isEmpty())
        {
            this.charset = charset;
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if(request.getMethod().equalsIgnoreCase("GET"))
        {
            if( !(request instanceof GetRequest))
            {
                //处理get请求编码
                request = new GetRequest(request,charset);
            }
        }
        else
        {
            //处理post请求编码
            request.setCharacterEncoding(charset);
        }
        filterChain.doFilter(request,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
