package com.src.anup.lld.model;

import com.src.anup.lld.public_facing.ISubscriber;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
public class TopicSubscriber {
    AtomicInteger OffsetCount;
    ISubscriber subscriber;
    public TopicSubscriber(ISubscriber subscriber) {
        this.subscriber = subscriber;
        this.OffsetCount=new AtomicInteger(0);
    }
}
