import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;

@WebServlet("/UpdateEmployeeServlet")
public class UpdateEmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String department = request.getParameter("department");
        String salaryStr = request.getParameter("salary");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (id == null || name == null || department == null || salaryStr == null
                || id.isEmpty() || name.isEmpty() || department.isEmpty() || salaryStr.isEmpty()) {
            out.println("<p>All fields are required!</p>");
            return;
        }

        double salary = 0;
        try {
            salary = Double.parseDouble(salaryStr);
        } catch (NumberFormatException e) {
            out.println("<p>Invalid salary format.</p>");
            return;
        }

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/employeemanagmentsystem", "root", "Sana@1710");

            String sql = "UPDATE employees SET name = ?, department = ?, salary = ? WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, department);
            ps.setDouble(3, salary);
            ps.setString(4, id);

            int rowsUpdated = ps.executeUpdate();

            if (rowsUpdated > 0) {
                response.sendRedirect("ViewEmployeeServlet");  // go back to employee list
            } else {
                out.println("<p>Error: Employee with ID " + id + " not found.</p>");
            }

        } catch (Exception e) {
            e.printStackTrace();
            out.println("<p>Error updating employee.</p>");
        } finally {
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
    }
}
