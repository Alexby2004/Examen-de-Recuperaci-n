/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package util;

import dao.MedicosJpaController;
import dao.exceptions.NonexistentEntityException;
import dto.Medicos;
import java.io.IOException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;

@WebServlet(name = "CrudMedicos", urlPatterns = {"/CrudMedicos"})
public class CrudMedicos extends HttpServlet {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_ExamenRecuperacion_war_1.0-SNAPSHOTPU");

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if ("editar".equals(accion)) {
            editarMedico(request, response);
        } else if ("nuevo".equals(accion)) {
            nuevoMedico(request, response);
        } else if ("eliminar".equals(accion)) {
            eliminarMedico(request, response);
        }
        // Puedes agregar más acciones aquí (eliminar, etc.)
    }

    private void editarMedico(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            int codiMedi = Integer.parseInt(request.getParameter("codiMedi"));
            String appaMedi = request.getParameter("appaMedi");
            String apmaMedi = request.getParameter("apmaMedi");
            String nombMedi = request.getParameter("nombMedi");
            String fechNaciMedi = request.getParameter("fechNaciMedi"); // formato: yyyy-MM-dd

            MedicosJpaController controller = new MedicosJpaController(emf);
            Medicos medico = controller.findMedicos(codiMedi);
            if (medico != null) {
                medico.setAppaMedi(appaMedi);
                medico.setApmaMedi(apmaMedi);
                medico.setNombMedi(nombMedi);
                medico.setFechNaciMedi(java.sql.Date.valueOf(fechNaciMedi));
                controller.edit(medico);
                response.getWriter().write("{\"success\":true}");
            } else {
                response.getWriter().write("{\"success\":false, \"error\":\"No encontrado\"}");
            }
        } catch (NonexistentEntityException e) {
            response.getWriter().write("{\"success\":false, \"error\":\"No existe el médico\"}");
        } catch (Exception e) {
            response.getWriter().write("{\"success\":false, \"error\":\"" + e.getMessage() + "\"}");
        }
    }

    private void nuevoMedico(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            String ndniMedi = request.getParameter("ndniMedi");
            String appaMedi = request.getParameter("appaMedi");
            String apmaMedi = request.getParameter("apmaMedi");
            String nombMedi = request.getParameter("nombMedi");
            String fechNaciMedi = request.getParameter("fechNaciMedi");
            String logiMedi = request.getParameter("logiMedi");
            String passMedi = request.getParameter("passMedi");
            String hashed = BCrypt.hashpw(passMedi, BCrypt.gensalt());

            if (ndniMedi == null || ndniMedi.isBlank() ||
                appaMedi == null || appaMedi.isBlank() ||
                apmaMedi == null || apmaMedi.isBlank() ||
                nombMedi == null || nombMedi.isBlank() ||
                fechNaciMedi == null || fechNaciMedi.isBlank() ||
                logiMedi == null || logiMedi.isBlank() ||
                passMedi == null || passMedi.isBlank()) {
                response.getWriter().write("{\"success\":false, \"error\":\"Todos los campos son obligatorios\"}");
                return;
            }

            MedicosJpaController controller = new MedicosJpaController(emf);
            Medicos medico = new Medicos();
            medico.setNdniMedi(ndniMedi);
            medico.setAppaMedi(appaMedi);
            medico.setApmaMedi(apmaMedi);
            medico.setNombMedi(nombMedi);
            medico.setFechNaciMedi(java.sql.Date.valueOf(fechNaciMedi));
            medico.setLogiMedi(logiMedi);
            medico.setPassMedi(hashed);
            controller.create(medico);
            response.getWriter().write("{\"success\":true}");
        } catch (Exception e) {
            response.getWriter().write("{\"success\":false, \"error\":\"" + e.getMessage() + "\"}");
        }
    }

    private void eliminarMedico(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            int codiMedi = Integer.parseInt(request.getParameter("codiMedi"));

            MedicosJpaController controller = new MedicosJpaController(emf);
            controller.destroy(codiMedi);
            response.getWriter().write("{\"success\":true}");
        } catch (NonexistentEntityException e) {
            response.getWriter().write("{\"success\":false, \"error\":\"No existe el médico\"}");
        } catch (Exception e) {
            response.getWriter().write("{\"success\":false, \"error\":\"" + e.getMessage() + "\"}");
        }
    }
}
