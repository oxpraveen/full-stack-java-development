/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecommerce;

import DbConnection.DbConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jayan
 */
public class AddcustBE extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            //TODO output your page here. You may use following sample code. 
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddcustBE</title>");
            out.println("</head>");
            out.println("<body>");

            String cname = request.getParameter("cname");
            String email = request.getParameter("email");
            String pwd = request.getParameter("pwd");
            long phno = Long.parseLong(request.getParameter("phno"));
            String address = request.getParameter("address");

            /*out.println("Name:" + cname);
            out.println("Email:" + email);
            out.println("Password:" + pwd);
            out.println("Phone No:" + phno);
            out.println("Address:" + address);
            //jdbc Steps
             
//out.println("<h1>Servlet AddAttdBE at " + request.getContextPath() + "</h1>");*/
            
            
            Connection con = DbConnection.getConnectToECOM();
            String sql = "insert into CUSTOMER(CNAME,EMAIL,PASSWORD,PHNO,ADDRESS) values(?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, cname);
            ps.setString(2, email);
            ps.setString(3, pwd);
            ps.setLong(4, phno);
            ps.setString(5, address);
            int i = ps.executeUpdate();
            if (i > 0) {
                out.println("<script>");
                out.println("window.onload = function() {");
                out.println("  if (confirm('Signup Completed! Click OK continue.')) {");
                out.println("    window.location.href = 'home.html';"); // Replace with your desired URL
                out.println("  }");
                out.println("}");
                out.println("</script>");
            }
            con.close();
            out.println("</body>");
            out.println("</html>");
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
            try {
                processRequest(request, response);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AddcustBE.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddcustBE.class.getName()).log(Level.SEVERE, null, ex);
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
            try {
                processRequest(request, response);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AddcustBE.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddcustBE.class.getName()).log(Level.SEVERE, null, ex);
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
