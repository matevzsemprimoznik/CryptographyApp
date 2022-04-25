package models;

import utils.Message;
import utils.MessageType;
import utils.Pkcs12Util;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;

public class DigitalSignatureValidationModel {
    private File file;
    private File signedFile;
    private File digitalSignatureFile;
    private String password;
    private Message message;

    public void selectFile(){
        JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int r = j.showSaveDialog(null);

        if (r == JFileChooser.APPROVE_OPTION)
            file = j.getSelectedFile();
    }

    public void selectSignedFile(){
        JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int r = j.showSaveDialog(null);

        if (r == JFileChooser.APPROVE_OPTION)
            signedFile = j.getSelectedFile();
    }

    public void selectDigitalSignatureFile(){
        JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int r = j.showSaveDialog(null);

        if (r == JFileChooser.APPROVE_OPTION)
            digitalSignatureFile = j.getSelectedFile();
    }

    public void validateDigitalSignature(){
        try {
            Pkcs12Util certificate = new Pkcs12Util(digitalSignatureFile, password);
            Signature signature = Signature.getInstance("SHA256withRSA");

            signature.initVerify(certificate.getPublicKey());

            byte[] fileBytes = Files.readAllBytes(Path.of(file.getAbsolutePath()));
            byte[] signedFleBytes = Files.readAllBytes(Path.of(signedFile.getAbsolutePath()));
            signature.update(fileBytes);
            if(signature.verify(signedFleBytes))
                message = new Message("Podpis se ujema z datoteko", MessageType.SUCCESS);
            else
                message = new Message("Podpis se ne ujema z datoteko", MessageType.ERROR);

        } catch (IOException e) {
            e.printStackTrace();
            message = new Message("Prišlo je do napake pri nalaganju datoteke", MessageType.ERROR);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException | SignatureException e) {
            e.printStackTrace();
            message = new Message("Digitalni podpis ni pravi", MessageType.ERROR);
        }
    }

    public File getFile() {
        return file;
    }

    public File getSignedFile() {
        return signedFile;
    }

    public File getDigitalSignatureFile() {
        return digitalSignatureFile;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Message getMessage() {
        return message;
    }
}
