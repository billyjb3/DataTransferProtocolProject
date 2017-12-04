import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Created by User on 11/29/2017.
 */
public class Sender extends Transceiver implements Constants
{
    private byte currentSeq = (byte)1;

    public Sender(SystemContainer systemContainer)
    {
        super(systemContainer);
    }

    @Override
    public void sendMessage(byte[] packet)
    {
        sendLog.add(packet);
        gui.writeLineSender("Sent Packet: " + getBits(packet));
        receiver.receiveMessage(packet);
    }
    @Override
    public void receiveMessage(byte[] packet)
    {
        receiveLog.add(packet);
        gui.writeLineSender("received ACK: " + getBits(packet));
    }

    public void createMessage(String message)
    {
        currentSeq = 1;
        gui.writeLineSender("Message: " + message);
        try
        {
            byte[] bmess = message.getBytes("UTF-16LE");
            System.out.println(bmess.length);
            for(int i = 0; i < bmess.length; i += 2)
            {
                byte[] tb = new byte[3];
                tb[0] = currentSeq++;
                tb[1] = bmess[i];
                tb[2] = bmess[i+1];
                sendMessage(tb);
            }
        }
        catch(Exception e){e.printStackTrace();}
    }

}
