package es.thehillogy.thefarmerkitbackend.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CorsFilter implements Filter {

	private static Logger log = LoggerFactory.getLogger(CorsFilter.class);
	
	private static final String OPTIONS_LABEL = "OPTIONS";
	
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    	// No Implementation
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    	
    	HttpServletRequest request = (HttpServletRequest) servletRequest;
    	
    	log.info("CORSFilter HTTP Request: {} - {}", request.getMethod(), request.getRequestURI()); 
        
        // Authorize (allow) all domains to consume the content
        ((HttpServletResponse) servletResponse).setHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) servletResponse).setHeader("Access-Control-Allow-Methods","GET, OPTIONS, HEAD, PUT, POST, DELETE");
        ((HttpServletResponse) servletResponse).setHeader("Access-Control-Allow-Headers",
        		"Authorization,Access-Control-Allow-Origin,Content-Type,SOAPAction,user-jwt,sso-jwt,origin");
 
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
 
        // For HTTP OPTIONS verb/method reply with ACCEPTED status code -- per CORS handshake
        if (OPTIONS_LABEL.equals(request.getMethod())) {
            resp.setStatus(HttpServletResponse.SC_ACCEPTED);
            return;
        }
 
        // pass the request along the filter chain
        filterChain.doFilter(request, servletResponse);
    }

    @Override
    public void destroy() {
    	// No Implementation
    }
}
