import javax.swing.*;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by User on 11/29/2017.
 */
public class GUI extends JFrame implements Constants
{
    private SystemContainer systemContainer;
    private Sender sender;
    private Receiver receiver;

    private int width = 1200;
    private int height = width*9/16;
    private JPanel senderPanel;
    private JPanel receiverPanel;
    private JPanel inputPanel;

    private JScrollPane senderLog;
    private JTextArea senderText;

    private JScrollPane receiverLog;
    private JTextArea receiverText;

    private JTextField input;
    private JButton sendButton;

    private JRadioButton senderRandom;
    private JRadioButton ACKdrop;
    private JRadioButton senderNoDrop;
    private JRadioButton receiverRandom;
    private JRadioButton packetDrop;
    private JRadioButton receiverNoDrop;

    GUI(SystemContainer systemContainer)
    {
        this.systemContainer = systemContainer;//this is the only thing that should be here.
        // any other calls that need to be at constructor level go in the init() method.
    }
    public void init()
    {
        this.sender = systemContainer.getSender();
        this.receiver = systemContainer.getReceiver();

        createFrame();
        createSenderPanel();
        createReceiverPanel();
        createInputPanel();

        add(senderPanel);
        add(receiverPanel);
        add(inputPanel);
        setSize(width, height);
        setLocationRelativeTo(null);
    }

    public void writeLineSender(String line)
    {
        senderText.append(line + "\n");
    }
    public void writeLineReceiver(String line)
    {
        receiverText.append(line + "\n");
    }
    public int getSenderState()
    {
        if(senderRandom.isSelected())
            return SENDER_RANDOM;
        else if(ACKdrop.isSelected())
            return SENDER_ACKDROP;
        else if(senderNoDrop.isSelected())
            return SENDER_NODROP;
        return -1;
    }
    public int getReceiverState()
    {
        if(receiverRandom.isSelected())
            return RECEIVER_RANDOM;
        else if(packetDrop.isSelected())
            return RECEIVER_PACKETDROP;
        else if(receiverNoDrop.isSelected())
            return RECEIVER_NODROP;
        return -1;
    }

    private void createFrame()
    {
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.black);

        FlowLayout flayout = new FlowLayout();
        flayout.setHgap(0);
        flayout.setVgap(0);
        setLayout(flayout);
        setResizable(false);
    }
    private void createSenderPanel()
    {
        senderPanel = new JPanel();
        senderPanel.setPreferredSize(new Dimension(width/2 - 10, height/3*2));
        //senderPanel.setBorder(new BorderUIResource.LineBorderUIResource(Color.black));

        JLabel slabel = new JLabel("Sender");
        slabel.setPreferredSize(new Dimension(width/2-20, 15));
        slabel.setHorizontalAlignment(SwingConstants.CENTER);
        senderPanel.add(slabel);

        JPanel radioPanel = new JPanel();
        radioPanel.setLayout(new GridLayout(3,1));
        senderRandom = new JRadioButton("Random");
        ACKdrop = new JRadioButton("ACK Drop");
        senderNoDrop = new JRadioButton("No Drop");
        senderRandom.setSelected(true);
        radioPanel.add(senderRandom);
        radioPanel.add(ACKdrop);
        radioPanel.add(senderNoDrop);
        senderPanel.add(radioPanel);
        ButtonGroup senderRadioGroup = new ButtonGroup();
        senderRadioGroup.add(senderRandom);
        senderRadioGroup.add(ACKdrop);
        senderRadioGroup.add(senderNoDrop);

        senderText = new JTextArea((height/3*2-50)/15,  (width/4)/10);
        senderText.setEditable(false);
        senderLog = new JScrollPane(senderText);
        senderPanel.add(senderLog);
    }
    private void createReceiverPanel()
    {
        receiverPanel = new JPanel();
        receiverPanel.setPreferredSize(new Dimension(width/2 - 10, height/3*2));
        //receiverPanel.setBorder(new BorderUIResource.LineBorderUIResource(Color.blue));

        JLabel rlabel = new JLabel("Receiver");
        rlabel.setPreferredSize(new Dimension(width/2-20, 15));
        rlabel.setHorizontalAlignment(SwingConstants.CENTER);
        receiverPanel.add(rlabel);

        receiverText = new JTextArea((height/3*2-50)/15,  (width/4)/10);
        receiverText.setEditable(false);
        receiverLog = new JScrollPane(receiverText);
        receiverPanel.add(receiverLog);

        JPanel radioPanel = new JPanel();
        radioPanel.setLayout(new GridLayout(3,1));
        receiverRandom = new JRadioButton("Random");
        packetDrop = new JRadioButton("Drop Packet");
        receiverNoDrop = new JRadioButton("No Drop");
        receiverRandom.setSelected(true);
        radioPanel.add(receiverRandom);
        radioPanel.add(packetDrop);
        radioPanel.add(receiverNoDrop);
        ButtonGroup radioGroup = new ButtonGroup();
        radioGroup.add(receiverRandom);
        radioGroup.add(packetDrop);
        radioGroup.add(receiverNoDrop);
        receiverPanel.add(radioPanel);
    }
    private void createInputPanel()
    {
        inputPanel = new JPanel();
        inputPanel.setPreferredSize(new Dimension(width-20, height/3));
        //inputPanel.setBorder(new BorderUIResource.LineBorderUIResource(Color.green));
        FlowLayout ilayout = new FlowLayout();
        ilayout.setHgap(width/3*2);
        ilayout.setVgap(height/50);
        inputPanel.setLayout(ilayout);

        input = new JTextField();
        input.setPreferredSize(new Dimension(width/3, 20));
        sendButton = new JButton("Send");
        inputPanel.add(input);
        inputPanel.add(sendButton);

        JPanel protocolPanel = new JPanel();
        protocolPanel.setLayout(new GridLayout(1,2));
        JRadioButton goBackN = new JRadioButton("Go-Back-N");
        JRadioButton selectiveRepeat = new JRadioButton("Selective Repeat");
        goBackN.setSelected(true);
        protocolPanel.add(goBackN);
        protocolPanel.add(selectiveRepeat);
        ButtonGroup inputRadioGroup = new ButtonGroup();
        inputRadioGroup.add(goBackN);
        inputRadioGroup.add(selectiveRepeat);
        inputPanel.add(protocolPanel);

        sendButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(!input.getText().isEmpty())
                {
                    //senderText.append(input.getText());
                    sender.createMessage(input.getText());
                }
            }
        });
    }
}
