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
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
public class PlaceodrBE extends HttpServlet {

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
        String sourceDBURL = "jdbc:mysql://localhost:3306/ECOM";
        String destDBURL = "jdbc:mysql://localhost:3306/ECOM";
        String sourceDBUser = "root";
        String sourceDBPassword = "Jay@2003";
        String destDBUser = "root";
        String destDBPassword = "Jay@2003";
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            try (Connection sourceConn = DriverManager.getConnection(sourceDBURL, sourceDBUser, sourceDBPassword);
                    Connection destConn = DriverManager.getConnection(destDBURL, destDBUser, destDBPassword)) {

                String selectQuery = "SELECT * FROM CART";
                PreparedStatement selectStmt = sourceConn.prepareStatement(selectQuery);
                ResultSet resultSet = selectStmt.executeQuery();

                while (resultSet.next()) {
                    String insertQuery = "INSERT INTO ORDERS (PID, PNAME, CATEGORY, PPRICE, QUANTITY) VALUES (?, ?, ?, ?, ?)";
                    PreparedStatement insertStmt = destConn.prepareStatement(insertQuery);

                    // Assuming the columns are of type String here; adjust types as needed
                    insertStmt.setInt(1, resultSet.getInt("PID"));
                    insertStmt.setString(2, resultSet.getString("PNAME"));
                    insertStmt.setString(3, resultSet.getString("CATEGORY"));
                    insertStmt.setLong(4, resultSet.getLong("PPRICE"));
                    insertStmt.setInt(5, resultSet.getInt("QUANTITY"));
                    // Set other columns accordingly

                    insertStmt.executeUpdate();
                    insertStmt.close();
                }

                // Delete transferred data from the source database
                String deleteQuery = "DELETE FROM CART";
                PreparedStatement deleteStmt = sourceConn.prepareStatement(deleteQuery);
                deleteStmt.executeUpdate();
                int j = deleteStmt.executeUpdate();

                deleteStmt.close();

                resultSet.close();
                selectStmt.close();

                 out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Alert Example</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>YAYYYY!!!</h1>");
        out.println("<script>");
        out.println("    var confirmation = confirm('Login to Continue');");
        out.println("    if (confirmation) {");
        out.println("        window.location.href = 'userlogin.html';"); // Change the URL here
        out.println("    } else {");
        out.println("        window.location.href = 'PlaceodrBE.html';");
        out.println("    }");
        out.println("</script>");
        out.println("</body>");
        out.println("</html>");
            }
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
            Logger.getLogger(PlaceodrBE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PlaceodrBE.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(PlaceodrBE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PlaceodrBE.class.getName()).log(Level.SEVERE, null, ex);
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
