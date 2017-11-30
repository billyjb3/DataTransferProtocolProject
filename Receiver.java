/**
 * Created by User on 11/29/2017.
 */
public class Receiver
{
    private SystemContainer systemContainer;
    private Sender sender;
    private GUI gui;

    public Receiver(SystemContainer systemContainer)
    {
        this.systemContainer = systemContainer;
        this.sender = systemContainer.getSender();
        this.gui = systemContainer.getGUI();
    }

}
