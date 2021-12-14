package beans;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name="Reservation")
public class Reservation {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="date_reservation")
	private Date date_reservation;
	
	@Column(name="etat_reservation")
	private boolean etat_reservation;
	
	public Reservation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reservation(int id, Date date_reservation, boolean etat_reservation) {
		super();
		this.id = id;
		this.date_reservation = date_reservation;
		this.etat_reservation = etat_reservation;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate_reservation() {
		return date_reservation;
	}

	public void setDate_reservation(Date date_reservation) {
		this.date_reservation = date_reservation;
	}

	public boolean isEtat_reservation() {
		return etat_reservation;
	}

	public void setEtat_reservation(boolean etat_reservation) {
		this.etat_reservation = etat_reservation;
	}
	
	
	

}
