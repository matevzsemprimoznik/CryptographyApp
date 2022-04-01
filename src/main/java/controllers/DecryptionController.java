package controllers;

import models.DecryptionModel;
import models.EncryptionModel;
import utils.EncryptionAlgorithm;
import views.DecryptionView;
import views.EncryptionView;

public class DecryptionController {

    private DecryptionView view;
    private DecryptionModel model;

    public DecryptionController(DecryptionView view, DecryptionModel model) {
        this.view = view;
        this.model = model;

        view.getSelectFileButton().addActionListener(e -> {
            model.selectFile();
            view.getSelectedFileLabel().setText(model.getInputFile().getAbsolutePath());
        });

        view.getSelectFileWithKeyButton().addActionListener(e -> {
            model.selectFileWithKey();
            view.getSelectedFileWithKeyLabel().setText(model.getInputFileWithKey().getAbsolutePath());
        });

        view.getSelectOutputDirectoryButton().addActionListener(e -> {
            model.selectOutputDirectory();
            view.getSelectedOutputDirectoryLabel().setText(model.getOutputDirectory());
        });

        view.getDecryptButton().addActionListener(e -> {
            EncryptionAlgorithm ea = (EncryptionAlgorithm) view.getSelectAlgorithmJComboBox().getSelectedItem();
            model.setSelectedEncryptionAlgorithm(ea);
            model.decryptFile();
            view.getMessageLabel().setMessage(model.getMessage());
        });
    }
}
