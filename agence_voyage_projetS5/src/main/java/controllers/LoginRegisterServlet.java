package controllers;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Role;
import beans.*;
import dao.IClientImplDAO;
import dao.IUserImplDao;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet(urlPatterns = { "/LoginRegisterServlet", "/register","/logout","/login","/homeAdmin"})
public class LoginRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	IUserImplDao userDao = new IUserImplDao();
	IClientImplDAO clientDao = new IClientImplDAO();
       
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
		session.removeAttribute("user");
		System.out.println("the user is invalid now!");
		session.invalidate();
		System.out.println("la session est devenue invalide");
		request.getRequestDispatcher("/home.jsp").forward(request, response);
		
	}
	
	
	if (request.getServletPath().equals("/register")) {
		
		IUserImplDao userDao = new IUserImplDao();
		
		
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String nationalite = request.getParameter("nationalite");
		String adresse = request.getParameter("adresse");
		int codePostal = Integer.parseInt(request.getParameter("codePostal"));
		int telephone = Integer.parseInt(request.getParameter("telephone"));
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		
		
		User user = new User();
		Client client = new Client();
		
		client.setNom(nom);
		client.setPrenom(prenom);
		client.setNationalite(nationalite);
		client.setAdresse(adresse);
		client.setCodePostal(codePostal);
		client.setTelephone(telephone);
		client.setEmail(email);
		
		
		user.setLogin(username);
		user.setPassword(password);
		user.setRole(Role.Client);
		
		userDao.saveUser(user);
		clientDao.saveClient(client);
		System.out.println("ID USER : " +user.getId());
	    
	    
		
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
            	Client client = new Client();
            	session.setAttribute("client", user);
				System.out.print("you are a client!!!");
				System.out.println("YOU ARE HERE : "+request.getServletPath());
				session.setAttribute("client_nom", client.getNom());
				request.getRequestDispatcher("homeClient.jsp").forward(request, response);
				System.out.println("YOU ARE HERE : "+request.getServletPath());
				
             }
             else if (user.getRole().name().equals("admin")) {
 				session.setAttribute("admin", user);
 				System.out.print("you are an admin ");
 				request.getRequestDispatcher("/homeAdmin.jsp").forward(request, response);
             }
		    }
		    else {
            	 request.getRequestDispatcher("/error-404.jsp").forward(request, response);
             }
		    
	}
		    
		}
	}





	


