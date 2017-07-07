package org.forten.si.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Administrator on 2017/7/6.
 */
@WebFilter(urlPatterns = {"/student/*"})
public class AuthLoginedFilter4Student implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        boolean isAdmin = (Boolean)session.getAttribute("isAdmin");
        if(isAdmin){
            res.sendRedirect("/admin/index.html");
        }else{
            chain.doFilter(req,res);
        }
    }

    @Override
    public void destroy() {

    }
}
