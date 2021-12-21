package beans;

import java.util.List;


import javax.persistence.*;

@Entity
@Table(name = "Voyage")
public class Voyage {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "date_depart")
	private String date_depart;
	
	@Column(name = "date_arrivee")
	private String date_arrivee;
	
	@Column(name = "destination")
	private String destination;
	
	@Column(name = "duree")
	private String duree;
	
	@Column(name = "endroit_depart")
	private String endroit_depart;
	
	@Column(name = "prix")
	private float prix;
	
	@Column(name = "type_voyage")
	private String type_voyage;
	
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			
			name = "Reservation", 
			joinColumns = @JoinColumn(name = "Panier_id", referencedColumnName = "id"), 
			inverseJoinColumns = @JoinColumn(name = "voyage_id", referencedColumnName = "id"))
	
	private List <Panier> paniers;
	
	
	@OneToMany(mappedBy = "voyage")
	private List<Activite> activites;
	
	@OneToMany(mappedBy = "voyage")
	private List<Theme> themes;
	
	public List<Panier> getPaniers() {
		return paniers;
	}

	public void setPaniers(List<Panier> paniers) {
		this.paniers = paniers;
	}

	public List<Activite> getActivites() {
		return activites;
	}

	public void setActivites(List<Activite> activites) {
		this.activites = activites;
	}

	public List<Theme> getThemes() {
		return themes;
	}

	public void setThemes(List<Theme> themes) {
		this.themes = themes;
	}

	public List<Hebergement> getHebergements() {
		return hebergements;
	}

	public void setHebergements(List<Hebergement> hebergements) {
		this.hebergements = hebergements;
	}

	@OneToMany(mappedBy = "voyage")
	private List<Hebergement> hebergements;
	
	public Voyage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Voyage(int id, String date_depart, String date_arrivee, String destination, String duree,String endroit_depart, float prix, String type_voyage) {
		super();
		this.id = id;
		this.date_depart = date_depart;
		this.date_arrivee = date_arrivee;
		this.destination = destination;
		this.duree = duree;
		this.endroit_depart = endroit_depart;
		this.prix = prix;
		this.type_voyage = type_voyage;
	}
	
	

	public Voyage(int id, String destination, float prix) {
		super();
		this.id = id;
		this.destination = destination;
		this.prix = prix;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate_depart() {
		return date_depart;
	}

	public void setDate_depart(String date_depart) {
		this.date_depart = date_depart;
	}

	public String getDate_arrivee() {
		return date_arrivee;
	}

	public void setDate_arrivee(String date_arrivee) {
		this.date_arrivee = date_arrivee;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getDuree() {
		return duree;
	}

	public void setDuree(String duree) {
		this.duree = duree;
	}

	public String getEndroit_depart() {
		return endroit_depart;
	}

	public void setEndroit_depart(String endroit_depart) {
		this.endroit_depart = endroit_depart;
	}

	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	public String getType_voyage() {
		return type_voyage;
	}

	public void setType_voyage(String type_voyage) {
		this.type_voyage = type_voyage;
	}
	
	

}