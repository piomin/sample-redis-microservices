package pl.piomin.services.trip.publish;

import pl.piomin.services.trip.model.Trip;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;

public class TripPublisher {

	RedisTemplate<?, ?> redisTemplate;
	ChannelTopic topic;

	public TripPublisher(RedisTemplate<?, ?> redisTemplate, ChannelTopic topic) {
		this.redisTemplate = redisTemplate;
		this.topic = topic;
	}

	public void publish(Trip trip) {
		redisTemplate.convertAndSend(topic.getTopic(), trip);
	}

}
