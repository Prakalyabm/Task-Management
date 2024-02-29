package com.Todo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    // Your other servlet code...

    private boolean isValidLogin(String email, String password) {
        try (SessionFactory sf = new Configuration().configure().addAnnotatedClass(User.class).buildSessionFactory();
             Session session = sf.openSession()) {

            // Retrieve the user from the database based on the provided email
            Query<User> query = session.createQuery("from User where email = :email", User.class);
            query.setParameter("email", email);
            User user = query.uniqueResult();

            // Check if the user exists and the password matches
            return user != null && user.getPassword().equals(password);

        } catch (Exception e) {
            e.printStackTrace(); // Log the exception or handle it as needed
            return false;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        // Validate login credentials
        if (isValidLogin(email, password)) {
            // Successful login, redirect or perform other actions
            resp.sendRedirect("todo.jsp");
        } else {
            // Unsuccessful login, use JavaScript to show a message
            resp.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = resp.getWriter()) {
                out.println("<script>alert('Invalid password. Please try again.');</script>");
                // You can also include additional logic or redirect the user to another login page
            }
        }
    }

    // Your other servlet code...


    private User getUserFromDatabase(String email) {
        try (SessionFactory sf = new Configuration().configure().addAnnotatedClass(User.class).buildSessionFactory();
             Session session = sf.openSession()) {

            // Query the database for the user with the provided email
            Query<User> query = session.createQuery("FROM User WHERE email = :email", User.class);
            query.setParameter("email", email);

            return query.uniqueResult();

        } catch (Exception e) {
            e.printStackTrace(); // Log the exception or handle it as needed
            return null;
        }
    }
}
