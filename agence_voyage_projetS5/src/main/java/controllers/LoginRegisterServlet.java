package controllers;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.hibernate.jpa.criteria.expression.function.CurrentDateFunction;
import org.hibernate.type.LocalDateTimeType;

import beans.*;
import dao.*;


/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet(urlPatterns = { "/LoginRegisterServlet", "/register","/logout","/login","/modifierVoy","/modifierVoyage","/supprimerVoy", "/ajoutVoyage", "/ajouteVoyage", "/listVoyages","/afficherVoyages"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class LoginRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession session;
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
		doPost(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TODO Auto-generated method stub
		
		session = request.getSession();
		
		
 
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
		client.setUser(user);
		clientDao.saveClient(client);
		
		
		
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
				
				Client client1  = clientDao.getClientByUserId(userDao.getUserId(user.getLogin(), user.getPassword()));
		        System.out.println("L'adresse de user avec ID:"+userDao.getUserId(user.getLogin(), user.getPassword())+ "est "+client1.getAdresse());
		        session.setAttribute("client1", client1);
				    
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
	
	if (request.getServletPath().equals("/ajoutVoyage")) {
		InputStream inputStream1=null;
		IVoyageImplDAO i=new IVoyageImplDAO();
		IThemesImplDao t=new IThemesImplDao();
		IActiviteeImplDao act=new IActiviteeImplDao();
		IHebergementImplDao heb=new IHebergementImplDao();
		Voyage voy =new Voyage();
		Part filePart1 = request.getPart("image");
		if (filePart1 != null ) {

			System.out.println(filePart1.getName());

			inputStream1 = filePart1.getInputStream();
			
		}
		voy.setDestination(request.getParameter("destination"));
		voy.setEndroit_depart(request.getParameter("depart"));
		voy.setDate_arrivee(request.getParameter("date_arrive"));
		voy.setDate_depart(request.getParameter("date_depart"));
		voy.setDuree(request.getParameter("duree"));
		voy.setPrix(Float.parseFloat(request.getParameter("prix")));
		voy.setType_voyage(request.getParameter("type"));
		voy.setImage(inputStream1.readAllBytes());
		String[] themes = request.getParameterValues("themes");
		String[] activitee = request.getParameterValues("activitee");
		
		int maison_hote=Integer.parseInt(request.getParameter("maison_hote"));
		int chalet=Integer.parseInt(request.getParameter("chalet"));
		int chambre_hotel=Integer.parseInt(request.getParameter("chambre_hotel"));
		i.saveVoyage(voy);
		for(int j=0;j<themes.length;j++) {
			Theme e=new Theme();
			e.setNom(themes[j]);
			e.setVoyage(voy);
			t.setTheme(e);
			
		}
		for(int j=0;j<activitee.length;j++) {
			Activite e=new Activite();
			e.setNom(activitee[j]);
			e.setVoyage(voy);
			act.setActivitee(e);
			
		}
		for(int j=0;j<chalet;j++) {
			Hebergement e=new Hebergement();
			e.setNom_hebergement("Chalet");
			e.setVoyage(voy);
			heb.setHeber(e);
			
		}
		for(int j=0;j<maison_hote;j++) {
			Hebergement e=new Hebergement();
			e.setNom_hebergement("Maison d'hote");
			e.setVoyage(voy);
			heb.setHeber(e);
			
		}
		for(int j=0;j<chambre_hotel;j++) {
			Hebergement e=new Hebergement();
			e.setNom_hebergement("Chambre hotel");
			e.setVoyage(voy);
			heb.setHeber(e);
			
		}
		request.getRequestDispatcher("/ajoutVoyage.jsp").forward(request, response);
		
	}
	if (request.getServletPath().equals("/ajouteVoyage")) {
		
		request.getRequestDispatcher("/ajoutVoyage.jsp").forward(request, response);
	}

    if (request.getServletPath().equals("/afficherVoyages")) {
		
    	IVoyageImplDAO i=new IVoyageImplDAO();
		ArrayList<Voyage> voy=new ArrayList<Voyage>();
		try {
			i.deleteOldTravels();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    voy = i.getVoyageList();
	
	    request.setAttribute("list", voy);
		request.getRequestDispatcher("/voyagesPage.jsp").forward(request, response);
	}
	
	if (request.getServletPath().equals("/supprimerVoy")) {
		IVoyageImplDAO i=new IVoyageImplDAO();
		int id_voy= Integer.parseInt(request.getParameter("id_voy"));
		i.deleteVoyage(id_voy);
		request.getRequestDispatcher("listVoyages").forward(request, response);
	}
	
	if (request.getServletPath().equals("/listVoyages")) {
		
		IVoyageImplDAO i=new IVoyageImplDAO();
		IThemesImplDao t=new IThemesImplDao();
		IActiviteeImplDao act=new IActiviteeImplDao();
		IHebergementImplDao heb=new IHebergementImplDao();
		
		ArrayList<Voyage> voy=new ArrayList<Voyage>();
		try {
			i.deleteOldTravels();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		voy = i.getVoyageList();
		
		for (int r=0;r<voy.size();r++) {
			voy.get(r).setThemes(t.getThemesById(voy.get(r).getId()));
			voy.get(r).setActivites(act.getActiviteById(voy.get(r).getId()));
		}
	    request.setAttribute("listVoyages", voy);
	
		request.getRequestDispatcher("/listVoyages.jsp").forward(request, response);
	}
    if (request.getServletPath().equals("/modifierVoy")) {
		
		IVoyageImplDAO i=new IVoyageImplDAO();
		int id_voy=Integer.parseInt(request.getParameter("id_voy"));
		Voyage voy=i.getVoyage(id_voy);
		
		request.setAttribute("voy", voy);
		request.setAttribute("id_voy", id_voy);
		request.getRequestDispatcher("/modifierVoy.jsp").forward(request, response);
	}
    
	if (request.getServletPath().equals("/modifierVoyage")) {
		
		IVoyageImplDAO i=new IVoyageImplDAO();
		int id_voy=Integer.parseInt(request.getParameter("id_voy"));
		Voyage voya =i.getVoyage(id_voy);
		voya.setDestination(request.getParameter("destination"));
		voya.setEndroit_depart(request.getParameter("depart"));
		voya.setDate_arrivee(request.getParameter("date_arrive"));
		voya.setDate_depart(request.getParameter("date_depart"));
		voya.setDuree(request.getParameter("duree"));
		voya.setPrix(Float.parseFloat(request.getParameter("prix")));
		i.updateVoyage(voya);
		request.getRequestDispatcher("/listVoyages").forward(request, response);
	}
	
		}
	
	}