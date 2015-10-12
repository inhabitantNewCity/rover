package utils.massagesystem;



import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Владислав on 09.10.2015.
 */
public class MessageToRover implements Msg {
    private static ConcurrentLinkedQueue<Msg> queue;
    private Object text;
    public MessageToRover(Object text){
        this.text = text;
    }
    public static void initMessageToRover(ConcurrentLinkedQueue<Msg> queue){
        MessageToRover.queue = queue;
    }
    @Override
    public void send() {
        queue.add(this);
    }

    @Override
    public Object getText() {
        return text;
    }

    @Override
    public void setText(Object text) {
        this.text = text;
    }

}
