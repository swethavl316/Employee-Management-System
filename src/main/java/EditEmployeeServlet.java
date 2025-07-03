import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;

@WebServlet("/EditEmployeeServlet")
public class EditEmployeeServlet extends HttpServlet {
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
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/employeemanagmentsystem", "root", "Sana@1710");

            String sql = "SELECT * FROM employees WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);

            rs = ps.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                String department = rs.getString("department");
                double salary = rs.getDouble("salary");

                out.println("<html><head><title>Edit Employee</title></head><body>");
                out.println("<h2>Edit Employee</h2>");
                out.println("<form action='UpdateEmployeeServlet' method='post'>");

                out.println("<label for='id'>Employee ID:</label><br>");
                out.println("<input type='text' id='id' name='id' value='" + id + "' readonly><br><br>");

                out.println("<label for='name'>Name:</label><br>");
                out.println("<input type='text' id='name' name='name' value='" + name + "' required><br><br>");

                out.println("<label for='department'>Department:</label><br>");
                out.println("<input type='text' id='department' name='department' value='" + department + "' required><br><br>");

                out.println("<label for='salary'>Salary:</label><br>");
                out.println("<input type='number' step='0.01' id='salary' name='salary' value='" + salary + "' required><br><br>");

                out.println("<input type='submit' value='Update Employee'>");

                out.println("</form>");
                out.println("</body></html>");

            } else {
                out.println("<p>Employee not found for ID: " + id + "</p>");
            }

        } catch (Exception e) {
            e.printStackTrace();
            out.println("<p>Error fetching employee data.</p>");
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
    }
}

