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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
public class CartBE extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
           Connection con = DbConnection.getConnectToECOM();
            String sql = "SELECT * FROM CART";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // Display products in the cart
            out.println("<html><body>");
            out.println("<span>ITEMS IN CART</span>");
            out.println("<ul>");

            
                /*String img = rs.getString("IMG");
                String pnameName = rs.getString("PNAME");
                int pid = rs.getInt("PID");
                String category = rs.getString("CATEGORY");     
                long pprice = rs.getLong("PPRICE");
                int  quantity = rs.getInt("CATEGORY");*/
                out.println("<table border=1>");
                out.println("<tr><th>IMAGE</th><th>NAME</th><th>P-ID</th><th>CATEGORY</th><th>PRICE</th><th>QUANTITY</th></tr>");
            while (rs.next()) {
                out.println("<tr><td>" + rs.getString(1)+"</td>");
                out.println("<td>" + rs.getString(2)+"</td>");
                out.println("<td>" + rs.getInt(3)+"</td>");
                out.println("<td>" + rs.getString(4)+"</td>");
                out.println("<td>" + rs.getLong(5)+"</td>");
                out.println("<td>" + rs.getInt(6)+"</td></tr>");
            }
            out.println("</table>");
            out.println("<form method=\"post\" action=\"OrdersBE\">");
            out.println("<input type=\"submit\" value=\"Place order\">");

            out.println("</ul>");
            out.println("</body></html>");

            rs.close();
            stmt.close();
            con.close();
        
            /*out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CartBE</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CartBE at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");*/
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
            Logger.getLogger(CartBE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CartBE.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(CartBE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CartBE.class.getName()).log(Level.SEVERE, null, ex);
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
