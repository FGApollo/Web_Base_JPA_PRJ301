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
@WebServlet(name="DeleteAccountServlet", urlPatterns={"/DeleteAccountServlet"})
public class DeleteAccountServlet extends HttpServlet {
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
        //1.get all user's infomation
        String username = request.getParameter("pk");
        String searchValue = request.getParameter("lastSearchvalue");
        String url = ERROR_PAGE;
        
        try  {
            //2.Controller call Methods 
            //2.1. New DAO object
            RegistrationBLO blo = new RegistrationBLO();
            
            //2.2. call method of DAO object
            boolean result = blo.deleteAccount(username);
            //3.Process
            if(result){
                //refresh --> call previous function function again
                // --> remind --> add request parameter into url based on how many input controller
                url ="DispatchServlet"
                    + "?btAction=Search"
                    + "&txtSearchValue=" + searchValue;
            }// delete is successful
//        }catch(SQLException ex){
//            log("SQL: " + ex.getMessage());
//        }catch(ClassNotFoundException ex){
//            log("Class not found: " + ex.getMessage());
        }finally{
            //forward
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
