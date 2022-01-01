package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Reservation;
import beans.Voyage;
import dao.IPanierImplDao;

/**
 * Servlet implementation class PanierServlet
 */
@WebServlet(urlPatterns = { "/ajoutPanier","/monPanier"})
public class PanierServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession session;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PanierServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		session = request.getSession();
		
		
 
	if (request.getServletPath().equals("/monPanier")) {
		IPanierImplDao i=new IPanierImplDao();
		ArrayList<Voyage> voy=new ArrayList<Voyage>();
		int idClient=Integer.parseInt(request.getParameter("idClient"));
		/*try {
			i.deleteOldTravels();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		voy = i.getPanier(idClient);
		request.setAttribute("list", voy);
		request.getRequestDispatcher("/contacts.jsp").forward(request, response);
		
	}
	if (request.getServletPath().equals("/ajoutPanier")) {
		int idVoy=Integer.parseInt(request.getParameter("idVoy"));
		int idClient=Integer.parseInt(request.getParameter("idClient"));
		IPanierImplDao i=new IPanierImplDao();
		i.ajout_pan(idVoy, idClient);
		request.getRequestDispatcher("/afficherVoyages").forward(request, response);
		
	}
	}

}