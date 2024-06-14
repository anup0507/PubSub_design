<b>PubSub_design</b>

In this project we design a publisher and subscriber kind of message queue using multiple threads such that no publishers are aware of subscribers and vice versa.

We have to design a message queue supporting publisher-subscriber model. It should support following operations:

1. It should support multiple topics where messages can be published.
2. Publisher should be able to publish a message to a particular topic.
3. Subscribers should be able to subscribe to a topic.
4. Whenever a message is published to a topic, all the subscribers, who are subscribed to that topic, should receive the message.
5. We should be able to reset the offset for the subscriber. That means the Subscriber then restarts reading again from that offset.
   -- It is like replying the messages again.
7. Subscribers should be able to run in parallel
