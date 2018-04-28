

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/myClass")
public class myClass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public myClass() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dbname = "jdbc:postgresql://localhost:5432/postgres";
		String driver = "org.postgresql.Driver";
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection con = null;
		try {
			con = DriverManager.getConnection(dbname, "postgres", "admin123");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Statement statement = null;
		try {
			statement = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String sql = "SELECT * FROM \"AIT\".users";
		ResultSet rs = null;
		try {
			rs = statement.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		PrintWriter out = response.getWriter();
		try {
			out.println("<table>");
			out.println("<tr><th>ID</th><th>Name</th><th>Lastname</th><tr>");
			while(rs.next())
			{
				out.println("<tr><td>" + rs.getString("ID") + "</td><td>" + rs.getString("Name") + "</td><td>" + rs.getString("Lastname") + "</td></tr>");
			}
			out.println("</table>");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
