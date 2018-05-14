package bookStore.config;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Override
    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response) {
        // Get the role of logged in user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String role = authentication.getAuthorities().toString();

        String targetUrl = "";
        if(role.contains("ADMINISTRATOR")) {
            targetUrl = "/admin";
        } else if(role.contains("EMPLOYEE")) {
            targetUrl = "/employee";
        } else if(role.contains("SECRETARY")){
            targetUrl = "/secretary";
        } else if(role.contains("DOCTOR")){
            targetUrl = "/doctor";
        }
        return targetUrl;
    }

}

