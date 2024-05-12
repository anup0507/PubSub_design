package com.src.anup.lld.public_facing;

import com.src.anup.lld.handlers.TopicHandler;
import com.src.anup.lld.model.Messages;
import com.src.anup.lld.model.Topic;
import com.src.anup.lld.model.TopicSubscriber;

import java.util.HashMap;
import java.util.Map;

public class pub_sub_client {
    Map<String, TopicHandler> topicHandlers=new HashMap<>();

    public void createTopic(String topicName,String topicDescription){
        Topic topic=new Topic(topicName,topicDescription);

        if(topicHandlers.containsKey(topicName)){
            return;
        }
        TopicHandler topichandler=new TopicHandler();
        topichandler.setTopic(topic);
        topicHandlers.put(topic.getTopicName(),topichandler);
    }
    public void subscribeToTopic(String topicName,ISubscriber subscriber){
        if(!topicHandlers.containsKey(topicName)){
            return;
        }
        TopicHandler topicHandler=topicHandlers.get(topicName);
        topicHandler.getTopic().getSubscribers().add(new TopicSubscriber(subscriber));

    }
    public void publishToTopic(String message,String topicName){
        if(!topicHandlers.containsKey(topicName)){
            return;
        }
        TopicHandler topichandler=topicHandlers.get(topicName);
        Topic topic=topichandler.getTopic();
        topic.addMessage(new Messages(message));

        new Thread(()->{
            topichandler.NotifySubscribers();
        }).start();

    }
    public void resetOffset(ISubscriber subscriber,int resetCountTo,String TopicName){
        if(!topicHandlers.containsKey(TopicName)){
            return;
        }
        TopicHandler topicHandler=topicHandlers.get(TopicName);
        for(TopicSubscriber topicSubscriber:topicHandler.getTopic().getSubscribers()){
            if(topicSubscriber.getSubscriber().getSubscriberName().equals(subscriber.getSubscriberName())){
                topicSubscriber.getOffsetCount().set(resetCountTo);
                System.out.println("Offset reset for subscriber: "+subscriber.getSubscriberName()+" to: "+resetCountTo);
                new Thread(()->{topicHandler.startSubscriberWorker(topicSubscriber);}).start();
            }
        }

    }
}
