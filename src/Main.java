import com.src.anup.lld.public_facing.SleepingSubscriber;
import com.src.anup.lld.public_facing.pub_sub_client;
import lombok.SneakyThrows;

public class Main {
    @SneakyThrows
    public static void main(String[] args) {
        pub_sub_client client=new pub_sub_client();
        client.createTopic("t1","test topic 1");
        client.createTopic("t2","test topic 2");
        client.createTopic("t3","test topic 3");
        SleepingSubscriber s1=new SleepingSubscriber("s1",10000);
        SleepingSubscriber s2=new SleepingSubscriber("s2",5000);
        SleepingSubscriber s3=new SleepingSubscriber("s3",5000);
        client.subscribeToTopic("t1",s1);
        client.subscribeToTopic("t1",s2);

        client.publishToTopic("message 1","t1");
        client.publishToTopic("message 2","t1");
        client.publishToTopic("message 3","t1");
        Thread.sleep(10000);
        client.subscribeToTopic("t2",s3);
        client.publishToTopic("message 4","t2");
        client.publishToTopic("message 5","t2");
        client.publishToTopic("message 8","t2");
        client.publishToTopic("message 6","t1");
        client.publishToTopic("message 7","t1");
        Thread.sleep(30000);
        client.resetOffset(s1,0,"t1");

    }
}