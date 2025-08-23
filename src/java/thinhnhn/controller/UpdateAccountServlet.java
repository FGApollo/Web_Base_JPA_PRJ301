/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package thinhnhn.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import thinhnhn.model.RegistrationBLO;
import thinhnhn.model.RegistrationDAO;

/**
 *
 * @author FGApollo
 */
@WebServlet(name="UpdateAccountServlet", urlPatterns={"/UpdateAccountServlet"})
public class UpdateAccountServlet extends HttpServlet {
   
    private final String ERROR_PAGE = "error.html";
    
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String role = request.getParameter("chkAdmin");
        String lastSearch = request.getParameter("lastSearchvalue");
        boolean isAdmin = role !=null;
        String url = ERROR_PAGE;
        
        try  {
            //2.controller call method of model
            //2.1 new dao object
            RegistrationBLO blo = new RegistrationBLO();
            
            boolean result = blo.updateAccount(username, password, isAdmin);
            
            if(result){
                url = "DispatchServlet"
                        + "?btAction=Search"
                        + "&txtSearchValue=" + lastSearch;
            }
            
//        }catch(SQLException ex){
//            log("SQL: "+ ex.getMessage());
//        }catch(ClassNotFoundException ex){
//            log("Class Not Found: "+ ex.getMessage());
        }finally{
            response.sendRedirect(url);
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
