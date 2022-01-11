package controllers;

import java.io.IOException;
import javax.servlet.http.HttpSession;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.*;
import dao.IClientImplDAO;
import dao.IUserImplDao;

/**
 * Servlet implementation class ModifClient
 */
@WebServlet(urlPatterns = {"/ModifClient","/modifierInfos","/editInfos","/afficherInfos"})
public class ModifClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IUserImplDao userDao = new IUserImplDao();
	IClientImplDAO clientDao = new IClientImplDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifClient() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session;
		session = request.getSession();
		
		
        if (request.getServletPath().equals("/afficherInfos")) {
			
			request.getRequestDispatcher("/infosPersos.jsp").forward(request, response);
		}
		
		if (request.getServletPath().equals("/modifierInfos")) {
 	    	
 	    	System.out.println("YOU ARE HEREEEE : "+request.getServletPath());
 	   
 			int id = Integer.parseInt(request.getParameter("id").trim());
 			Client client  = clientDao.getClientById(id);
 			request.setAttribute("client2",client);
 			RequestDispatcher dispatcher = request.getRequestDispatcher("/modifierInfos.jsp");
 			dispatcher.forward(request, response);
 	    }
 	    
         else if (request.getServletPath().equals("/editInfos")) {
        	 
        	int id = Integer.parseInt(request.getParameter("id").trim()); 
        	
        	
 	        Client client = new Client();
 	    	String nom = request.getParameter("nom");
 	    	String prenom = request.getParameter("prenom");
 	    	String adresse = request.getParameter("adresse");
 	    	String nationalite = request.getParameter("nationalite");
 	    	int telephone = Integer.parseInt(request.getParameter("telephone"));
 	    	int codePostal = Integer.parseInt(request.getParameter("codePostal"));
 	    	String email  = request.getParameter("email");
 	    	
 	        int idUser = clientDao.getIdUserByIdClient(id);
 	    	System.out.println("LE user id de ce client est : "+clientDao.getIdUserByIdClient(id));
 	    	User user = userDao.getUserById(idUser);
 	    	
 	    	client.setUser(user);
 	    	client.setId(id);
 	    	client.setNom(nom);
 	    	client.setPrenom(prenom);
 	    	client.setAdresse(adresse);
 	    	client.setNationalite(nationalite);
 	    	client.setTelephone(telephone);
 	    	client.setCodePostal(codePostal);
 	    	client.setEmail(email);
 	    	
 	    	clientDao.updateClient(client);
 	    	session.setAttribute("client3",client);
 	    	System.out.println("CLIENT UPDATED");
 	    
 	    	response.sendRedirect("infosPersos.jsp");
 	    	 	   
 		}
		
	}

}