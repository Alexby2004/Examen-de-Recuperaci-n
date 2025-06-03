/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package util;

import dao.MedicosJpaController;
import dto.Medicos;
import java.io.IOException;
import java.io.PrintWriter;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Hakur
 */
@WebServlet(name = "CambiarPassword", urlPatterns = {"/CambiarPassword"})
public class CambiarPassword extends HttpServlet {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_ExamenRecuperacion_war_1.0-SNAPSHOTPU");

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CambiarPassword</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CambiarPassword at " + request.getContextPath() + "</h1>");
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
        response.setContentType("application/json;charset=UTF-8");
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("medico") == null) {
            response.getWriter().write("{\"success\":false, \"error\":\"No autenticado\"}");
            return;
        }

        Medicos medico = (Medicos) session.getAttribute("medico");
        String passActual = request.getParameter("passActual");
        String passNueva = request.getParameter("passNueva");

        if (!BCrypt.checkpw(passActual, medico.getPassMedi())) {
            response.getWriter().write("{\"success\":false, \"error\":\"Contraseña actual incorrecta\"}");
            return;
        }

        try {
            MedicosJpaController controller = new MedicosJpaController(emf);
            medico.setPassMedi(BCrypt.hashpw(passNueva, BCrypt.gensalt()));
            controller.edit(medico);
            // Actualiza el objeto en sesión
            session.setAttribute("medico", medico);
            response.getWriter().write("{\"success\":true, \"redirect\":\"verificar2fa.html\"}");
        } catch (Exception e) {
            response.getWriter().write("{\"success\":false, \"error\":\"" + e.getMessage() + "\"}");
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
