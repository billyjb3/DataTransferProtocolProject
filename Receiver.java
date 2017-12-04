/**
 * Created by User on 11/29/2017.
 */
public class Receiver extends Transceiver implements Constants
{
    public Receiver(SystemContainer systemContainer)
    {
        super(systemContainer);
    }

    @Override
    public void sendMessage(byte[] packet)
    {
        byte[] ack = new byte[1];
        ack[0] = packet[0];
        gui.writeLineReceiver("sent ACK: " + getBits(ack));
        sender.receiveMessage(ack);
    }
    @Override
    public void receiveMessage(byte[] packet)
    {
        receiveLog.add(packet);
        gui.writeLineReceiver("received packet: " + getBits(packet));
        sendMessage(packet);
    }

}
