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
    

    private boolean isValidLogin(String email, String password) {
        try (SessionFactory sf = new Configuration().configure().addAnnotatedClass(User.class).buildSessionFactory();
             Session session = sf.openSession()) {

            
            Query<User> query = session.createQuery("from User where email = :email", User.class);
            query.setParameter("email", email);
            User user = query.uniqueResult();

            
            return user != null && user.getPassword().equals(password);

        } catch (Exception e) {
            e.printStackTrace(); 
            return false;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

       
        if (isValidLogin(email, password)) {
           
            resp.sendRedirect("todo.jsp");
        } else {
            
            resp.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = resp.getWriter()) {
                out.println("<script>alert('Invalid password. Please try again.');</script>");
               
            }
        }
    }

    

    private User getUserFromDatabase(String email) {
        try (SessionFactory sf = new Configuration().configure().addAnnotatedClass(User.class).buildSessionFactory();
             Session session = sf.openSession()) {

            
            Query<User> query = session.createQuery("FROM User WHERE email = :email", User.class);
            query.setParameter("email", email);

            return query.uniqueResult();

        } catch (Exception e) {
            e.printStackTrace(); 
            return null;
        }
    }
}
