/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package thinhnhn.controller;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javax.persistence.PersistenceException;
import thinhnhn.model.Registration;
import thinhnhn.model.RegistrationBLO;
import thinhnhn.model.RegistrationCreateError;

/**
 *
 * @author FGApollo
 */
@WebServlet(name = "CreateAccountServlet", urlPatterns = {"/CreateAccountServlet"})
public class CreateAccountServlet extends HttpServlet {
    private final String ERROR_PAGE = "createNewAccount.jsp";
    private final String LOGIN_PAGE = "login.html";

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR_PAGE;
        

        //1. get all user information
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String fullName = request.getParameter("txtFullname");

        RegistrationCreateError errors = new RegistrationCreateError();
        boolean foundErr = false;

        try {
            //1.1 Verify user's errors
            if (username.trim().length() < 6 || username.trim().length() > 12) {
                foundErr = true;
                errors.setUsernameLengthErr(
                        "Username is required typing from 6 to 12 characters");
            }
            if (password.trim().length() < 8 || password.trim().length() > 20) {
                foundErr = true;
                errors.setPasswordLengthErr(
                        "Password is required typing from 8 to 20 characters");

            } else if (!confirm.trim().equals(password.trim())) {
                foundErr = true;
                errors.setConformNotMatch("Confirm must match Password");
            }

            if (fullName.trim().length() < 2 || fullName.trim().length() > 40) {
                foundErr = true;
                errors.setFullNameLengErr(
                        "fullName is required typing from 2 to 40 characters");
            }
            if (foundErr) {
                request.setAttribute("CREATE_ERROR", errors);
            } else {// no error
                //2. controller call method of model
                //2.1 new dao
                RegistrationBLO blo = new RegistrationBLO();
                //2.2 call method of dao object
                Registration account = new Registration(
                        username, password, fullName, false);
                boolean result = blo.createAccount(account);
                //3. process
                
                if(result){
                    url = LOGIN_PAGE;
                }
            }

        } catch (PersistenceException ex){
            String msg = ex.getMessage();
            log("SQL: " + msg);
            if(msg.contains("duplicate")){
                errors.setUsernameIsExisted(username + " is Existed");
                //khi get Attribute xuong thi phai set len lai
                request.setAttribute("CREATE_ERROR", errors);
            }
//        } catch (ClassNotFoundException ex){
//            log("Class Not Found: " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
