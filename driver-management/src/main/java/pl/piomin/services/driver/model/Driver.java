package pl.piomin.services.driver.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("driver")
public class Driver {

	@Id
	private Long id;
	private String name;

	Long getId() {
		return id;
	}

	void setId(Long id) {
		this.id = id;
	}

	String getName() {
		return name;
	}

	void setName(String name) {
		this.name = name;
	}

}
