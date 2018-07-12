package pers.vicxu.firstweb.servlet;

import pers.vicxu.firstweb.bean.User;
import pers.vicxu.firstweb.util.UserUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

/**
 * Create by BaseServlet on 7/11/2018
 */
public class BaseServlet extends HttpServlet {
    private static final long serialVersionUID = 1617951502525192976L;
    public String encoding = "UTF-8";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(encoding);
        resp.setHeader("Content-Type", "text/html;charset=" + encoding);
        resp.setCharacterEncoding(encoding);
        String path = req.getServletPath();
        if (path.equals("/login"))
            super.service(req, resp);
        else {
            if (req.getSession().getAttribute("userName") == null) {
                if (path.equals("/user")) {
                    User user = getUser(req);
                    req.getSession().setAttribute("user", user);
                }
                resp.sendRedirect("login.html");
            } else {
                super.service(req, resp);
            }
        }
    }

    User getUser(HttpServletRequest req) {
        User user = null;
        if (req.getSession().getAttribute("user") != null) {
            user = (User) req.getSession().getAttribute("user");
        } else {
            String name = req.getParameter("name");
            String passWord = req.getParameter("passWord");
            String birthString = req.getParameter("birth");
            String major = req.getParameter("major");
            Date birth = null;
            try {
                birth = UserUtil.format.parse(birthString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            user = new User(name, passWord, birth, major);
        }
        return user;
    }
}
