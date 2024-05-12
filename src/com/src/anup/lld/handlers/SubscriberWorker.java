package com.src.anup.lld.handlers;

import com.src.anup.lld.model.Messages;
import com.src.anup.lld.model.Topic;
import com.src.anup.lld.model.TopicSubscriber;
import lombok.SneakyThrows;

public class SubscriberWorker implements Runnable {
    Topic topic;
    TopicSubscriber topicSubscriber;
    public SubscriberWorker(Topic topic, TopicSubscriber topicSubscriber) {
        this.topic = topic;
        this.topicSubscriber = topicSubscriber;
    }

    @SneakyThrows
    @Override
    public void run() {
        synchronized (topicSubscriber)
        {
            do {

                int offsetCount=topicSubscriber.getOffsetCount().get();
                while(offsetCount>=topic.getMessages().size())
                {

                    topicSubscriber.wait();
                }
                Messages message=topic.getMessages().get(offsetCount);
                topicSubscriber.getSubscriber().ConsumeMessages(message.getMessage());
                topicSubscriber.getOffsetCount().compareAndSet(offsetCount,offsetCount+1);
            }
            while(true);

        }

    }
    public void wakeUpifneeded()
    {
        synchronized (topicSubscriber)
        {
            topicSubscriber.notify();
        }
    }

}
