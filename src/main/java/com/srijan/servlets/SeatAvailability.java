package com.srijan.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SeatAvailability extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        long trainNo = Long.parseLong(req.getParameter("trainnumber"));

        try (Connection con = DBUtil.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT seats FROM train WHERE trainno=?");
            ps.setLong(1, trainNo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                req.setAttribute("seats", rs.getLong("seats"));
            }
            RequestDispatcher rd = req.getRequestDispatcher("SeatAvailability.jsp");
            rd.forward(req, res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
