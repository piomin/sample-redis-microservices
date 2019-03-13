package pl.piomin.services.trip.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("trip")
public class Trip {

	@Id
	private Long id;

	Long getId() {
		return id;
	}

	void setId(Long id) {
		this.id = id;
	}

}
