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

import com.srijan.beans.AdminBean;

public class AdminLogin extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();

        String uName = req.getParameter("mailid");
        String pWord = req.getParameter("pword");

        try (Connection con = DBUtil.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM admin WHERE mailid=? AND pword=?");
            ps.setString(1, uName);
            ps.setString(2, pWord);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                AdminBean admin = new AdminBean();
                admin.setMailId(rs.getString("mailid"));
                admin.setpWord(rs.getString("pword"));
                admin.setfName(rs.getString("fname"));
                admin.setlName(rs.getString("lname"));
                admin.setAddr(rs.getString("addr"));
                admin.setPhNo(rs.getLong("phno"));

                HttpSession session = req.getSession();
                session.setAttribute("admin", admin);

                RequestDispatcher rd = req.getRequestDispatcher("AdminHome.jsp");
                rd.forward(req, res);
            } else {
                RequestDispatcher rd = req.getRequestDispatcher("AdminLogin.html");
                rd.include(req, res);
                pw.println("<div class='tab'><p1 class='menu'>Invalid Credentials!</p1></div>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
