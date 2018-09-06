import javax.servlet.*;
import java.util.*;

// 类似于对URL进行过滤, 指定URL执行对应操作等.
// 通过web.xml进行配置
public class LogFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
        String site = config.getInitParameter("filterName");

        System.out.println("filterName : " + site);
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws java.io.IOException, ServletException {
        System.out.println("LogFilter : " + request);

        chain.doFilter(request, response);
    }

    public void destroy() {

    }
}