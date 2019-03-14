package pl.piomin.services.driver.model;

import java.io.Serializable;
import java.util.Date;

public class Trip implements Serializable {

	private Long id;
	private Date startDate;
	private Long driverId;
	private Long passengerId;
	private TripStatus status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Long getDriverId() {
		return driverId;
	}

	public void setDriverId(Long driverId) {
		this.driverId = driverId;
	}

	public Long getPassengerId() {
		return passengerId;
	}

	public void setPassengerId(Long passengerId) {
		this.passengerId = passengerId;
	}

	public TripStatus getStatus() {
		return status;
	}

	public void setStatus(TripStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Trip{" + "id=" + id + ", status=" + status + ", driverId=" + driverId + ", passengerId=" + passengerId + "}";
	}

}
