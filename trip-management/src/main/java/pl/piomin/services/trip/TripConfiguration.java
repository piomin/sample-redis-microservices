package pl.piomin.services.trip;

import pl.piomin.services.trip.model.Trip;
import pl.piomin.services.trip.publish.TripPublisher;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;

@Configuration
public class TripConfiguration {

	@Bean
	LettuceConnectionFactory redisConnectionFactory() {
		return new LettuceConnectionFactory(new RedisStandaloneConfiguration("192.168.99.100", 6379));
	}

	@Bean
	RedisTemplate<String, Trip> redisTemplate() {
		RedisTemplate<String, Trip> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(redisConnectionFactory());
		return redisTemplate;
	}

	@Bean
	TripPublisher redisPublisher() {
		return new TripPublisher(redisTemplate(), topic());
	}

	@Bean
	ChannelTopic topic() {
		return new ChannelTopic("trips");
	}

}
