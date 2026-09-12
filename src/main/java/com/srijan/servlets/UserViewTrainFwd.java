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

public class UserViewTrainFwd extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        List<TrainBean> trains = new ArrayList<>();
        try (Connection con = DBUtil.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM train");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                trains.add(new TrainBean(
                    rs.getLong("trainno"),
                    rs.getString("trainname"),
                    rs.getString("fromstn"),
                    rs.getString("tostn"),
                    rs.getLong("seats"),
                    rs.getDouble("fare")
                ));
            }
            req.setAttribute("trainList", trains);
            RequestDispatcher rd = req.getRequestDispatcher("UserViewTrains.jsp");
            rd.forward(req, res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
