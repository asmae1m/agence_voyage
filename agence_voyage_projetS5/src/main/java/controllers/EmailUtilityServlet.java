package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.IClientImplDAO;
import dao.SendEmail;
import beans.*;

/**
 * Servlet implementation class EmailUtilityServlet
 */
@WebServlet(urlPatterns = {"/EmailUtilityServlet","/sendEmail"})
public class EmailUtilityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession session;
	
	IClientImplDAO clientDao = new IClientImplDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmailUtilityServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		if (request.getServletPath().equals("/sendEmail")) {
			
			SendEmail send = new SendEmail();
		
		    String from = request.getParameter("email");
		    String password = request.getParameter("password");
			String sujet = request.getParameter("subject");
			String commentaire = request.getParameter("commentaire");
			send.sendMail(from,password,sujet,commentaire);
		
			response.sendRedirect("home.jsp");
		
			
			
		}
	}

}
