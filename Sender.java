/**
 * Created by User on 11/29/2017.
 */
public class Sender implements Constants
{
    private SystemContainer systemContainer;
    private Receiver receiver;
    private GUI gui;

    public Sender(SystemContainer systemContainer)
    {
        this.systemContainer = systemContainer; //this is the only thing that should be here.
        // any other calls that need to be at constructor level go in the init() method.
    }
    public void init()
    {
        this.receiver = systemContainer.getReceiver();
        this.gui = systemContainer.getGUI();
    }
    public void sendMessage(String message)
    {
        gui.writeLineSender(message);
    }
}
