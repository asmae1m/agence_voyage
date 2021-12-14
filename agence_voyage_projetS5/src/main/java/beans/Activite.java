package beans;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "Activite")
public class Activite {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "nom")
	private String nom;
	
	@ManyToOne
	@JoinColumn(name="voyage_id",referencedColumnName="id")
	private Voyage voyage;
	
	public Activite() {
		super();
	}

	public Activite(int id, String nom) {
		super();
		this.id = id;
		this.nom = nom;
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
	
}
