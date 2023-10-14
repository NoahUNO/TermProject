import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/InsertServlet")
public class InsertServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public InsertServlet() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String courseName = request.getParameter("courseName");
      String courseDate = request.getParameter("courseDate");
      String courseProfessor = request.getParameter("courseProfessor");
      String courseGrade = request.getParameter("courseGrade");

      Connection connection = null;
      String insertSql = " INSERT INTO projectTable (id, COURSENAME, COURSEDATE, COURSEPROFESSOR, COURSEGRADE) values (default, ?, ?, ?, ?)";

      try {
         DBConnection.getDBConnection();
         connection = DBConnection.connection;
         PreparedStatement preparedStmt = connection.prepareStatement(insertSql);
         preparedStmt.setString(1, courseName);
         preparedStmt.setString(2, courseDate);
         preparedStmt.setString(3, courseProfessor);
         preparedStmt.setString(4, courseGrade);
         preparedStmt.execute();
         connection.close();
      } catch (Exception e) {
         e.printStackTrace();
      }

      // Set response content type
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Insert Data to DB table";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h2 align=\"center\">" + title + "</h2>\n" + //
            "<ul>\n" + //

            "  <li><b>Course Name</b>: " + courseName + "\n" + //
            "  <li><b>Course Date</b>: " + courseDate + "\n" + //
            "  <li><b>Course Professor</b>: " + courseProfessor + "\n" + //
            "  <li><b>Course Grade</b>: " + courseGrade + "\n" + //

            "</ul>\n");

      out.println("<a href=/termproject/search.html>Search Data</a> <br>");
      out.println("</body></html>");
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}
