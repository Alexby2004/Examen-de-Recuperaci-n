/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import com.google.gson.Gson;
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

@WebServlet(name = "ListarMedicos", urlPatterns = {"/ListarMedicos"})
public class ListarMedicos extends HttpServlet {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_ExamenRecuperacion_war_1.0-SNAPSHOTPU");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        try {
            MedicosJpaController medicosController = new MedicosJpaController(emf);
            List<Medicos> lista = medicosController.findMedicosEntities();

            Gson gson = new Gson();
            String json = gson.toJson(lista);

            response.getWriter().write(json);
        } catch (Exception e) {
            response.setContentType("text/plain");
            response.getWriter().write("ERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
