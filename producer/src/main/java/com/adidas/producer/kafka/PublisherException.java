package com.adidas.producer.kafka;

public class PublisherException extends Throwable {

    private final String type;

    public PublisherException(String type) {
        this.type = type;
    }
}
