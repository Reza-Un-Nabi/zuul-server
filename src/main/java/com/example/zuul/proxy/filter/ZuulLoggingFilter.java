package com.example.zuul.proxy.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


import javax.servlet.http.HttpServletRequest;


@Component
public class ZuulLoggingFilter extends ZuulFilter {
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    @Override
    public String filterType() {

        return "pre"; //intercept all the request before execution
    }

    @Override
    public int filterOrder() {

        return 1;
    }

    @Override
    public boolean shouldFilter() {

        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //getting the current HTTP request that is to be handle
        HttpServletRequest request= RequestContext.getCurrentContext().getRequest();
//printing the detail of the request
        logger.info("Logging................request -> {} request uri-> {}", request, request.getRequestURI());
        return null;
    }


    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

}
