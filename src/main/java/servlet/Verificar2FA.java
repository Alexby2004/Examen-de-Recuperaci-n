package servlet;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import dao.MedicosJpaController;
import dto.Medicos;
import java.io.IOException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "Verificar2FA", urlPatterns = {"/Verificar2FA"})
public class Verificar2FA extends HttpServlet {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_ExamenRecuperacion_war_1.0-SNAPSHOTPU");

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String codigo2fa = request.getParameter("codigo2fa");
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("medico") == null) {
            response.sendRedirect("login.html");
            return;
        }
        Medicos medico = (Medicos) session.getAttribute("medico");
        String secret = medico.getSecret2fa(); // Debes tener este campo en tu entidad

        GoogleAuthenticator gAuth = new GoogleAuthenticator();
        boolean isCodeValid = gAuth.authorize(secret, Integer.parseInt(codigo2fa));
        if (isCodeValid) {
            session.setAttribute("autenticado2fa", true);
            response.sendRedirect("tables.html");
        } else {
            response.sendRedirect("verificar2fa.html?error=1");
        }
    }
}