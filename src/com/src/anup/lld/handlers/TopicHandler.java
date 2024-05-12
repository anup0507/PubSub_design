package com.src.anup.lld.handlers;

import com.src.anup.lld.model.Topic;
import com.src.anup.lld.model.TopicSubscriber;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class TopicHandler {
    Topic topic;
    Map<String,SubscriberWorker> subscriberWorkermap= new HashMap<>();

    public void NotifySubscribers()
    {
        for(TopicSubscriber subscriber : topic.getSubscribers())
        {
            startSubscriberWorker(subscriber);
        }
    }
    public void startSubscriberWorker(TopicSubscriber subscriber)
    {
        SubscriberWorker worker;
        if(subscriberWorkermap.containsKey(subscriber.getSubscriber().getSubscriberName()))
        {
            worker=subscriberWorkermap.get(subscriber.getSubscriber().getSubscriberName());
        }
        else
        {
            worker=new SubscriberWorker(topic,subscriber);
            subscriberWorkermap.put(subscriber.getSubscriber().getSubscriberName(),worker);
            new Thread(worker).start();
        }
        worker.wakeUpifneeded();
    }
}
