package models;

import javax.swing.*;
import java.awt.*;

public class MainPanelModel {

    private Panels currentPanel;

    public void switchToPageForEncryption(){
        currentPanel = Panels.ENCRYPTION_PANEL;
    }
    public void switchToPageForDecryption(){
        currentPanel = Panels.DECRYPTION_PANEL;
    }
    public void switchToPageForSigning(){
        currentPanel = Panels.SIGNING_PANEL;
    }
    public void switchToPageForValidation(){
        currentPanel = Panels.SIGNATURE_VALIDATION_PANEL;
    }

    public Panels getCurrentPanel() {
        return currentPanel;
    }
}
