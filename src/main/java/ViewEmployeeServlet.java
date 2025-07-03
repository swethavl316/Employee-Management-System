import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;

@WebServlet("/ViewEmployeeServlet")
public class ViewEmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><head><title>All Employees</title>");
        out.println("<style>");
        out.println("body { font-family: Poppins, sans-serif; background: #f4f6f8; padding: 30px; }");
        out.println("table { width: 100%; border-collapse: collapse; margin-top: 30px; }");
        out.println("th, td { padding: 12px 20px; border-bottom: 1px solid #ddd; text-align: left; }");
        out.println("th { background-color: #2f80ed; color: white; }");
        out.println("tr:nth-child(even) { background-color: #f9f9f9; }");
        out.println("tr:hover { background-color: #e0eaff; }");
        out.println("a.button { text-decoration: none; padding: 6px 12px; background-color: #2f80ed; color: white; border-radius: 6px; margin-right: 5px; }");
        out.println("a.button:hover { background-color: #1c60c0; }");
        out.println("</style>");
        out.println("</head><body>");

        out.println("<h2>Employee List</h2>");

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeemanagmentsystem", "root", "Sana@1710");

            String sql = "SELECT * FROM employees";
            ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            out.println("<table>");
            out.println("<tr><th>ID</th><th>Name</th><th>Department</th><th>Salary</th><th>Actions</th></tr>");

            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String department = rs.getString("department");
                double salary = rs.getDouble("salary");

                out.println("<tr>");
                out.println("<td>" + id + "</td>");
                out.println("<td>" + name + "</td>");
                out.println("<td>" + department + "</td>");
                out.println("<td>₹" + salary + "</td>");
                out.println("<td>"
                        + "<a class='button' href='EditEmployeeServlet?id=" + id + "'>Edit</a>"
                        + "<a class='button' href='DeleteEmployeeServlet?id=" + id + "' onclick=\"return confirm('Are you sure you want to delete this employee?');\">Delete</a>"
                        + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");

        } catch (Exception e) {
            e.printStackTrace();
            out.println("<p>Error fetching employee data.</p>");
        } finally {
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }

        out.println("</body></html>");
    }
}


