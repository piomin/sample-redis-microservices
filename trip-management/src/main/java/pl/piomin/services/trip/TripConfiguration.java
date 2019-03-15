package pl.piomin.services.trip;

import pl.piomin.services.trip.model.Trip;
import pl.piomin.services.trip.publish.TripPublisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableRedisRepositories
public class TripConfiguration {

	@Autowired
	RedisTemplate<?, ?> redisTemplate;

	@Bean
	TripPublisher redisPublisher() {
		return new TripPublisher(redisTemplate, topic());
	}

	@Bean
	ChannelTopic topic() {
		return new ChannelTopic("trips");
	}

}
