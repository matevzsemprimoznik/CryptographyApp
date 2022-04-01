package views;

import controllers.DecryptionController;
import controllers.EncryptionController;
import models.DecryptionModel;
import models.EncryptionModel;
import models.Panels;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainPanelView extends JPanel {

    private JPanel navigationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel contentPanel = new JPanel();
    private JButton encryptNavigationButton = new JButton("Kriptiranje");
    private JButton decryptNavigationButton = new JButton("Dekriptiranje");
    private GridBagConstraints gbc = new GridBagConstraints();
    private CardLayout cl = new CardLayout();

    public MainPanelView(){
        BorderLayout borderLayout = new BorderLayout();
        this.setLayout(borderLayout);
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        navigationPanel.add(encryptNavigationButton);
        navigationPanel.add(decryptNavigationButton);

        this.add(navigationPanel, BorderLayout.NORTH);

        contentPanel.setLayout(cl);
        EncryptionView encryptionView = new EncryptionView();
        contentPanel.add(encryptionView, Panels.ENCRYPTION_PANEL.toString());
        EncryptionModel encryptionModel = new EncryptionModel();
        EncryptionController encryptionController = new EncryptionController(encryptionView, encryptionModel);

        DecryptionView decryptionView = new DecryptionView();
        contentPanel.add(decryptionView, Panels.DECRYPTION_PANEL.toString());
        DecryptionModel decryptionModel = new DecryptionModel();
        DecryptionController decryptionController = new DecryptionController(decryptionView, decryptionModel);

        cl.show(contentPanel, Panels.DECRYPTION_PANEL.toString());

        this.add(contentPanel, BorderLayout.CENTER);

    }

    public void setViewInsideContentPanel(Panels panelLabel){
        cl.show(contentPanel, panelLabel.toString());
    }

    public void addEncryptNavigationButtonListener(ActionListener actionListener){
        encryptNavigationButton.addActionListener(actionListener);
    }

    public void addDecryptNavigationButtonListener(ActionListener actionListener){
        decryptNavigationButton.addActionListener(actionListener);
    }
}
