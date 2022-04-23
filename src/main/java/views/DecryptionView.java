package views;

import utils.EncryptionAlgorithm;
import views.components.FormRow;
import views.components.MessageLabel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DecryptionView extends JPanel {

    JLabel title = new JLabel("Dekriptiranje datoteke");
    JLabel selectedFileLabel = new JLabel("Trenutno ni izbrana nobena datoteka");
    JButton selectFileButton = new JButton("Izberi");
    JLabel selectedFileWithKeyLabel = new JLabel("Trenutno ni izbrana nobena datoteka s ključem");
    JButton selectFileWithKeyButton = new JButton("Izberi");
    JButton selectOutputDirectoryButton = new JButton("Izberi");
    JLabel selectedOutputDirectoryLabel = new JLabel("Treutno ni izbrana lokacija za hrambo dekriptirane datoteke");
    JComboBox<EncryptionAlgorithm> selectAlgorithmJComboBox = new JComboBox<>(EncryptionAlgorithm.values());
    JButton decryptButton = new JButton("Dekriptiraj");
    MessageLabel messageLabel = new MessageLabel();


    public DecryptionView(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JPanel p = new JPanel();
        p.setLayout(new FlowLayout(FlowLayout.LEFT));
        p.setMaximumSize(new Dimension(8000, 120));
        title.setFont(new Font("Dialog", Font.BOLD, 22));
        title.setBorder(new EmptyBorder( 30, 0, 20, 0));
        p.add(title);
        this.add(p);

        selectedFileLabel.setMinimumSize(new Dimension(500, 100));
        JPanel selectFileRow = new FormRow("Datoteka za dekriptiranje:", selectedFileLabel, selectFileButton);
        this.add(selectFileRow);

        JPanel selectFileWithKeyRow = new FormRow("Datoteka s klučem: ", selectedFileWithKeyLabel, selectFileWithKeyButton);
        this.add(selectFileWithKeyRow);

        JPanel selectOutputDirectoryRow = new FormRow("Lokacija za shranjevanje: ", selectedOutputDirectoryLabel, selectOutputDirectoryButton);
        this.add(selectOutputDirectoryRow);

        JPanel selectAlgorithmRow = new FormRow("Način dešifriranja: ", selectAlgorithmJComboBox);
        this.add(selectAlgorithmRow);

        JPanel decryptButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        decryptButtonPanel.setBorder(new EmptyBorder(0, 0, 15, 0));
        decryptButtonPanel.setMaximumSize(new Dimension(8000, 100));
        decryptButtonPanel.add(decryptButton);
        this.add(decryptButtonPanel);

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

    public JLabel getSelectedFileWithKeyLabel() {
        return selectedFileWithKeyLabel;
    }

    public JButton getSelectFileWithKeyButton() {
        return selectFileWithKeyButton;
    }

    public JButton getSelectOutputDirectoryButton() {
        return selectOutputDirectoryButton;
    }

    public JLabel getSelectedOutputDirectoryLabel() {
        return selectedOutputDirectoryLabel;
    }

    public JButton getDecryptButton() {
        return decryptButton;
    }

    public JComboBox<EncryptionAlgorithm> getSelectAlgorithmJComboBox() {
        return selectAlgorithmJComboBox;
    }

    public MessageLabel getMessageLabel() {
        return messageLabel;
    }
}
