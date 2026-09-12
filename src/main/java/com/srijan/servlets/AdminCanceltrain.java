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

public class AdminCanceltrain extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();
        long trainNo = Long.parseLong(req.getParameter("trainno"));

        try (Connection con = DBUtil.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM train WHERE trainno=?");
            ps.setLong(1, trainNo);
            int k = ps.executeUpdate();

            if (k > 0) {
                RequestDispatcher rd = req.getRequestDispatcher("AdminHome.jsp");
                rd.include(req, res);
                pw.println("<div class='tab'><p1 class='menu'>Train Cancelled Successfully!</p1></div>");
            } else {
                RequestDispatcher rd = req.getRequestDispatcher("AdminHome.jsp");
                rd.include(req, res);
                pw.println("<div class='tab'><p1 class='menu'>Train Number Not Found!</p1></div>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
