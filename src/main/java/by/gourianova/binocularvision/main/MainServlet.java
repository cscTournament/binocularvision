package by.gourianova.binocularvision.main;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

import static by.gourianova.binocularvision.util.PageOfConstants.*;

@WebServlet("/index")
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Integer visitCounter = (Integer) session.getAttribute("visitCounter");
        if (visitCounter == null) {
            visitCounter = 1;
        } else {
            visitCounter++;

        }
        session.setAttribute("visitCounter", visitCounter);
        String username = req.getParameter("username");
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.write("<html>");
        out.write("<head>");
        out.write("<title>You are welcome!</title>");

        out.write("<style>"+
                "body {"+
           "background: url(../webapp/images/bv.jpg);"+
          "}"+
              "  hr{"+
              " background:\"lightgreensea\";"+
               " background-color:\"lightgreensea\"; height: 12px;"+
          "        }"+
         "</style>");
        out.write("</head>");
        out.write("<body>");
        String message=req.getParameter("message");
        //TODO: redone to get real name of authorized user
        if (username == null) {

            //TODO: to choose font
      out.write("<center>"+"<img src=\"logo_large.jpg\">"+"</center>");
            out.write("<br><br> " +
                    "<p style=\"font-family=glyphicon\">");
         //   out.write("<center>"+"<h3>"+"Good day, Anonymous" + "<br>");
            out.write("<p style=\"font-family=glyphicon\">"+"<br>"+"<h3>"+"<center>"+
                    "<p style=\"font-family=glyphicon\">"+"We represent the scope of medical apps for the training and rehabilitation of disorders of binocular"+
                   "and monocular vision"+
                    "<br> (including  strabismus  amblyopia), <br>"+
                    "prevention of these disorders in healthy individuals,&nbsp;"+
                    "as well as developing apps for the stimulation of sensory-motor mechanisms and improve coordination and logical thinking. <br> Our apps are effectively used for children of preschool and school age"+
                    "</center>"+"</font>"+"</h3>");

            out.write("<center>&nbsp;<h3> <hr  width=30%  color=\"#20B2AA\" ></h3></center>");
            //TODO: to change status?
            if (message==null) {
            out.write(" <p style=\"font-family=glyphicon\"> <center> before you'll got an app or contact, you should register<br><br></center> " +

                 "<center>"+ "<h3>" + "<a href=\"" + REGISTRATION_PAGE    + "\">&Rscr;egistration</a> " +  "</h3>" + "<center>"+"<br> " +
                    "<center>"+"<h3>" + "<a href=\"" + LOGIN_PAGE       + "\">&Lscr;ogin</a> " + "</h3>" + "</center>"+"<br> " +
                   "<center>"+"<h3>" + "<a href=\""+  FIRST_PAGE       +"\">&Lscr;ist of &Ascr;pps</a> " + "</h3>" + "</center>" +"</p>");

         }
      else{
          out.write("<h3>"+"<a href=\"ListOfApps/Controller?command=gotoindexpage&"+message+"\">&Cscr;ontinue</a> "+"</h3>"+"</center>");
        } }else {
            out.write("Good day, " + username + "<br>");
            out.write("Page was visited " +visitCounter + " times.");

            //TODO: to correct number of loading  this page
            //TODO: to stop application after 3 wrong entering of password
        }
        out.write("</body>");
        out.write("</html>");
        out.close();
    }
}