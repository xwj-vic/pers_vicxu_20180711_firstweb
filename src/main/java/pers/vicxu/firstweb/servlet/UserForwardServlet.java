package pers.vicxu.firstweb.servlet;

import pers.vicxu.firstweb.bean.User;
import pers.vicxu.firstweb.manager.UserManager;
import pers.vicxu.firstweb.manager.UserManagerImpl;
import pers.vicxu.firstweb.xml.DomProcessor;
import pers.vicxu.firstweb.xml.DomProcessorWithDom4j;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Create by UserServlet on 7/11/2018
 */
@WebServlet(value = "/userforward", initParams = {@WebInitParam(name = "encoding", value = "UTF-8")})
public class UserForwardServlet extends BaseServlet {

    private static final long serialVersionUID = 1631912166561318650L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        UserManager userManager = new UserManagerImpl();
        List<User> users = userManager.findAllUsers();
        DomProcessor processor = new DomProcessorWithDom4j();
        processor.usersToHtml(users, resp.getWriter());
    }


}
