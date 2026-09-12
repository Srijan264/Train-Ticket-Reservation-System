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

public class UserRegister extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();

        String mailId = req.getParameter("mailid");
        String pWord = req.getParameter("pword");
        String fName = req.getParameter("fname");
        String lName = req.getParameter("lname");
        String addr = req.getParameter("addr");
        long phNo = Long.parseLong(req.getParameter("phno"));

        try (Connection con = DBUtil.getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO user VALUES(?,?,?,?,?,?)");
            ps.setString(1, mailId);
            ps.setString(2, pWord);
            ps.setString(3, fName);
            ps.setString(4, lName);
            ps.setString(5, addr);
            ps.setLong(6, phNo);

            int k = ps.executeUpdate();
            if (k > 0) {
                RequestDispatcher rd = req.getRequestDispatcher("UserLogin.html");
                rd.include(req, res);
                pw.println("<div class='tab'><p1 class='menu'>User Registered Successfully!</p1></div>");
            } else {
                RequestDispatcher rd = req.getRequestDispatcher("UserRegister.html");
                rd.include(req, res);
                pw.println("<div class='tab'><p1 class='menu'>Registration Failed!</p1></div>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
