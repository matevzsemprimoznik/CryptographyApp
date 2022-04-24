package views;

import views.components.FormRow;
import views.components.MessageLabel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DigitalSigningView extends JPanel {
    JLabel title = new JLabel("Digitalno podpisovanje datoteke");
    JLabel selectedFileLabel = new JLabel("Trenutno ni izbrana nobena datoteka");
    JButton selectFileButton = new JButton("Izberi");
    JLabel selectedDigitalSignatureLabel = new JLabel("Trenutno ni izbran noben digitalni podpis");
    JButton selectDigitalSignatureButton = new JButton("Izberi");
    JLabel selectedOutputDirectoryLabel = new JLabel("Treutno ni izbrana lokacija za hrambo dekriptirane datoteke");
    JButton selectOutputDirectoryButton = new JButton("Izberi");
    JTextField passwordTextField = new JTextField(16);
    JButton signButton = new JButton("Podpiši");
    MessageLabel messageLabel = new MessageLabel();

    public DigitalSigningView(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JPanel p = new JPanel();
        p.setLayout(new FlowLayout(FlowLayout.LEFT));
        p.setMaximumSize(new Dimension(8000, 120));
        title.setFont(new Font("Dialog", Font.BOLD, 22));
        title.setBorder(new EmptyBorder( 30, 0, 20, 0));
        p.add(title);
        this.add(p);

        selectedFileLabel.setMinimumSize(new Dimension(500, 100));
        JPanel selectFileRow = new FormRow("Datoteka za podpisovanje:", selectedFileLabel, selectFileButton);
        this.add(selectFileRow);

        JPanel selectDigitalSignatureRow = new FormRow("Digitalni podpis: ", selectedDigitalSignatureLabel, selectDigitalSignatureButton);
        this.add(selectDigitalSignatureRow);

        JPanel selectOutputDirectoryRow = new FormRow("Lokacija za shranjevanje: ", selectedOutputDirectoryLabel, selectOutputDirectoryButton);
        this.add(selectOutputDirectoryRow);

        JPanel passwordRow = new FormRow("Geslo: ", passwordTextField);
        this.add(passwordRow);

        JPanel signButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        signButtonPanel.setBorder(new EmptyBorder(0, 0, 15, 0));
        signButtonPanel.setMaximumSize(new Dimension(8000, 100));
        signButtonPanel.add(signButton);
        this.add(signButtonPanel);

        JPanel messagePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        messagePanel.add(messageLabel);
        this.add(messagePanel);
    }

    public JLabel getSelectedFileLabel() {
        return selectedFileLabel;
    }

    public JButton getSelectFileButton() {
        return selectFileButton;
    }

    public JLabel getSelectedDigitalSignatureLabel() {
        return selectedDigitalSignatureLabel;
    }

    public JButton getSelectDigitalSignatureButton() {
        return selectDigitalSignatureButton;
    }

    public JLabel getSelectedOutputDirectoryLabel() {
        return selectedOutputDirectoryLabel;
    }

    public JButton getSelectOutputDirectoryButton() {
        return selectOutputDirectoryButton;
    }

    public JButton getSignButton() {
        return signButton;
    }

    public JTextField getPasswordTextField() {
        return passwordTextField;
    }

    public MessageLabel getMessageLabel() {
        return messageLabel;
    }
}
