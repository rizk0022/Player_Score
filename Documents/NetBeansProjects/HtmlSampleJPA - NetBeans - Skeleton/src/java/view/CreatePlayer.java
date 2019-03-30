/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entity.Player;
import entity.Username;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.PlayerLogic;
import logic.UsernameLogic;

/**
 *
 * @author Youssef Rizk
 */
public class CreatePlayer extends HttpServlet {

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
            out.println("<title>Servlet Sample3Servlet</title>");
            /*https://www.w3schools.com/css/css_form.asp*/
            out.println("<style>\n"
                    + "input[type=text], select {\n"
                    + "  width: 100%;\n"
                    + "  padding: 12px 20px;\n"
                    + "  margin: 8px 0;\n"
                    + "  display: inline-block;\n"
                    + "  border: 1px solid #ccc;\n"
                    + "  border-radius: 4px;\n"
                    + "  box-sizing: border-box;\n"
                    + "}\n"
                    + "\n"
                    + "input[type=submit] {\n"
                    + "  width: 100%;\n"
                    + "  background-color: #4CAF50;\n"
                    + "  color: white;\n"
                    + "  padding: 14px 20px;\n"
                    + "  margin: 8px 0;\n"
                    + "  border: none;\n"
                    + "  border-radius: 4px;\n"
                    + "  cursor: pointer;\n"
                    + "}\n"
                    + "\n"
                    + "input[type=submit]:hover {\n"
                    + "  background-color: #45a049;\n"
                    + "}\n"
                    + "\n"
                    + "div {\n"
                    + "  border-radius: 5px;\n"
                    + "  background-color: #f2f2f2;\n"
                    + "  padding: 20px;\n"
                    + "}\n"
                    + "</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div style=\"text-align: center;\">");
            out.println("<div style=\"display: inline-block; text-align: left;\">");
            out.println("<form action=\"CreatePlayer\" method=\"post\">");
            out.println("ID:<br>");
            out.println("<input type=\"text\" name=\"id\" value=\"\"><br>");
            out.println("First name:<br>");
            out.println("<input type=\"text\" name=\"firstName\" value=\"\"><br>");
            out.println("Last name:<br>");
            out.println("<input type=\"text\" name=\"lastName\" value=\"\"><br>");
            out.println("Email:<br>");
            out.println("<input type=\"text\" name=\"email\" value=\"\"><br>");
            out.println("Username:<br>");
            out.println("<input type=\"text\" name=\"username\" value=\"\"><br><br>");
            out.println("<input type=\"submit\" value=\"Submit\">");
            out.println("</form>");
            out.println("<pre>");
            out.println("Submitted keys and values:");
            out.println(toStringMap(request.getParameterMap()));
            out.println("</pre>");
            out.println("</div>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    private String toStringMap(Map<String, String[]> values) {
        StringBuilder builder = new StringBuilder();
        values.forEach((k, v) -> builder.append("Key=").append(k)
                .append(", ")
                .append("Value/s=").append(Arrays.toString(v))
                .append(System.lineSeparator()));
        return builder.toString();
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
        try {
            PlayerLogic playerLogic = new PlayerLogic();
            UsernameLogic userNameLogic = new UsernameLogic();
            Username username = userNameLogic.createEntity(request.getParameterMap());
            Player player = playerLogic.createEntity(request.getParameterMap());

            playerLogic.update(player);

            username.setPlayer(player);
            username.setPlayerid(player.getId());
            player.setUsername(username);

            playerLogic.update(player);

            response.sendRedirect("PlayerTableViewNormal");

        } catch (NullPointerException n) {
            PrintWriter out = response.getWriter();
            out.println("<script>alert(\"Wrong Entry!"+"\\n\\n"+"- Name, ID and Username shouldn't be Empty"+"\\n"+"- Name should not exceeding 45 characters\");</script>");
        } catch (IllegalArgumentException n) {
            PrintWriter out = response.getWriter();
            out.println("<script>alert(\"Wrong Entry!"+"\\n\\n"+"- ID Should be only whole numbers"+"\\n"+"- Names should be only letters"+"\\n"+"- Email should be valid Email\");</script>");
        }
        processRequest(request, response);

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Create Player";
    }// </editor-fold>

}
