package pl.piomin.services.trip.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash("trip")
public class Trip implements Serializable {

	@Id
	private Long id;
	private Date startDate;
	@Indexed
	private Long driverId;
	@Indexed
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
		return "Trip{" +
				"id=" + id +
				", startDate=" + startDate +
				", driverId=" + driverId +
				", passengerId=" + passengerId +
				", status=" + status +
				'}';
	}
}
