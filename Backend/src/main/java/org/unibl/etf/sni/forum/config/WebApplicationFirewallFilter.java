package org.unibl.etf.sni.forum.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;

@Component
public class WebApplicationFirewallFilter extends OncePerRequestFilter {

    private  Siem siem;

    public WebApplicationFirewallFilter(Siem siem){
        this.siem = siem;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8))) {
            char[] charBuffer = new char[128];
            int bytesRead;
            while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                stringBuilder.append(charBuffer, 0, bytesRead);
            }
        }
       String body = stringBuilder.toString();

        if (isPotentialSqlInjection(request) || containsSqlInjectionPatterns(body)) {
            siem.logWarning("Potential SQL Injection attempt detected. Request URI: {} IP Address: {}", request.getRequestURI(), request.getRemoteAddr());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Potential SQL Injection attempt detected");
            return;
        }
        filterChain.doFilter(request, response);
    }

    private boolean isPotentialSqlInjection(HttpServletRequest request) {
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            String[] parameterValues = request.getParameterValues(parameterName);
            for (String value : parameterValues) {
                if (containsSqlInjectionPatterns(value)) {
                    return true;
                }
            }
        }

        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            if (containsSqlInjectionPatterns(headerValue)) {
                return true;
            }
        }

        return false;
    }

    private boolean containsSqlInjectionPatterns(String value) {
        String[] sqlPatterns = {"'","#" ,"drop", "union", "select", "insert", "update", "delete"};

        if (value != null) {
            value = value.toLowerCase();
            for (String pattern : sqlPatterns) {
                if (value.contains(pattern)) {
                    return true;
                }
            }
        }

        return false;
    }
}
