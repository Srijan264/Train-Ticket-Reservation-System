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

public class EditUserProfile extends HttpServlet {
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
        String fName = req.getParameter("fname");
        String lName = req.getParameter("lname");
        String addr = req.getParameter("addr");
        long phNo = Long.parseLong(req.getParameter("phno"));

        try (Connection con = DBUtil.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                "UPDATE user SET fname=?, lname=?, addr=?, phno=? WHERE mailid=?"
            );
            ps.setString(1, fName);
            ps.setString(2, lName);
            ps.setString(3, addr);
            ps.setLong(4, phNo);
            ps.setString(5, user.getMailId());

            int k = ps.executeUpdate();
            if (k > 0) {
                user.setfName(fName);
                user.setlName(lName);
                user.setAddr(addr);
                user.setPhNo(phNo);
                session.setAttribute("user", user);

                RequestDispatcher rd = req.getRequestDispatcher("UserProfile.jsp");
                rd.include(req, res);
                pw.println("<div class='tab'><p1 class='menu'>Profile Updated Successfully!</p1></div>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
