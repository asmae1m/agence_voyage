package beans;

import javax.persistence.*;

@Entity
@Table(name = "Hebergement")
public class Hebergement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "nom_heberement")
	private String nom_hebergement;
	
	@ManyToOne
	@JoinColumn(name="voyage_id",referencedColumnName="id")
	private  Voyage voyage;
	
	public Hebergement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Hebergement(int id, String nom_hebergement) {
		super();
		this.id = id;
		this.nom_hebergement = nom_hebergement;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom_hebergement() {
		return nom_hebergement;
	}

	public void setNom_hebergement(String nom_hebergement) {
		this.nom_hebergement = nom_hebergement;
	}
	
	
	

}
