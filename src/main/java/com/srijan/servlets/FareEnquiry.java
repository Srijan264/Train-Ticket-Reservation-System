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

public class FareEnquiry extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String fromStn = req.getParameter("fromstation");
        String toStn = req.getParameter("tostation");

        try (Connection con = DBUtil.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                "SELECT fare FROM train WHERE fromstn=? AND tostn=?"
            );
            ps.setString(1, fromStn);
            ps.setString(2, toStn);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                req.setAttribute("fare", rs.getDouble("fare"));
            }
            RequestDispatcher rd = req.getRequestDispatcher("FareResult.jsp");
            rd.forward(req, res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
