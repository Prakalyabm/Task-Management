package com.Todo;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
@WebServlet("/signup")
public class AddUser extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String username = req.getParameter("username");
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String confirm_password = req.getParameter("confirm_password");

           
            User u = new User();
            u.setUsername(username);
            u.setEmail(email);
            u.setPassword(password);

            try (SessionFactory sf = new Configuration().configure().addAnnotatedClass(User.class).buildSessionFactory();
                 Session session = sf.openSession()) {

                Transaction transaction = session.beginTransaction();
                session.save(u);
                transaction.commit();

            } catch (Exception e) {
                e.printStackTrace(); 
               
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error processing signup: " + e.getMessage());
                return;
            }

            resp.sendRedirect("todo.jsp");

        } catch (Exception e) {
            e.printStackTrace(); 
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error processing signup: " + e.getMessage());
        }
    }
}
