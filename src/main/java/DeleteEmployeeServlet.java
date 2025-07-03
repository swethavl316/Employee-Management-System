import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;

@WebServlet("/DeleteEmployeeServlet")
public class DeleteEmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (id == null || id.isEmpty()) {
            out.println("<p>Invalid Employee ID.</p>");
            return;
        }

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/employeemanagmentsystem", "root", "Sana@1710");

            String sql = "DELETE FROM employees WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);

            int rowsDeleted = ps.executeUpdate();

            if (rowsDeleted > 0) {
                response.sendRedirect("ViewEmployeeServlet"); // Redirect to updated employee list
            } else {
                out.println("<p>Error: Employee not found with ID " + id + ".</p>");
            }

        } catch (Exception e) {
            e.printStackTrace();
            out.println("<p>Error deleting employee.</p>");
        } finally {
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
    }
}
