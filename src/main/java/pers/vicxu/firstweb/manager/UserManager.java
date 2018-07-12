package pers.vicxu.firstweb.manager;

import pers.vicxu.firstweb.bean.User;

import java.util.List;

/**
 * Create by UserManager on 7/11/2018
 */
public interface UserManager {

    public int addUser(User user);

    public List<User> findAllUsers();
}
