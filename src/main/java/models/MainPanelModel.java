package models;

import javax.swing.*;
import java.awt.*;

public class MainPanelModel {

    private Panels currentPanel = Panels.ENCRYPTION_PANEL;

    public void switchToPageForEncryption(){
        currentPanel = Panels.ENCRYPTION_PANEL;
    }
    public void switchToPageForDecryption(){
        currentPanel = Panels.DECRYPTION_PANEL;
    }

    public Panels getCurrentPanel() {
        return currentPanel;
    }
}
