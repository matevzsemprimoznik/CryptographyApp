package controllers;

import models.EncryptionModel;
import utils.EncryptionAlgorithm;
import utils.EncryptionAlgorithmName;
import views.EncryptionView;
import views.components.MessageLabel;

public class EncryptionController {

    private EncryptionView view;
    private EncryptionModel model;

    public EncryptionController(EncryptionView view, EncryptionModel model) {
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

        view.getEncryptButton().addActionListener(e -> {
            EncryptionAlgorithm ea = (EncryptionAlgorithm) view.getSelectAlgorithmJComboBox().getSelectedItem();
            model.setSelectedEncryptionAlgorithm(ea);
            model.encryptFile();
            view.getMessageLabel().setMessage(model.getMessage());
        });

    }
}
