package com.nixmash.shiro.service;

import com.nixmash.shiro.models.CurrentUser;
import com.nixmash.shiro.models.Role;
import com.nixmash.shiro.models.User;
import org.apache.shiro.subject.Subject;

import java.util.List;

public interface UserService {
    User createUser(User user);
    User getUser(String username);
    List<Role> getRoles(Long userId);
    CurrentUser createCurrentUser(Subject subject);

}
