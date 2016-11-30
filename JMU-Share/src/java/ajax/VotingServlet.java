package ajax;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DBHandler;

/**
 *
 * @author greatwmc
 */
@WebServlet(name = "VotingServlet", urlPatterns = {"/voteUp"})
public class VotingServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param req servlet request
     * @param res servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String commentId = req.getParameter("id");
        DBHandler db = new DBHandler();
        int newScore = db.increaseScore(Integer.parseInt(commentId));

        String textin = req.getParameter("id");

        PrintWriter out = res.getWriter();
        res.setContentType("text/html");

        out.println("<h1>");

        out.println("You entered:</h1><p>" + textin.toUpperCase());

        out.println("</p>");

        out.close();

        log("Echoed: " + textin);
    }
    
    int score;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
    
    public String testing()
    {
        try
    {
        System.out.println("Trying to print");
        return "success";
    }
    catch(Exception e)
    {   
         return "error";
    } 
    }
}
