package views.components;

import utils.Message;
import utils.MessageType;

import javax.swing.*;
import java.awt.*;

public class MessageLabel extends JLabel {

    private Message message;

    public MessageLabel(){}

    private void setColor(MessageType messageType){
        if(messageType == MessageType.ERROR)
            this.setForeground(new Color(255, 55, 71));
        else if(messageType == MessageType.SUCCESS)
            this.setForeground(new Color(46, 204, 113));
    }

    public void setMessage(Message message) {
        this.message = message;
        this.setText(message.getText());
        setColor(message.getType());
    }

}
