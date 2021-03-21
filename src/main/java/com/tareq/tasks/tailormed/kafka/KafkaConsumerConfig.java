/**
 * 
 */
package com.tareq.tasks.tailormed.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

/**
 * @author tareq
 *
 */
@EnableKafka
@Configuration
public class KafkaConsumerConfig {

	private String groupId = "producer";

	@Bean
	public ConsumerFactory<String, HCSVRecord> consumerFactory() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaProducerConfig.BOOTSTRAP_SERVER_URL);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		return new DefaultKafkaConsumerFactory<String, HCSVRecord>(props, new StringDeserializer(),
				new JsonDeserializer<>(HCSVRecord.class));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, HCSVRecord> csvRecordKafkaListenerContainerFactory() {

		ConcurrentKafkaListenerContainerFactory<String, HCSVRecord> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}

}
