package pl.piomin.services.passenger;

import pl.piomin.services.passenger.subscribe.PassengerSubscriber;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableRedisRepositories
public class PassengerConfiguration {

	@Autowired
	RedisConnectionFactory redisConnectionFactory;

	@Bean
	RedisMessageListenerContainer container() {
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.addMessageListener(messageListener(), topic());
		container.setConnectionFactory(redisConnectionFactory);
		return container;
	}

	@Bean
	MessageListenerAdapter messageListener() {
		return new MessageListenerAdapter(new PassengerSubscriber());
	}

	@Bean
	ChannelTopic topic() {
		return new ChannelTopic("trips");
	}

}
