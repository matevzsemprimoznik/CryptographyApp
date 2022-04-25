package views;

import controllers.DecryptionController;
import controllers.DigitalSignatureValidationController;
import controllers.DigitalSigningController;
import controllers.EncryptionController;
import models.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainPanelView extends JPanel {

    private JPanel navigationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel contentPanel = new JPanel();
    private JButton encryptNavigationButton = new JButton("Kriptiranje");
    private JButton decryptNavigationButton = new JButton("Dekriptiranje");
    private JButton navigationSignButton = new JButton("Digitalno podpisovanje");
    private JButton navigationValidationButton = new JButton("Preverjanje podpisa");
    private CardLayout cl;

    public MainPanelView(){
        BorderLayout borderLayout = new BorderLayout();
        this.setLayout(borderLayout);
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        navigationPanel.add(encryptNavigationButton);
        navigationPanel.add(decryptNavigationButton);
        navigationPanel.add(navigationSignButton);
        navigationPanel.add(navigationValidationButton);

        this.add(navigationPanel, BorderLayout.NORTH);
        cl = new CardLayout();
        contentPanel.setLayout(cl);

        EncryptionView encryptionView = new EncryptionView();
        contentPanel.add(encryptionView, Panels.ENCRYPTION_PANEL.toString());
        EncryptionModel encryptionModel = new EncryptionModel();
        EncryptionController encryptionController = new EncryptionController(encryptionView, encryptionModel);

        DecryptionView decryptionView = new DecryptionView();
        contentPanel.add(decryptionView, Panels.DECRYPTION_PANEL.toString());
        DecryptionModel decryptionModel = new DecryptionModel();
        DecryptionController decryptionController = new DecryptionController(decryptionView, decryptionModel);

        DigitalSigningView digitalSigningView = new DigitalSigningView();
        contentPanel.add(digitalSigningView, Panels.SIGNING_PANEL.toString());
        DigitalSigningModel digitalSigningModel = new DigitalSigningModel();
        DigitalSigningController digitalSigningController = new DigitalSigningController(digitalSigningView, digitalSigningModel);

        DigitalSignatureValidationView digitalSignatureValidationView = new DigitalSignatureValidationView();
        contentPanel.add(digitalSignatureValidationView, Panels.SIGNATURE_VALIDATION_PANEL.toString());
        DigitalSignatureValidationModel signatureValidationModel = new DigitalSignatureValidationModel();
        DigitalSignatureValidationController digitalSignatureValidationController = new DigitalSignatureValidationController(digitalSignatureValidationView, signatureValidationModel);

        cl.next(contentPanel);
        cl.first(contentPanel);

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

    public void addNavigationSignButtonListener(ActionListener actionListener){
        navigationSignButton.addActionListener(actionListener);
    }

    public void addNavigationValidationButtonListener(ActionListener actionListener){
        navigationValidationButton.addActionListener(actionListener);
    }
}
