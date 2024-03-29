package com.adidas.producer.kafka;

import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Validated
@Service
public class KafkaPublisher<T> {

    private final KafkaChannelProvider provider;

    public KafkaPublisher(KafkaChannelProvider provider) {
        this.provider = provider;
    }

    @Retryable
    public void publish(@Valid  T t) throws PublisherException {
        MessageChannel messageChannel = provider.provideForMessage(t);
        messageChannel.send(MessageBuilder.withPayload(t).build());
    }
}
