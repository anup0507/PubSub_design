package com.src.anup.lld.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Topic {
    @Getter
    String TopicName;
    String TopicDescription;
    @Getter
    List<Messages> messages=new ArrayList<>();
    @Getter
    List<TopicSubscriber> subscribers=new ArrayList<>();
    public Topic(String topicName, String topicDescription) {
        TopicName = topicName;
        TopicDescription = topicDescription;
    }
    public synchronized void addMessage(Messages message)
    {
        messages.add(message);
    }
    public synchronized void addSubscriber(TopicSubscriber subscriber)
    {
        subscribers.add(subscriber);
    }

}
