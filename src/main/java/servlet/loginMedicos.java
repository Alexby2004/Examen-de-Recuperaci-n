/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import dao.MedicosJpaController;
import dto.Medicos;
import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;

@WebServlet(name = "loginMedicos", urlPatterns = {"/loginMedicos"})
public class loginMedicos extends HttpServlet {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_ExamenRecuperacion_war_1.0-SNAPSHOTPU");

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String ndniMedi = request.getParameter("ndniMedi");
        String passMedi = request.getParameter("passMedi");

        MedicosJpaController medicosController = new MedicosJpaController(emf);
        List<Medicos> lista = medicosController.findMedicosEntities();

        Medicos medicoLogueado = null;
        for (Medicos m : lista) {
            if (m.getNdniMedi().equals(ndniMedi) && BCrypt.checkpw(passMedi, m.getPassMedi())) {
                medicoLogueado = m;
                break;
            }
        }

        if (medicoLogueado != null) {
            // Guarda el médico en sesión (pero no logueado completamente)
            request.getSession().setAttribute("medico", medicoLogueado);
            // Redirige a la página de verificación 2FA
            response.sendRedirect("verificar2fa.html");
        } else {
            response.sendRedirect("login.html?error=1");
        }
    }
}
