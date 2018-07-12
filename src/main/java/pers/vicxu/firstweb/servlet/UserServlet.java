package pers.vicxu.firstweb.servlet;

import pers.vicxu.firstweb.bean.User;
import pers.vicxu.firstweb.manager.UserManager;
import pers.vicxu.firstweb.manager.UserManagerImpl;
import pers.vicxu.firstweb.util.UserUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;


/**
 * Create by UserServlet on 7/11/2018
 */
@WebServlet(value = "/user", initParams = {@WebInitParam(name = "encoding", value = "UTF-8")})
public class UserServlet extends BaseServlet {


    private static final long serialVersionUID = 3752081400313375074L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (user == null)
            user = getUser(req);
        if (user.getName() != null && user.getPassWord() != null && user.getBirth() != null && user.getMajor() != null) {
            UserManager manager = new UserManagerImpl();
            manager.addUser(user);
            if (req.getSession().getAttribute("user") != null)
                req.getSession().removeAttribute("user");
            req.getRequestDispatcher("/userforward").forward(req, resp);
        }
    }
}
