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
        this.systemContainer = systemContainer;
        this.receiver = systemContainer.getReceiver();
        this.gui = systemContainer.getGUI();
    }
    public void sendMessage(String message)
    {

    }
}
