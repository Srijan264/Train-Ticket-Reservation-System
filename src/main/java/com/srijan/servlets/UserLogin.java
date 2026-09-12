package com.srijan.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.srijan.beans.UserBean;

public class UserLogin extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();

        String uName = req.getParameter("mailid");
        String pWord = req.getParameter("pword");

        try (Connection con = DBUtil.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM user WHERE mailid=? AND pword=?");
            ps.setString(1, uName);
            ps.setString(2, pWord);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                UserBean user = new UserBean();
                user.setMailId(rs.getString("mailid"));
                user.setpWord(rs.getString("pword"));
                user.setfName(rs.getString("fname"));
                user.setlName(rs.getString("lname"));
                user.setAddr(rs.getString("addr"));
                user.setPhNo(rs.getLong("phno"));

                HttpSession session = req.getSession();
                session.setAttribute("user", user);

                RequestDispatcher rd = req.getRequestDispatcher("UserHome.jsp");
                rd.forward(req, res);
            } else {
                RequestDispatcher rd = req.getRequestDispatcher("UserLogin.html");
                rd.include(req, res);
                pw.println("<div class='tab'><p1 class='menu'>Invalid Username or Password!</p1></div>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
