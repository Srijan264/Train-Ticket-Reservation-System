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

public class AdminUpdateTrain extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();

        long trainNo = Long.parseLong(req.getParameter("trainno"));
        String trainName = req.getParameter("trainname");
        String fromStn = req.getParameter("fromstn");
        String toStn = req.getParameter("tostn");
        long seats = Long.parseLong(req.getParameter("seats"));
        double fare = Double.parseDouble(req.getParameter("fare"));

        try (Connection con = DBUtil.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                "UPDATE train SET trainname=?, fromstn=?, tostn=?, seats=?, fare=? WHERE trainno=?"
            );
            ps.setString(1, trainName);
            ps.setString(2, fromStn);
            ps.setString(3, toStn);
            ps.setLong(4, seats);
            ps.setDouble(5, fare);
            ps.setLong(6, trainNo);

            int k = ps.executeUpdate();
            if (k > 0) {
                RequestDispatcher rd = req.getRequestDispatcher("AdminHome.jsp");
                rd.include(req, res);
                pw.println("<div class='tab'><p1 class='menu'>Train Schedule Updated!</p1></div>");
            } else {
                RequestDispatcher rd = req.getRequestDispatcher("AdminHome.jsp");
                rd.include(req, res);
                pw.println("<div class='tab'><p1 class='menu'>Update Failed!</p1></div>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
