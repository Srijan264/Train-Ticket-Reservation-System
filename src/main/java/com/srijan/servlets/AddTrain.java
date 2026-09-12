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

public class AddTrain extends HttpServlet {
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
            PreparedStatement ps = con.prepareStatement("INSERT INTO train VALUES(?,?,?,?,?,?)");
            ps.setLong(1, trainNo);
            ps.setString(2, trainName);
            ps.setString(3, fromStn);
            ps.setString(4, toStn);
            ps.setLong(5, seats);
            ps.setDouble(6, fare);

            int k = ps.executeUpdate();
            if (k > 0) {
                RequestDispatcher rd = req.getRequestDispatcher("AdminHome.jsp");
                rd.include(req, res);
                pw.println("<div class='tab'><p1 class='menu'>Train Added Successfully!</p1></div>");
            } else {
                RequestDispatcher rd = req.getRequestDispatcher("AddTrains.html");
                rd.include(req, res);
                pw.println("<div class='tab'><p1 class='menu'>Failed to Add Train!</p1></div>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
