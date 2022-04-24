package views;

import views.components.FormRow;
import views.components.MessageLabel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DigitalSignatureValidationView extends JPanel {
    JLabel title = new JLabel("Prevrjanje digitalnega podpisa");
    JLabel selectedFileLabel = new JLabel("Trenutno ni izbrana nobena datoteka");
    JButton selectFileButton = new JButton("Izberi");
    JLabel selectedDigitalSignatureLabel = new JLabel("Trenutno ni izbran noben digitalni podpis");
    JButton selectDigitalSignatureButton = new JButton("Izberi");
    JLabel selectedSignedFileLabel = new JLabel("Trenutno ni izbrana nobena podpisana datoteka");
    JButton selectSignedFileButton = new JButton("Izberi");
    JTextField passwordTextField = new JTextField(16);
    JButton validateButton = new JButton("Preveri");
    MessageLabel messageLabel = new MessageLabel();

    public DigitalSignatureValidationView(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JPanel p = new JPanel();
        p.setLayout(new FlowLayout(FlowLayout.LEFT));
        p.setMaximumSize(new Dimension(8000, 120));
        title.setFont(new Font("Dialog", Font.BOLD, 22));
        title.setBorder(new EmptyBorder( 30, 0, 20, 0));
        p.add(title);
        this.add(p);

        selectedFileLabel.setMinimumSize(new Dimension(500, 100));
        JPanel selectFileRow = new FormRow("Datoteka:", selectedFileLabel, selectFileButton);
        this.add(selectFileRow);

        JPanel selectDigitalSignatureRow = new FormRow("Digitalni podpis: ", selectedDigitalSignatureLabel, selectDigitalSignatureButton);
        this.add(selectDigitalSignatureRow);

        JPanel selectSignedFileRow = new FormRow("Digitalno odpisana datoteka: ", selectedSignedFileLabel, selectSignedFileButton);
        this.add(selectSignedFileRow);

        JPanel passwordRow = new FormRow("Geslo: ", passwordTextField);
        this.add(passwordRow);

        JPanel signButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        signButtonPanel.setBorder(new EmptyBorder(0, 0, 15, 0));
        signButtonPanel.setMaximumSize(new Dimension(8000, 100));
        signButtonPanel.add(validateButton);
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

    public JLabel getSelectedSignedFileLabel() {
        return selectedSignedFileLabel;
    }

    public JButton getSelectSignedFileButton() {
        return selectSignedFileButton;
    }

    public JTextField getPasswordTextField() {
        return passwordTextField;
    }

    public JButton getValidateButton() {
        return validateButton;
    }

    public MessageLabel getMessageLabel() {
        return messageLabel;
    }
}
