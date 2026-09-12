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
import javax.servlet.http.HttpSession;

import com.srijan.beans.HistoryBean;
import com.srijan.beans.UserBean;

public class History extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            RequestDispatcher rd = req.getRequestDispatcher("UserLogin.html");
            rd.forward(req, res);
            return;
        }

        UserBean user = (UserBean) session.getAttribute("user");
        List<HistoryBean> historyList = new ArrayList<>();

        try (Connection con = DBUtil.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM history WHERE mailid=?");
            ps.setString(1, user.getMailId());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                historyList.add(new HistoryBean(
                    rs.getString("transid"),
                    rs.getString("from_stn"),
                    rs.getString("to_stn"),
                    rs.getString("date"),
                    rs.getString("mailid"),
                    rs.getLong("seats"),
                    rs.getDouble("amount"),
                    rs.getLong("tr_no")
                ));
            }
            req.setAttribute("history", historyList);
            RequestDispatcher rd = req.getRequestDispatcher("History.jsp");
            rd.forward(req, res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
