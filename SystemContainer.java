/**
 * Created by User on 11/30/2017.
 */
public class SystemContainer
{
    private Sender sender;
    private Receiver receiver;
    private GUI gui;

    public SystemContainer()
    {
        sender = new Sender(this);
        receiver = new Receiver(this);
        gui = new GUI(this);
    }
    public Sender getSender()
    {
        return sender;
    }
    public Receiver getReceiver()
    {
        return receiver;
    }
    public GUI getGUI()
    {
        return gui;
    }
}
