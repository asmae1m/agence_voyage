package beans;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "Panier")
public class Panier {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@OneToOne
	@JoinColumn(name = "client_id", referencedColumnName = "id")
	private Client client;
	
	@ManyToMany(mappedBy="paniers")
	private List<Voyage> voyages;
	
	public Panier() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Panier(int id) {
		super();
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	

}
