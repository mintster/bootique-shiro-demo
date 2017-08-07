package com.nixmash.shiro.views;

import com.nixmash.shiro.models.CurrentUser;
import io.bootique.mvc.AbstractView;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

import static com.nixmash.shiro.service.UserServiceImpl.CURRENT_USER;

public class PageView extends AbstractView {

    private Object model;
    private CurrentUser user;

    public PageView(String template, Object model) {
        super(template);
        this.model = model;

        Session session = SecurityUtils.getSubject().getSession();
        if (SecurityUtils.getSubject().getPrincipals() != null)
            this.user = (CurrentUser) session.getAttribute(CURRENT_USER);
    }

    public Object getModel() {
        return model;
    }

    public CurrentUser getUser() {
        return user;
    }
}

