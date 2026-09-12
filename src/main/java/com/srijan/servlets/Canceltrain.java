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

public class Canceltrain extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();
        String transId = req.getParameter("transid");

        try (Connection con = DBUtil.getConnection()) {
            PreparedStatement ps1 = con.prepareStatement("SELECT * FROM history WHERE transid=?");
            ps1.setString(1, transId);
            ResultSet rs = ps1.executeQuery();

            if (rs.next()) {
                long trainNo = rs.getLong("tr_no");
                long seats = rs.getLong("seats");

                PreparedStatement ps2 = con.prepareStatement("UPDATE train SET seats = seats + ? WHERE trainno=?");
                ps2.setLong(1, seats);
                ps2.setLong(2, trainNo);
                ps2.executeUpdate();

                PreparedStatement ps3 = con.prepareStatement("DELETE FROM history WHERE transid=?");
                ps3.setString(1, transId);
                ps3.executeUpdate();

                RequestDispatcher rd = req.getRequestDispatcher("UserHome.jsp");
                rd.include(req, res);
                pw.println("<div class='tab'><p1 class='menu'>Ticket Cancelled Successfully!</p1></div>");
            } else {
                RequestDispatcher rd = req.getRequestDispatcher("UserHome.jsp");
                rd.include(req, res);
                pw.println("<div class='tab'><p1 class='menu'>Transaction ID not found!</p1></div>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
