package controllers;

import models.MainPanelModel;
import views.MainPanelView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanelController {

    private MainPanelView view;
    private MainPanelModel model;

    public MainPanelController(MainPanelView view, MainPanelModel model) {
        this.view = view;
        this.model = model;

        view.addDecryptNavigationButtonListener(e -> {
            model.switchToPageForDecryption();
            view.setViewInsideContentPanel(model.getCurrentPanel());
        });

        view.addEncryptNavigationButtonListener(e -> {
            model.switchToPageForEncryption();
            view.setViewInsideContentPanel(model.getCurrentPanel());
        });

        view.addNavigationSignButtonListener(e -> {
            model.switchToPageForSigning();
            view.setViewInsideContentPanel(model.getCurrentPanel());
        });

        view.addNavigationValidationButtonListener(e -> {
            model.switchToPageForValidation();
            view.setViewInsideContentPanel(model.getCurrentPanel());
        });
    }
}
