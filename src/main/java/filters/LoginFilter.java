package filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        String url = ((HttpServletRequest) req).getRequestURI();
        if (url.toLowerCase().endsWith(".js")
                || url.toLowerCase().endsWith(".css")
                || url.toLowerCase().endsWith(".ttf")) {
            chain.doFilter(req, resp);
            return;
        }
        Object isAuthorised = ((HttpServletRequest) req).getSession().getAttribute("isAuthorised");
        if (isAuthorised == null && url.toLowerCase().endsWith("/login")) {
            chain.doFilter(req, resp);
            return;
        }
        if (isAuthorised != null) {
            chain.doFilter(req, resp);
            return;
        }
        ((HttpServletResponse) resp).sendRedirect("/login");
    }

    @Override
    public void destroy() {
    }
}
