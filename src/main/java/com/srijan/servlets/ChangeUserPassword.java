package com.srijan.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.srijan.beans.UserBean;

public class ChangeUserPassword extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            RequestDispatcher rd = req.getRequestDispatcher("UserLogin.html");
            rd.forward(req, res);
            return;
        }

        UserBean user = (UserBean) session.getAttribute("user");
        String oldPwd = req.getParameter("oldpassword");
        String newPwd = req.getParameter("newpassword");

        try (Connection con = DBUtil.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                "UPDATE user SET pword=? WHERE mailid=? AND pword=?"
            );
            ps.setString(1, newPwd);
            ps.setString(2, user.getMailId());
            ps.setString(3, oldPwd);

            int k = ps.executeUpdate();
            if (k > 0) {
                user.setpWord(newPwd);
                session.setAttribute("user", user);
                RequestDispatcher rd = req.getRequestDispatcher("UserHome.jsp");
                rd.include(req, res);
                pw.println("<div class='tab'><p1 class='menu'>Password Updated Successfully!</p1></div>");
            } else {
                RequestDispatcher rd = req.getRequestDispatcher("ChangePassword.jsp");
                rd.include(req, res);
                pw.println("<div class='tab'><p1 class='menu'>Incorrect Current Password!</p1></div>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
