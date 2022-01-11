package beans;
import javax.persistence.*;

@Entity
@Table(name="Theme")
public class Theme {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="nom")
	private String nom;
	
	@ManyToOne
	@JoinColumn(name="voyage_id",referencedColumnName="id")
	private Voyage voyage;
	
	public Voyage getVoyage() {
		return voyage;
	}
	public void setVoyage(Voyage voyage) {
		this.voyage = voyage;
	}
	public Theme() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Theme(int id, String nom) {
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