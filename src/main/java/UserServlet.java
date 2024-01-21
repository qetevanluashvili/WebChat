
import java.io.IOException;
import java.sql.Connection;;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try (Connection connection = DatabaseManager.getConnection()) {
            if (DatabaseManager.isUserExists(username, connection)) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "The entered username is already registered");
            } else {
                DatabaseManager.registerUser(username, password, connection);

                clearFormFields(response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
        }
    }

    private void clearFormFields(HttpServletResponse response) throws IOException {

        response.getWriter().write("<script>alert('You have successfully registered'); document.getElementById('username').value=''; document.getElementById('password').value='';</script>");
    }
}
