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
import java.util.List;
import thinhnhn.model.Registration;
import thinhnhn.model.RegistrationBLO;

/**
 *
 * @author FGApollo
 */
@WebServlet(name = "SearchLastNameServlet", urlPatterns = {"/SearchLastNameServlet"})
public class SearchLastNameServlet extends HttpServlet {

    //private final String SEARCH_PAGE = "search.html";
    private final String SEARCH_PAGE = "search.jsp";
    private final String SEARCH_RESULT = "search.jsp";

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
        //1.get all user's infomation
        String searchValue = request.getParameter("txtSearchValue");
        String url = SEARCH_PAGE;

        try {
            if (!searchValue.trim().isEmpty()) {
                //2.COntroller calls method of Model
                //2.1 new DAO
                RegistrationBLO blo = new RegistrationBLO();
                //2.2 call method of DAO object
                blo.searchLastName(searchValue);
                //3. process
                //dung parameter chi de tham chieu, dung attribute de chinh sua tuy
                //nhien cung chon attribute khi dang ow phia server
                
                List<Registration> result = blo.searchLastName(searchValue);
                url = SEARCH_RESULT;
                request.setAttribute("SEARCH_RESULT", result);
                
                
                
            }//user 

//        } catch (SQLException ex) {
//            log("SQL: " + ex.getMessage());
//
//        } catch (ClassNotFoundException ex) {
//            log("Class not found: "+ ex.getMessage());
        } finally {
            //minh can giu lai parameter de hien thi gia tri search, con neu su dung 
            //response.sendRedirect thi server se xoa toan bo request va response 
            //object
            RequestDispatcher rd = request.getRequestDispatcher(url);
            //Controller dang gui dia chi DTO den view
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
