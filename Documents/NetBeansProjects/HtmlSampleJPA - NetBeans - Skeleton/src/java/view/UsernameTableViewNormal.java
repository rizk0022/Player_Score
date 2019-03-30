package view;

import logic.UsernameLogic;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entity.Username;

/**
 *
 * @author Shariar
 */
public class UsernameTableViewNormal extends HttpServlet {

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
            out.println("<title>Username Table Simple</title>");
            out.println("<style>\n"
                    + "h2 {text-align: center;\n"
                    + "font-family: \"Georgia\"}\n"
                    + "table {\n"
                    + "  border-collapse: collapse;\n"
                    + "  border: 3px ridge;\n"
                    + "  width: 50%;\n"
                    + "}\n"
                    + "\n"
                    + "tr:nth-child(even){background-color: #f2f2f2}\n"
                    + "tr:hover {background-color:#bec6e0;}\n"
                    + "\n"
                    + "th, td {\n"
                    + "  text-align: center;\n"
                    + "  padding: 8px;\n"
                    + "font-family: \"Georgia\"\n"
                    + "}\n"
                    + "\n"
                    + "th {\n"
                    + "  background-color: #3F7FBF;\n"
                    + "  color: white;\n"
                    + "}\n"
                    + "</style>");
            out.println("</head>");
            out.println("<body>");

            UsernameLogic logic = new UsernameLogic();
            List<Username> courses = logic.getAll();
            out.println("<table align=\"center\" border=\"1\">");
             out.println("<h2>Username Table</h2>");
            out.println("<tr>");
            out.println("<th>Player ID</th>");
            out.println("<th>Username</th>");
            out.println("</tr>");
            for (Username course : courses) {
                out.printf("<tr><td>%s</td><td>%s</td></tr>", course.getPlayerid(), course.getUsername());
            }
            
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
    }
}
