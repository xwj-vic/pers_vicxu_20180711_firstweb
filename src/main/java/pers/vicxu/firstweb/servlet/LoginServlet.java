package pers.vicxu.firstweb.servlet;

import pers.vicxu.firstweb.data.StaticData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Create by LoginServlet on 7/11/2018
 */
@WebServlet(value = "/login", initParams = {@WebInitParam(name = "encoding", value = "UTF-8")})
public class LoginServlet extends BaseServlet {

    private static final long serialVersionUID = 3530163658318626315L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        String passWord = req.getParameter("passWord");
        if (userName.equals(StaticData.userName) && passWord.equals(StaticData.passWord)) {
            req.getSession().setAttribute("userName", userName);
            if (req.getSession().getAttribute("user") != null)
                req.getRequestDispatcher("/user").forward(req, resp);
            else
                resp.sendRedirect("user.html");
        } else {
            resp.sendRedirect("login.html");
        }
    }
}
