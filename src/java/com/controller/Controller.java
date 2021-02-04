package com.controller;

import com.model.UserBean;
import com.login.LoginServlet;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Controller extends HttpServlet {

    Connection con;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            Class.forName(config.getInitParameter("driver"));
            String username = config.getInitParameter("dbuser");
            String password = config.getInitParameter("password");
            StringBuffer url = new StringBuffer(config.getInitParameter("url"));
            con = DriverManager.getConnection(url.toString(), username, password);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String search = request.getParameter("search");
        String delete = request.getParameter("delete");
        String update = request.getParameter("update");
        ArrayList<String> title = new ArrayList<String>();
        ArrayList<String> genre = new ArrayList<String>();
        ArrayList<String> year = new ArrayList<String>();
        HttpSession session = request.getSession();
        ResultSet rs;
        String qry;
        String result;

        UserBean bean = new UserBean();

        if (search != null) {
            if (Character.isDigit(search.charAt(0)) == true) {
                boolean check = bean.checkYear(con, search);
                if (check == false) {
                    request.setAttribute("error", "Song does not exist in your playlist");
                    RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
                    rd.include(request, response);
                } else {
                    result = (String) session.getAttribute("role");
                    qry = "SELECT * FROM SONGS WHERE RELEASED = ?";
                    rs = bean.search(con, search, qry);
                    while (rs.next()) {
                        title.add(rs.getString("title"));
                        genre.add(rs.getString("genre"));
                        year.add(rs.getString("released"));
                    }
                    request.setAttribute("title", title);
                    request.setAttribute("genre", genre);
                    request.setAttribute("year", year);
                    request.setAttribute("role", result);
                    RequestDispatcher rd = request.getRequestDispatcher("result.jsp");
                    rd.forward(request, response);
                }
            } else if (bean.checkGenre(con, search.toUpperCase()) == true) {
                result = (String) session.getAttribute("role");
                qry = "SELECT * FROM SONGS WHERE UPPER(GENRE) = ?";
                rs = bean.search(con, search.toUpperCase(), qry);
                while (rs.next()) {
                    title.add(rs.getString("title"));
                    genre.add(rs.getString("genre"));
                    year.add(rs.getString("released"));
                }
                request.setAttribute("title", title);
                request.setAttribute("genre", genre);
                request.setAttribute("year", year);
                request.setAttribute("role", result);
                RequestDispatcher rd = request.getRequestDispatcher("result.jsp");
                rd.forward(request, response);
            } else if (bean.checkTitle(con, search.toUpperCase()) == true) {
                result = (String) session.getAttribute("role");
                rs = bean.searchTitle(con, search.toUpperCase());
                while (rs.next()) {
                    title.add(rs.getString("title"));
                    genre.add(rs.getString("genre"));
                    year.add(rs.getString("released"));
                }
                request.setAttribute("title", title);
                request.setAttribute("genre", genre);
                request.setAttribute("year", year);
                request.setAttribute("role", result);
                RequestDispatcher rd = request.getRequestDispatcher("result.jsp");
                rd.forward(request, response);
            } else {
                request.setAttribute("error", "Song does not exist in your playlist");
                RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
                rd.include(request, response);
            }
        } else if (delete != null) {
            if (Character.isDigit(delete.charAt(0)) == true) {
                boolean check = bean.checkYear(con, delete);
                if (check == false) {
                    request.setAttribute("error", "Song does not exist in your playlist");
                    RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
                    rd.forward(request, response);
                } else {
                    qry = "DELETE FROM SONGS WHERE RELEASED = ?";
                    bean.delete(con, delete, qry);
                    result = (String) session.getAttribute("role");
                    ResultSet res = bean.select(con);
                    while (res.next()) {
                        title.add(res.getString("title"));
                        genre.add(res.getString("genre"));
                        year.add(res.getString("released"));
                    }
                    request.setAttribute("title", title);
                    request.setAttribute("genre", genre);
                    request.setAttribute("year", year);
                    request.setAttribute("role", result);
                    RequestDispatcher rd = request.getRequestDispatcher("welcome.jsp");
                    rd.forward(request, response);
                }
            } else if (bean.checkGenre(con, delete.toUpperCase()) == true) {
                qry = "DELETE FROM SONGS WHERE UPPER(GENRE) = ?";
                bean.delete(con, delete.toUpperCase(), qry);
                result = (String) session.getAttribute("role");
                ResultSet res = bean.select(con);
                while (res.next()) {
                    title.add(res.getString("title"));
                    genre.add(res.getString("genre"));
                    year.add(res.getString("released"));
                }
                request.setAttribute("title", title);
                request.setAttribute("genre", genre);
                request.setAttribute("year", year);
                request.setAttribute("role", result);
                RequestDispatcher rd = request.getRequestDispatcher("welcome.jsp");
                rd.forward(request, response);
            } else if (bean.checkTitle(con, delete.toUpperCase()) == true) {
                qry = "DELETE FROM SONGS WHERE UPPER(TITLE) = ?";
                bean.delete(con, delete.toUpperCase(), qry);
                result = (String) session.getAttribute("role");
                ResultSet res = bean.select(con);
                while (res.next()) {
                    title.add(res.getString("title"));
                    genre.add(res.getString("genre"));
                    year.add(res.getString("released"));
                }
                request.setAttribute("title", title);
                request.setAttribute("genre", genre);
                request.setAttribute("year", year);
                request.setAttribute("role", result);
                RequestDispatcher rd = request.getRequestDispatcher("welcome.jsp");
                rd.forward(request, response);
            } else {
                request.setAttribute("error", "Song does not exist in your playlist");
                RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
                rd.include(request, response);
            }
        } else if (update != null) {
            request.setAttribute("update", update);
            RequestDispatcher rd = request.getRequestDispatcher("update.jsp");
            rd.forward(request, response);
        } else {
            request.setAttribute("error", "Please choose among the button");
            RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
            rd.include(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
