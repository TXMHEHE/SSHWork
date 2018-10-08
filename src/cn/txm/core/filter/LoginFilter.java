package cn.txm.core.filter;

import cn.txm.core.constant.Constant;
import cn.txm.core.permission.PermissionCheck;
import cn.txm.nsfw.user.entity.User;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter
            (ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
                                                                    throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;

        String uri=request.getRequestURI();

        //判断当前请求地址是否是登陆的请求地址
        if(!uri.contains("sys/login_")) {
            //非登陆请求
            if(request.getSession().getAttribute(Constant.USER) != null) {
                //说明已经登陆过
                //判断是否访问纳税服务系统
                if(uri.contains("/nsfw/")) {
                    //访问纳税服务子系统
                    User user=(User)request.getSession().getAttribute(Constant.USER);
                    //获取spring容器
                    ApplicationContext applicationContext= WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
                    PermissionCheck pc=(PermissionCheck)applicationContext.getBean("permissionCheck");
                    if(pc.isAccessible(user,"nsfw")) {
                        //说明有权限，放行
                        filterChain.doFilter(request,response);
                    } else {
                        //没有权限，跳转到没有权限的提示页面
                        response.sendRedirect(request.getContextPath()+"/sys/login_toNoPermissionUI.action");
                    }
                } else {
                    //非访问纳税服务子系统，则直接放行
                    filterChain.doFilter(request,response);
                }
            } else {
                //没有登陆，跳转到登陆页面
                response.sendRedirect(request.getContextPath()+"/sys/login_toLoginUI.action");
            }
        } else {
            //登陆请求，直接放行
            filterChain.doFilter(request,response);
        }
    }

    @Override
    public void destroy() {

    }
}