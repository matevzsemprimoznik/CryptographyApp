package views;

import utils.EncryptionAlgorithm;
import views.components.FormRow;
import views.components.MessageLabel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class EncryptionView extends JPanel {
    
    JLabel title = new JLabel("Kriptiranje datoteke");
    JLabel selectedFileLabel = new JLabel("Trenutno ni izbrana nobena datoteka");
    JButton selectFileButton = new JButton("Izberi");
    JButton selectOutputDirectoryButton = new JButton("Izberi");
    JLabel selectedOutputDirectoryLabel = new JLabel("Treutno ni izbrana lokacija za hrambo ključa in kriptirane datoteke");
    JComboBox<EncryptionAlgorithm> selectAlgorithmJComboBox = new JComboBox<>(EncryptionAlgorithm.values());
    JButton encryptButton = new JButton("Kriptiraj");
    MessageLabel messageLabel = new MessageLabel();

    public EncryptionView(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JPanel p = new JPanel();
        p.setLayout(new FlowLayout(FlowLayout.LEFT));
        p.setMaximumSize(new Dimension(8000, 120));
        title.setFont(new Font("Dialog", Font.BOLD, 22));
        title.setBorder(new EmptyBorder( 30, 0, 20, 0));
        p.add(title);
        this.add(p);

        selectedFileLabel.setMinimumSize(new Dimension(500, 100));
        JPanel selectFileRow = new FormRow("Datoteka za kriptiranje:", selectedFileLabel, selectFileButton);
        this.add(selectFileRow);

        JPanel selectOutputDirectoryRow = new FormRow("Lokacija za shranjevanje: ", selectedOutputDirectoryLabel, selectOutputDirectoryButton);
        this.add(selectOutputDirectoryRow);

        JPanel selectAlgorithmRow = new FormRow("Način šifriranja: ", selectAlgorithmJComboBox);
        this.add(selectAlgorithmRow);

        JPanel encryptButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        encryptButtonPanel.setBorder(new EmptyBorder(0, 0, 15, 0));
        encryptButtonPanel.setMaximumSize(new Dimension(8000, 100));
        encryptButtonPanel.add(encryptButton);
        this.add(encryptButtonPanel);

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

    public JButton getSelectOutputDirectoryButton() {
        return selectOutputDirectoryButton;
    }

    public JLabel getSelectedOutputDirectoryLabel() {
        return selectedOutputDirectoryLabel;
    }

    public JButton getEncryptButton() {
        return encryptButton;
    }

    public JComboBox<EncryptionAlgorithm> getSelectAlgorithmJComboBox() {
        return selectAlgorithmJComboBox;
    }

    public MessageLabel getMessageLabel() {
        return messageLabel;
    }
}
