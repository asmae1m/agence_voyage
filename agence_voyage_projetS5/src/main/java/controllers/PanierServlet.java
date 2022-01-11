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
import dao.IHebergementImplDao;
import dao.IPanierImplDao;

/**
 * Servlet implementation class PanierServlet
 */
@WebServlet(urlPatterns = { "/ajoutPanier","/supprimerPanier","/supprimerRes","/reservations","/monPanier","/reserverVoyage", "/supprimerResAdmin", "/reservationsAdmin"})
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
		IHebergementImplDao h=new IHebergementImplDao();
		ArrayList<Voyage> voy=new ArrayList<Voyage>();
		
		int idClient=Integer.parseInt(request.getParameter("id_client"));
		/*try {
			i.deleteOldTravels();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		voy = i.getPanier(idClient);
		String zero="";
		if(voy.size()==0) {
			zero="Votre panier est vide !";
		}
		request.setAttribute("zero", zero);
		request.setAttribute("list", voy);
		request.getRequestDispatcher("/contacts.jsp").forward(request, response);
		
	}
	if (request.getServletPath().equals("/ajoutPanier")) {
		int idVoy=Integer.parseInt(request.getParameter("idVoy"));
		int idClient=Integer.parseInt(request.getParameter("id_client"));
		IPanierImplDao i=new IPanierImplDao();
		i.ajout_pan(idVoy, idClient);
		request.setAttribute("id_client", idClient);
		request.getRequestDispatcher("/afficherVoyages").forward(request, response);
		
	}
	if (request.getServletPath().equals("/reserverVoyage")) {
		int idVoy=Integer.parseInt(request.getParameter("idVoy"));
		int idClient=Integer.parseInt(request.getParameter("id_client"));
		IPanierImplDao i=new IPanierImplDao();
		i.reserverVoy(idVoy, idClient);
		request.setAttribute("id_client", idClient);
		request.getRequestDispatcher("/afficherVoyages").forward(request, response);
		
	}
	if (request.getServletPath().equals("/supprimerPanier")) {
		int idVoy=Integer.parseInt(request.getParameter("idVoy"));
		int idClient=Integer.parseInt(request.getParameter("id_client"));
		IPanierImplDao i=new IPanierImplDao();
		i.suprimmer_pan(idVoy, idClient);
		request.setAttribute("id_client", idClient);
		request.getRequestDispatcher("/afficherVoyages").forward(request, response);
		
	}
	if (request.getServletPath().equals("/supprimerRes")) {
		int idVoy=Integer.parseInt(request.getParameter("id_voy"));
		int idClient=Integer.parseInt(request.getParameter("id_client"));
		IPanierImplDao i=new IPanierImplDao();
		i.mod_pan(idVoy, idClient);
		request.setAttribute("id_client", idClient);
		request.getRequestDispatcher("/afficherVoyages").forward(request, response);
		
	}
	if (request.getServletPath().equals("/reservations")) {
		int idClient=Integer.parseInt(request.getParameter("id_client"));
		IPanierImplDao i=new IPanierImplDao();
		ArrayList<Voyage> voy=new ArrayList<Voyage>();
		voy=i.getVoyageReser(idClient);
		String zero="";
		if(voy.size()==0) {
			zero="La liste de Reservations est vide !";
		}
		request.setAttribute("zero", zero);
		request.setAttribute("voy", voy);
		request.setAttribute("id_client", idClient);
		request.getRequestDispatcher("/ReservationsClient.jsp").forward(request, response);
		
	}
	if (request.getServletPath().equals("/reservationsAdmin")) {
		IPanierImplDao i=new IPanierImplDao();
		ArrayList<Voyage> voy=new ArrayList<Voyage>();
		voy=i.getVoyageReserAdmin();
		String zero="";
		if(voy.size()==0) {
			zero="La liste de Reservations est vide !";
		}
		request.setAttribute("voy", voy);
		request.setAttribute("zero", zero);
		request.getRequestDispatcher("/listReserAdmin.jsp").forward(request, response);
		
	}
	if (request.getServletPath().equals("/supprimerResAdmin")) {
		int idVoy=Integer.parseInt(request.getParameter("id_voy"));
		String nomClient=request.getParameter("nom");
		IPanierImplDao i=new IPanierImplDao();
		int id=i.getClientId(nomClient);
		i.mod_pan(idVoy, id);
		request.setAttribute("id_client", id);
		request.getRequestDispatcher("/reservationsAdmin").forward(request, response);
		
	}
	}

}