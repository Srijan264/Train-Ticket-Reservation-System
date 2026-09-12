package com.srijan.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.srijan.beans.TrainBean;

public class TrainBwStn extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String fromStn = req.getParameter("fromstation");
        String toStn = req.getParameter("tostation");
        List<TrainBean> list = new ArrayList<>();

        try (Connection con = DBUtil.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM train WHERE fromstn=? AND tostn=?"
            );
            ps.setString(1, fromStn);
            ps.setString(2, toStn);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new TrainBean(
                    rs.getLong("trainno"),
                    rs.getString("trainname"),
                    rs.getString("fromstn"),
                    rs.getString("tostn"),
                    rs.getLong("seats"),
                    rs.getDouble("fare")
                ));
            }
            req.setAttribute("trainList", list);
            RequestDispatcher rd = req.getRequestDispatcher("SearchTrain.jsp");
            rd.forward(req, res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
