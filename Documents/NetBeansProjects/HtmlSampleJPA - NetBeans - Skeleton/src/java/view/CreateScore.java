/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entity.Player;
import entity.Score;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.PlayerLogic;
import logic.ScoreLogic;

/**
 *
 * @author Youssef Rizk
 */
public class CreateScore extends HttpServlet {

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
            out.println("<form action=\"CreateScore\" method=\"post\">");
            out.println("Player ID:<br>");
            out.println("<input type=\"text\" name=\"id\" value=\"\"><br>");
            out.println("Score:<br>");
            out.println("<input type=\"text\" name=\"score\" value=\"\"><br><br>");
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
            ScoreLogic scoreLogic = new ScoreLogic();
            Score score = scoreLogic.createEntity(request.getParameterMap());

            PlayerLogic playerLogic = new PlayerLogic();
            Player player = playerLogic.getPlayerWithId(Integer.valueOf(request.getParameter(PlayerLogic.ID)));

            score.setPlayerid(player);
            player.getScoreList().add(score);
            playerLogic.update(player);

            response.sendRedirect("ScoreTableViewNormal");
        } catch (NullPointerException n) {
            PrintWriter out = response.getWriter();
            out.println("<script>alert(\"Wrong Entry!"+"\\n\\n"+"- Player ID should be already exist"+"\\n"+"- Both Player ID And Score shouldn't be Empty\");</script>");
        } catch (IllegalArgumentException n) {
            PrintWriter out = response.getWriter();
            out.println("<script>alert(\"Wrong Entry!"+"\\n\\n"+"- Score Should be only whole numbers AND less than 10 characters\");</script>");
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
        return "Create Score";
    }// </editor-fold>

}
