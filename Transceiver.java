import java.util.ArrayList;

/**
 * Created by User on 12/4/2017.
 */
public abstract class Transceiver
{
    protected SystemContainer systemContainer;
    protected Sender sender;
    protected Receiver receiver;
    protected GUI gui;
    protected ArrayList<byte[]> sendLog;
    protected ArrayList<byte[]> receiveLog;

    public Transceiver(SystemContainer systemContainer)
    {
        this.systemContainer = systemContainer;
        sendLog = new ArrayList<byte[]>();
        receiveLog = new ArrayList<byte[]>();
    }
    protected void init()
    {
        this.gui = systemContainer.getGUI();
        this.sender = systemContainer.getSender();
        this.receiver = systemContainer.getReceiver();
    }
    protected String getBits(byte[] packet)
    {
        int[] packets = new int[packet.length];
        String bits = new String();
        for(int i = 0; i < packet.length; i++)
        {
            packets[i] = packet[i];
            bits += String.format("%8s",Integer.toBinaryString(packets[i])).replace(' ', '0')+" ";
        }
        return bits;
    }
    public abstract void sendMessage(byte[] packet);
    public abstract void receiveMessage(byte[] packet);

}
