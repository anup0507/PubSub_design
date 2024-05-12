package com.src.anup.lld.public_facing;

import lombok.SneakyThrows;

import static java.lang.Thread.sleep;

public class SleepingSubscriber implements  ISubscriber{
    String SubscriberName;
    int sleepingtimeinmilliseconds;
    public SleepingSubscriber(String subscriberName,int sleepingtimeinmilliseconds) {
        SubscriberName = subscriberName;
        this.sleepingtimeinmilliseconds=sleepingtimeinmilliseconds;
    }
    @Override
    public String getSubscriberName() {
        return SubscriberName;
    }

    @SneakyThrows
    @Override
    public void ConsumeMessages(String message)  {
        System.out.println("Subscriber : " + SubscriberName + " is consuming message: " + message);

        Thread.sleep(sleepingtimeinmilliseconds);

        System.out.println("Subscriber : " + SubscriberName + " is done consuming message: " + message);

    }
}
