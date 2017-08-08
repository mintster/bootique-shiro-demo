package com.nixmash.shiro.auth;

import com.google.inject.Singleton;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

@Singleton
public class RoleFilter extends AuthorizationFilter {
    private String unauthorizedUrl;

    public RoleFilter() {
        super.setUnauthorizedUrl("/unauthorized");
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        Subject subject = this.getSubject(request, response);
        if (subject.getPrincipal() == null) {
            this.saveRequestAndRedirectToLogin(request, response);
        } else {
            String unauthorizedUrl = this.getUnauthorizedUrl();
            if (StringUtils.hasText(unauthorizedUrl)) {
                WebUtils.issueRedirect(request, response, unauthorizedUrl);
            } else {
                WebUtils.toHttp(response).sendError(401);
            }
        }

        return false;
    }
}
