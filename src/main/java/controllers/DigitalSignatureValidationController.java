package controllers;


import models.DigitalSignatureValidationModel;
import views.DigitalSignatureValidationView;

public class DigitalSignatureValidationController {
    private DigitalSignatureValidationView view;
    private DigitalSignatureValidationModel model;

    public DigitalSignatureValidationController(DigitalSignatureValidationView view, DigitalSignatureValidationModel model) {
        this.view = view;
        this.model = model;

        view.getSelectFileButton().addActionListener(e -> {
            model.selectFile();
            view.getSelectedFileLabel().setText(model.getFile().getAbsolutePath());
        });

        view.getSelectSignedFileButton().addActionListener(e -> {
            model.selectSignedFile();
            view.getSelectedSignedFileLabel().setText(model.getSignedFile().getAbsolutePath());
        });

        view.getSelectDigitalSignatureButton().addActionListener(e -> {
            model.selectDigitalSignatureFile();
            view.getSelectedDigitalSignatureLabel().setText(model.getDigitalSignatureFile().getAbsolutePath());
        });

        view.getValidateButton().addActionListener(e -> {
            model.setPassword(view.getPasswordTextField().getText());
            model.validateDigitalSignature();
            view.getMessageLabel().setMessage(model.getMessage());
        });
    }
}
