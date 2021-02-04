package com.login;

//import com.pdfCreator.PdfCreator;
import com.model.UserBean;
import com.pdfCreator.PDFCreator;
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

public class LoginServlet extends HttpServlet {
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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ServletConfig config = getServletConfig();

            byte[] key = config.getInitParameter("key").getBytes();
            String user = request.getParameter("user");
            String pass = request.getParameter("pass");
            String newuser = request.getParameter("newuser");
            String newpass = request.getParameter("newpass");
            String conpass = request.getParameter("conpass");
            String role = request.getParameter("role");
            String name;

            UserBean bean = new UserBean();

            if (conpass == null) {
                String encryptedPW = bean.encrypt(pass, key);
                boolean chk = bean.check(con, user, encryptedPW);
                if (chk == true) {
                    ResultSet rset = bean.user(con, user, encryptedPW);
                    while (rset.next()) {
                        bean.setName(rset.getString("username"));
                    }
                    PDFCreator pdf = new PDFCreator();
                    pdf.generate(bean);
                    HttpSession session = request.getSession();
                    session.setAttribute("username", user);
                    ArrayList<String> title = new ArrayList<String>();
                    ArrayList<String> genre = new ArrayList<String>();
                    ArrayList<String> year = new ArrayList<String>();
                    String result = "";
                    ResultSet rs = bean.role(con, user, encryptedPW);
                    while (rs.next()) {
                        result = rs.getString("role");
                    }
                    session.setAttribute("role", result);
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
                    boolean checkingUser = bean.checkUser(con, user);
                    if (checkingUser == false) {
                        request.setAttribute("error", "Username and Password are incorrect");
                        RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
                        rd.include(request, response);
                    } else {
                        request.setAttribute("error", "Incorrect password");
                        RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
                        rd.include(request, response);
                    }
                }
            } else {
                String result = "";
                ResultSet rs = bean.compare(con, newuser);
                while (rs.next()) {
                    result = rs.getString("username");
                }
                if (newuser.equals(result)) {
                    request.setAttribute("error", "Username has already been taken");
                    RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
                    rd.include(request, response);
                } else if (newpass.equals(conpass)) {
                    String encryptedPassword = bean.encrypt(conpass, key);
                    bean.insert(con, newuser, encryptedPassword, role);
                    response.sendRedirect("successSignUp.jsp");
                } else {
                    request.setAttribute("error", "Password does not match");
                    RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
                    rd.include(request, response);
                }
            }
        } catch (Exception e) {
            System.out.print("Error");
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
        processRequest(request, response);
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
        processRequest(request, response);
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
