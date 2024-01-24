package com.itheima.reggie.filter;

import com.alibaba.fastjson.JSON;
import com.itheima.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.PublicKey;

/**
 * 键盘敲烂,工资过万.
 * 自定义过滤器LoginCheckFilter
 * 检查用户是否完成登录
 */

@Slf4j
@WebFilter(filterName = "loginCheckFilter",urlPatterns = "/*")
public class LoginCheckFilter implements Filter {

    //路径匹配器，支持通配符
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request =(HttpServletRequest) servletRequest;
        HttpServletResponse response=(HttpServletResponse) servletResponse;

        //获取本次请求的URL
        String requestURI = request.getRequestURI();

        //1.定义不需要处理的的请求路径
        String[] urls= new String[]{

                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**"
        };
        //2.判断本次请求是否需要处理
        boolean check = check(urls, requestURI);
        //3.如果不需要处理直接放行
        if (check){
            log.info("本次不需拦截的路径是：{}",requestURI);
            //放开拦截
            filterChain.doFilter(request,response);
            return;
        }
        //4.判断登录状态，如果已登录，直接放行
        if (request.getSession().getAttribute("employee")!=null){
            log.info("用户已登录：{}",requestURI);
            filterChain.doFilter(request,response);
            return;
        }
       //5.如果未登录则返回未登录结果，通过输出流方式向客户端页面相应数据
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
            return;

        //log.info("拦截到请求：{}",request.getRequestURI());

    }

    /**
     * 路径匹配，检查是否需要放行
     * @param urls
     * @param requestURI
     * @return
     */
    public boolean check(String[] urls,String requestURI){
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url,requestURI);
            if (match){
                return true;
            }
        }
        return false;
    }
}
