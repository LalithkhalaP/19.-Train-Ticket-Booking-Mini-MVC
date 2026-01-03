package ticket;
import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet({"/bookTicket", "/viewTicket"})
public class BookingServlet extends HttpServlet {

    Connection con;

    public void init() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/traindb",
                "root",
                "lalli@2007"
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void service(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        String path = req.getServletPath();

        try {
            if (path.equals("/bookTicket")) {

                PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO ticket(name,source,destination,travel_date) VALUES(?,?,?,?)"
                );

                ps.setString(1, req.getParameter("name"));
                ps.setString(2, req.getParameter("source"));
                ps.setString(3, req.getParameter("destination"));
                ps.setDate(4, Date.valueOf(req.getParameter("date")));

                ps.executeUpdate();

                res.sendRedirect(req.getContextPath() + "/viewTicket");
            }

            else if (path.equals("/viewTicket")) {

                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM ticket");

                out.println("<html><body>");
                out.println("<h2>Booked Tickets</h2>");
                out.println("<table border='1'>");
                out.println("<tr><th>ID</th><th>Name</th><th>Source</th><th>Destination</th><th>Date</th></tr>");

                while (rs.next()) {
                    out.println("<tr>");
                    out.println("<td>" + rs.getInt("id") + "</td>");
                    out.println("<td>" + rs.getString("name") + "</td>");
                    out.println("<td>" + rs.getString("source") + "</td>");
                    out.println("<td>" + rs.getString("destination") + "</td>");
                    out.println("<td>" + rs.getDate("travel_date") + "</td>");
                    out.println("</tr>");
                }

                out.println("</table>");
                out.println("<br>");
                out.println("<a href='" + req.getContextPath() + "/booking.jsp'>Book Another Ticket</a>");
                out.println("</body></html>");
            }

        } catch (Exception e) {
            out.println("<h3>Error occurred</h3>");
            e.printStackTrace(out);
        }
    }

    public void destroy() {
        try { con.close(); } catch (Exception e) {}
    }
}

