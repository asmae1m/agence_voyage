package beans;
import javax.persistence.*;

@Entity
@Table(name = "Client")
public class Client {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "nom")
	private String nom;
	
	@Column(name = "prenom")
	private String prenom;
	
	@Column(name = "adresse")
	private String adresse;
	
	@Column(name = "nationalite")
	private String nationalite;
	
	@Column(name = "telephone")
	private int telephone;
	
	@Column(name = "codePostal")
	private int codePostal;
	
	@Column(name = "email")
	private String email;
	
	@OneToOne
	@JoinColumn(name = "User_id", referencedColumnName = "id")
	private User user;
	
	@OneToOne (mappedBy = "client")
	private Panier panier;
	
	
	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Client(int id, String nom, String prenom, String adresse, String nationalite, int telephone) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.nationalite = nationalite;
		this.telephone = telephone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getNationalite() {
		return nationalite;
	}

	public void setNationalite(String nationalite) {
		this.nationalite = nationalite;
	}

	public int getTelephone() {
		return telephone;
	}

	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}

	public int getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
	
	
	
	
	

}
