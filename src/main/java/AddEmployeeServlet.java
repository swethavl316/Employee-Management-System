
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;

@WebServlet("/AddEmployeeServlet")
public class AddEmployeeServlet extends HttpServlet {
 
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String department = request.getParameter("department");
        String salary = request.getParameter("salary");

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Ensure this driver is present
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeemanagmentsystem", "root", "Sana@1710");

            String sql = "INSERT INTO employees (id, name, department, salary) VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, id);
            stmt.setString(2, name);
            stmt.setString(3, department);
            stmt.setDouble(4, Double.parseDouble(salary));

            int rows = stmt.executeUpdate();

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            if (rows > 0) {
                out.println("<script>alert('Employee Added Successfully'); window.location.href='index.html';</script>");
            } else {
                out.println("<script>alert('Failed to Add Employee'); history.back();</script>");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("<h3>Error: " + e.getMessage() + "</h3>");
        } finally {
            try { if (stmt != null) stmt.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
    }
}

