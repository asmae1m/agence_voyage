package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Role;
import beans.User;
import dao.IUserImplDao;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet(urlPatterns = { "/Servlet", "/register","/logout","/login"})
public class LoginRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	IUserImplDao userDao = new IUserImplDao();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginRegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/home.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		
	if (request.getServletPath().equals("/logout")) {
		request.getSession().invalidate();	
		request.getRequestDispatcher("/home.jsp").forward(request, response);
	}
	if (request.getServletPath().equals("/register")) {
		
		IUserImplDao userDao = new IUserImplDao();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		
		
		User user = new User();
		user.setLogin(username);
		user.setPassword(password);
		user.setEmail(email);
		user.setRole(Role.Client);
		
		userDao.saveUser(user);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
        dispatcher.forward(request, response);
		
	}
	if (request.getServletPath().equals("/login")) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		/*---------------------------------------Checking if username and password matched---------------------------------*/

		    if (userDao.login(username, password)==true) {

			User user = userDao.getUser(username, password);
			System.out.println("username" + user.getLogin());
			System.out.println("role: " + user.getRole());
			System.out.println("idUser: " + user.getId());

             if (user.getRole().name().equals("Client")) {
            	session.setAttribute("user", user);
				System.out.print("you are a client!!!");
				request.getRequestDispatcher("/home.jsp").forward(request, response);
			}
             if (user.getRole().name().equals("admin")) {
 				session.setAttribute("user", user);
 				System.out.print("you are an admin ");
 				request.getRequestDispatcher("/admin/homeAdmin.jsp").forward(request, response);
             }
		    }
             else {
            	 request.getRequestDispatcher("/error-404.jsp").forward(request, response);
             }
             
             
		    
			/*---------------------------------------Admin, Professeur or Etudiant ????---------------------------------*/

			/*if (user.getRole().name().equals("admin")) {
				session.setAttribute("admin", user);
				System.out.print("you are an admin Maaaan!!!");
				request.getRequestDispatcher("/homeAdmin.jsp").forward(request, response);

			} else if (user.getRole().name().equals("Client")) {
				
				System.out.print("you are a client!!!");
				request.getRequestDispatcher("/home.jsp").forward(request, response);
			}
			
		}
		else {
			System.out.println("erreur a albnt");*/
		}
	}
}


	


