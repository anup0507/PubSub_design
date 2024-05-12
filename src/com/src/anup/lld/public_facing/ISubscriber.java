package com.src.anup.lld.public_facing;

public interface ISubscriber {
    String getSubscriberName();
    void ConsumeMessages(String message);

}
