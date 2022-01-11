package controllers;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

import org.hibernate.jpa.criteria.expression.function.CurrentDateFunction;
import org.hibernate.type.LocalDateTimeType;

import beans.*;
import dao.*;


/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet(urlPatterns = { "/LoginRegisterServlet", "/register","/logout","/login","/modifierVoy","/modifierVoyage","/supprimerVoy", "/ajoutVoyage", "/ajouteVoyage", "/listVoyages","/afficherVoyages","/filterSearch","/consulterVoyage","/voyagesClient"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class LoginRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	HttpSession session;
	
	IUserImplDao userDao = new IUserImplDao();
	IClientImplDAO clientDao = new IClientImplDAO();
	IThemesImplDao themeDao = new IThemesImplDao();
	IActiviteeImplDao activiteDao = new IActiviteeImplDao();
	IVoyageImplDAO voyageDao = new IVoyageImplDAO();
	
       
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
		
		try {

			MessageDigest md = MessageDigest.getInstance("md5");
			byte[] hashInBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
			
			
			StringBuilder sb = new StringBuilder();
			
			for (byte b : hashInBytes) {
				sb.append(String.format("%02x", b));
			}
			System.out.println("le mot de passe après hachage est : "+sb.toString());
		
			
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
		user.setPassword(sb.toString());
		user.setRole(Role.Client);
		
		userDao.saveUser(user);
		client.setUser(user);
		clientDao.saveClient(client);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
        dispatcher.forward(request, response);
		
	
	}
	catch (NoSuchAlgorithmException e){
		System.err.println("Erreur: "+e.toString());
	}
	}
		
	if (request.getServletPath().equals("/login")) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		

	
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
		
    	int id_client= Integer.parseInt(request.getParameter("id_client"));
    	IVoyageImplDAO i=new IVoyageImplDAO();
    	IPanierImplDao p=new IPanierImplDao();
		ArrayList<Voyage> voy1= i.getVoyagesClient();
		ArrayList<Voyage> voy2= p.getPanier(id_client);
		ArrayList<Voyage> voy3= p.getVoyageReser(id_client);
		ArrayList<Voyage> voy= new ArrayList<Voyage>();
		for(int j=0;j<voy1.size();j++) {
			int c=0;
			for(int z=0;z<voy2.size();z++) {
				
				if(voy1.get(j).getId()==voy2.get(z).getId()) {
					c++;
				}
				
			}
				if(c==0)
					voy.add(voy1.get(j));
		}
		for(int j=0;j<voy.size();j++) {
			for(int z=0;z<voy3.size();z++) {
				
				if(voy.get(j).getId()==voy3.get(z).getId()) {
					voy.remove(j);
				}
				
			}
		}
		String zero="";
		if(voy.size()==0) {
			zero="La liste de voyages est vide !";
		}
		request.setAttribute("zero", zero);
		/*try {
			i.deleteOldTravels();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	    
	
	    request.setAttribute("list", voy);
		request.getRequestDispatcher("/voyagesPage.jsp").forward(request, response);
	}
    
    if (request.getServletPath().equals("/voyagesClient")) {
    	IVoyageImplDAO i=new IVoyageImplDAO();
    	IPanierImplDao p=new IPanierImplDao();
		ArrayList<Voyage> voy= i.getVoyagesClient();
		String zero="";
		if(voy.size()==0) {
			zero="La liste de voyages est vide !";
		}
		request.setAttribute("zero", zero);
		/*try {
			i.deleteOldTravels();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	    
	
	    request.setAttribute("list", voy);
		request.getRequestDispatcher("/voyagePageNonConn.jsp").forward(request, response);
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
	if (request.getServletPath().equals("/filterSearch")) {
		if (request.getServletPath().equals("/filterSearch")) {
			
			HashSet <Integer> voyages = new HashSet<Integer>();
			int id_client=Integer.parseInt(request.getParameter("id_client"));
			String theme= request.getParameter("theme");
			System.out.println("le theme est : "+theme);
			String activite= request.getParameter("activite");
			
			System.out.println("L'ACTIVITE EST:  "+activite);
			String destination= request.getParameter("destination");
			
			System.out.println("LA DESTINATION EST : "+destination);
			String Date_depart= request.getParameter("date_depart");
			
			System.out.println("LA DATE DE DEPART EST:  "+Date_depart);
			String duree = request.getParameter("duree");
		
			System.out.println("LA DUREE EST :  "+duree);
			
			String type = request.getParameter("type");
			System.out.println("LE TYPE DE VOYAGE EST :  "+type);
			
			themeDao.getIdVoy(themeDao.getThemeId(theme));
			
			System.out.println("ID DU THEME: "+themeDao.getThemeId(theme));
			
			activiteDao.getIdVoyDeAct(activiteDao.getActiviteId(activite));
			System.out.println("ID DE ACTIVITE : "+activiteDao.getActiviteId(activite));
			IVoyageImplDAO ii=new IVoyageImplDAO();
	    	IPanierImplDao p=new IPanierImplDao();
			ArrayList<Voyage> voy1= ii.getVoyagesClient();
			ArrayList<Voyage> voy22= p.getPanier(id_client);
			ArrayList<Voyage> voy33= p.getVoyageReser(id_client);
			ArrayList<Voyage> voy= new ArrayList<Voyage>();
			for(int j=0;j<voy1.size();j++) {
				int c=0;
				for(int z=0;z<voy22.size();z++) {
					
					if(voy1.get(j).getId()==voy22.get(z).getId()) {
						c++;
					}
					
				}
					if(c==0)
						voy.add(voy1.get(j));
			}
			for(int j=0;j<voy.size();j++) {
				for(int z=0;z<voy33.size();z++) {
					
					if(voy.get(j).getId()==voy33.get(z).getId()) {
						voy.remove(j);
					}
					
				}
			}
			String zero="";
			if(voy.size()==0) {
				zero="La liste de voyages est vide !";
			}
			request.setAttribute("zero", zero);
			
			if (!theme.equals("---")) {
				List <Voyage> voy11 = voyageDao.voyageParTheme(theme);
				System.out.println("voyage initial size : "+voy.size());
				int size=voy.size();
				System.out.println("voyage filtrer size : "+voy11.size());
				int i=0;
				for(int j=0;j<size;j++) {
					
					int c=0;
					for(int z=0;z<voy11.size();z++) {
						System.out.println("id voyage : "+voy.get(i).getId());
						System.out.println("id voyage : "+voy11.get(z).getId());
						if(voy.get(i).getId()==voy11.get(z).getId()) {
							c++;
						}
						
					}
						if(c==0) {
							voy.remove(voy.get(i));
						}else
							++i;
				}
				System.out.println("voyage filtrer size : "+voy.size());
				
			}
			if (!activite.equals("---")) {
				List <Voyage> voy2 = voyageDao.voyageParActivite(activite);
				System.out.println("voyage initial size : "+voy.size());
				int size=voy.size();
				System.out.println("voyage filtrer size : "+voy2.size());
				int i=0;
				for(int j=0;j<size;j++) {
					
					int c=0;
					for(int z=0;z<voy2.size();z++) {
						System.out.println("id voyage : "+voy.get(i).getId());
						System.out.println("id voyage : "+voy2.get(z).getId());
						if(voy.get(i).getId()==voy2.get(z).getId()) {
							c++;
						}
						
					}
						if(c==0) {
							voy.remove(voy.get(i));
						}else
							++i;
				}
				System.out.println("voyage filtrer size : "+voy.size());
			}
			if (!destination.equals("---")) {
				List <Voyage> voy3 = voyageDao.voyageParDesti(destination);
				System.out.println("voyage initial size : "+voy.size());
				int size=voy.size();
				System.out.println("voyage filtrer size : "+voy3.size());
				int i=0;
				for(int j=0;j<size;j++) {
					
					int c=0;
					for(int z=0;z<voy3.size();z++) {
						System.out.println("id voyage : "+voy.get(i).getId());
						System.out.println("id voyage : "+voy3.get(z).getId());
						if(voy.get(i).getId()==voy3.get(z).getId()) {
							c++;
						}
						
					}
						if(c==0) {
							voy.remove(voy.get(i));
						}else
							++i;
				}
				System.out.println("voyage filtrer size : "+voy.size());
			}
			if (!Date_depart.equals("")) {
				List <Voyage> voy4 = voyageDao.voyageParDateDepart(Date_depart);
				System.out.println("voyage initial size : "+voy.size());
				int size=voy.size();
				System.out.println("voyage filtrer size : "+voy4.size());
				int i=0;
				for(int j=0;j<size;j++) {
					
					int c=0;
					for(int z=0;z<voy4.size();z++) {
						System.out.println("id voyage : "+voy.get(i).getId());
						System.out.println("id voyage : "+voy4.get(z).getId());
						if(voy.get(i).getId()==voy4.get(z).getId()) {
							c++;
						}
						
					}
						if(c==0) {
							voy.remove(voy.get(i));
						}else
							++i;
				}
				System.out.println("voyage filtrer size : "+voy.size());
			}
			
			if (!duree.equals("---")) {
				List <Voyage> voy5 = voyageDao.voyageParDuree(duree);
				System.out.println("voyage initial size : "+voy.size());
				int size=voy.size();
				System.out.println("voyage filtrer size : "+voy5.size());
				int i=0;
				for(int j=0;j<size;j++) {
					
					int c=0;
					for(int z=0;z<voy5.size();z++) {
						System.out.println("id voyage : "+voy.get(i).getId());
						System.out.println("id voyage : "+voy5.get(z).getId());
						if(voy.get(i).getId()==voy5.get(z).getId()) {
							c++;
						}
						
					}
						if(c==0) {
							voy.remove(voy.get(i));
						}else
							++i;
				}
				System.out.println("voyage filtrer size : "+voy.size());
			}
			if (!type.equals("---")) {
				List <Voyage> voy6 = voyageDao.voyageParType(type);
				System.out.println("voyage initial size : "+voy.size());
				int size=voy.size();
				System.out.println("voyage filtrer size : "+voy6.size());
				int i=0;
				for(int j=0;j<size;j++) {
					
					int c=0;
					for(int z=0;z<voy6.size();z++) {
						System.out.println("id voyage : "+voy.get(i).getId());
						System.out.println("id voyage : "+voy6.get(z).getId());
						if(voy.get(i).getId()==voy6.get(z).getId()) {
							c++;
						}
						
					}
						if(c==0) {
							voy.remove(voy.get(i));
						}else
							++i;
				}
				System.out.println("voyage filtrer size : "+voy.size());
				
			}
			
		   
			request.setAttribute("list",voy);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/voyagesPage.jsp");
			dispatcher.forward(request, response);
		}
	}
if (request.getServletPath().equals("/consulterVoyage")) {
		
		int id_voy = Integer.parseInt(request.getParameter("id"));
		
		int id_act=Integer.parseInt(request.getParameter("id"));
		
		List<Theme> themes = themeDao.getThemeIdByVoy(id_voy);
		
		System.out.println("LA LISTE SIZE EST : "+themes.size());
		for (int r=0;r<themes.size();r++) {
			String nom=themes.get(r).getNom();
			System.out.println("LE NOM DE CHAQUE THEME est : "+nom);
			
		}
		List <Activite> activites = activiteDao.getActIdByVoy(id_voy);
		
		System.out.println("LA LISTE ACTIVITES SIZE EST : "+activites.size());
		for (int r=0;r<activites.size();r++) {
			String nom=activites.get(r).getNom();
			System.out.println("LE NOM DE CHAQUE ACT est : "+nom);
			
		}
		
		Voyage voyage = voyageDao.getVoyage(id_voy);
		System.out.println("THE VOYAGE WITH THIS ID IS : "+voyage.getDate_arrivee());
		
		request.setAttribute("voyage",voyage);
		request.setAttribute("themes",themes);
		request.setAttribute("activites",activites);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("consultVoyage.jsp");
		dispatcher.forward(request, response);
	
	}
	}
}
	
 	

	
	

