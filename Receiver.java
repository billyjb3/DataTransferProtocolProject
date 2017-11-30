/**
 * Created by User on 11/29/2017.
 */
public class Receiver implements Constants
{
    private SystemContainer systemContainer;
    private Sender sender;
    private GUI gui;

    public Receiver(SystemContainer systemContainer)
    {
        this.systemContainer = systemContainer;//this is the only thing that should be here.
        // any other calls that need to be at constructor level go in the init() method.
    }
    public void init()
    {
        this.sender = systemContainer.getSender();
        this.gui = systemContainer.getGUI();
    }

}
