package by.gourianova.binocularvision.main;

import by.gourianova.binocularvision.util.tetris.BVTetris;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/tetris")

public class IndexServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//TODO: think th logic of visiting pages

BVTetris bvTetris= new BVTetris();
bvTetris.run();
//           getServletContext().getRequestDispatcher("/ListOfApps/index").forward(req, resp); //if uncomment - the first page is registration

       //resp.sendRedirect(req.getContextPath() + "/ListOfApps/index");
        //resp.sendRedirect(req.getContextPath() + "/index");
    }
}