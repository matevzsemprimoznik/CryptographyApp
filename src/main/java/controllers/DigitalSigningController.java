package controllers;

import models.DecryptionModel;
import models.DigitalSigningModel;
import utils.EncryptionAlgorithm;
import views.DecryptionView;
import views.DigitalSigningView;

public class DigitalSigningController {
    private DigitalSigningView view;
    private DigitalSigningModel model;

    public DigitalSigningController(DigitalSigningView view, DigitalSigningModel model) {
        this.view = view;
        this.model = model;

        view.getSelectFileButton().addActionListener(e -> {
            model.selectFile();
            view.getSelectedFileLabel().setText(model.getInputFile().getAbsolutePath());
        });

        view.getSelectOutputDirectoryButton().addActionListener(e -> {
            model.selectOutputDirectory();
            view.getSelectedOutputDirectoryLabel().setText(model.getOutputDirectory());
        });

        view.getSelectDigitalSignatureButton().addActionListener(e -> {
            model.selectDigitalSignature();
            view.getSelectedDigitalSignatureLabel().setText(model.getInputDigitalSignature().getAbsolutePath());
        });

        view.getSignButton().addActionListener(e -> {
            model.setPassword(view.getPasswordTextField().getText());
            model.signFile();
            view.getMessageLabel().setMessage(model.getMessage());
        });
    }


}
