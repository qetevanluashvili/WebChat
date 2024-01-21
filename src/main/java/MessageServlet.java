
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/message")
public class MessageServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try (Connection connection = DatabaseManager.getConnection()) {
            if (DatabaseManager.isUserAuthenticated(username, password, connection)) {
                String userMessages = DatabaseManager.getUserMessages(username, connection);
                response.getWriter().write(userMessages);
            } else {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Authentication failed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String message = request.getParameter("message");

        try (Connection connection = DatabaseManager.getConnection()) {
            if (!MessageValidator.getInstance().isValidMessage(message) || !DatabaseManager.isUserExists(username, connection)) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid message or user does not exist");
            } else {
                DatabaseManager.saveMessage(username, message, connection);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
        }
    }


}

