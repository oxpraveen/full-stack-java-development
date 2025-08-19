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
public class ViewodrBE extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Connection con = DbConnection.getConnectToECOM();
            String sql = "SELECT * FROM ORDERS";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html lang=\"en\">");
            out.println("<head>");
            out.println("<link href=\"https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css\" rel=\"stylesheet\" />");
            out.println("<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css\">");
            out.println("<link rel=\"stylesheet\" href=\"style.css\" />");
            out.println("    <header class=\"header\">");
            out.println("        <nav>");
            out.println("            <img src=\"logo.png\" class=\"logob\">");
            out.println("            <ul>");
            out.println("                <li><a href=\"home.html\">HOME</a></li>");
            out.println("                <li><a href=\"category.html\">CATEGORIES</a></li>");
            out.println("                <li><a href=\"\">ABOUT US</a></li>");
            out.println("                <li><a href=\"\">CONTACT US</a></li>");
            out.println("            </ul>");
                        out.println("        </nav>");
            out.println("    </header>");
            out.println("    <meta charset=\"UTF-8\">");
            out.println("    <title>Order Page</title>");
            out.println("</head>");
            out.println("<body style=\"font-family: Arial, sans-serif; background-color: #f5f5f5; margin: 0; padding: 0;\">");
            out.println("    <div class=\"container\" style=\"width: 80%; max-width: 800px; margin: 20px auto; padding: 20px; background-color: #fff; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); border-radius: 8px;\">");
            out.println("        <h1 style=\"text-align: center; color: #333;\">All Orders List</h1>");
            out.println("        <table style=\"width: 100%; border-collapse: collapse; margin-top: 20px;\">");
            out.println("            <thead>");
            out.println("                <tr>");
            out.println("                    <th style=\"padding: 12px; text-align: left; background-color: #f2f2f2;\">Order ID</th>");
            out.println("                    <th style=\"padding: 12px; text-align: left; background-color: #f2f2f2;\">Customer ID</th>");
            out.println("                    <th style=\"padding: 12px; text-align: left; background-color: #f2f2f2;\">product ID</th>");
            out.println("                    <th style=\"padding: 12px; text-align: left; background-color: #f2f2f2;\">Product Name</th>");
            out.println("                    <th style=\"padding: 12px; text-align: left; background-color: #f2f2f2;\">Category</th>");
            out.println("                    <th style=\"padding: 12px; text-align: left; background-color: #f2f2f2;\">Price</th>");
            out.println("                    <th style=\"padding: 12px; text-align: left; background-color: #f2f2f2;\">Quantity</th>");
            out.println("                    <th style=\"padding: 12px; text-align: left; background-color: #f2f2f2;\">Address</th>");
            out.println("                </tr>");
            out.println("            </thead>");
            out.println("            <tbody>");

            while (rs.next()) {
                out.println("                <tr>");
           
                out.println("                    <td style=\"padding: 12px; text-align: left; border-bottom: 1px solid #ddd;\">" + rs.getInt("ORID") + "</td>");
                out.println("                    <td style=\"padding: 12px; text-align: left; border-bottom: 1px solid #ddd;\">" + rs.getInt("CID") + "</td>");
                out.println("                    <td style=\"padding: 12px; text-align: left; border-bottom: 1px solid #ddd;\">" + rs.getString("PID") + "</td>");
                out.println("                    <td style=\"padding: 12px; text-align: left; border-bottom: 1px solid #ddd;\">" + rs.getString("PNAME") + "</td>");
                out.println("                    <td style=\"padding: 12px; text-align: left; border-bottom: 1px solid #ddd;\">" + rs.getString("CATEGORY") + "</td>");
                out.println("                    <td style=\"padding: 12px; text-align: left; border-bottom: 1px solid #ddd;\">" + rs.getLong("PPRICE") + "</td>");
                out.println("                    <td style=\"padding: 12px; text-align: left; border-bottom: 1px solid #ddd;\">" + rs.getInt("QuANTITY") + "</td>");
                out.println("                    <td style=\"padding: 12px; text-align: left; border-bottom: 1px solid #ddd;\">" + rs.getString("ADDRESS") + "</td>");
                out.println("                </tr>");
            }

            out.println("        </tbody>");
            out.println("    </table>");
            
            out.println("    </div>");
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
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ViewodrBE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ViewodrBE.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ViewodrBE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ViewodrBE.class.getName()).log(Level.SEVERE, null, ex);
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
