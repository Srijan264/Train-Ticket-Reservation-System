package com.srijan.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.srijan.beans.UserBean;

public class BookTrains extends HttpServlet {
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
        long trainNo = Long.parseLong(req.getParameter("trainno"));
        long seats = Long.parseLong(req.getParameter("seats"));

        try (Connection con = DBUtil.getConnection()) {
            PreparedStatement ps1 = con.prepareStatement("SELECT * FROM train WHERE trainno=?");
            ps1.setLong(1, trainNo);
            ResultSet rs = ps1.executeQuery();

            if (rs.next()) {
                long availSeats = rs.getLong("seats");
                double fare = rs.getDouble("fare");
                String fromStn = rs.getString("fromstn");
                String toStn = rs.getString("tostn");

                if (availSeats >= seats) {
                    double totalAmount = seats * fare;
                    String transId = UUID.randomUUID().toString();

                    PreparedStatement ps2 = con.prepareStatement(
                        "UPDATE train SET seats = seats - ? WHERE trainno = ?"
                    );
                    ps2.setLong(1, seats);
                    ps2.setLong(2, trainNo);
                    ps2.executeUpdate();

                    PreparedStatement ps3 = con.prepareStatement("INSERT INTO history VALUES(?,?,?,?,?,?,?,?)");
                    ps3.setString(1, transId);
                    ps3.setString(2, fromStn);
                    ps3.setString(3, toStn);
                    ps3.setString(4, new java.util.Date().toString());
                    ps3.setString(5, user.getMailId());
                    ps3.setLong(6, seats);
                    ps3.setDouble(7, totalAmount);
                    ps3.setLong(8, trainNo);
                    ps3.executeUpdate();

                    req.setAttribute("transId", transId);
                    req.setAttribute("amount", totalAmount);
                    RequestDispatcher rd = req.getRequestDispatcher("BookingSuccess.jsp");
                    rd.forward(req, res);
                } else {
                    RequestDispatcher rd = req.getRequestDispatcher("BookTrain.jsp");
                    rd.include(req, res);
                    pw.println("<div class='tab'><p1 class='menu'>Requested seats not available!</p1></div>");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
