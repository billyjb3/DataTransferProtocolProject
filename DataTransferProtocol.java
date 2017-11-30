/**
 * Created by User on 11/29/2017.
 */
public class DataTransferProtocol
{
    public static void main(String[] args)
    {
        Sender sender = new Sender();
        Receiver receiver = new Receiver();
        GUI gui = new GUI(sender, receiver);
    }
}
