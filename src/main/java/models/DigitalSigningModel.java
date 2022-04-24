package models;

import utils.EncryptionAlgorithm;
import utils.Message;
import utils.MessageType;
import utils.Pkcs12Util;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;

public class DigitalSigningModel {
    private File inputFile;
    private File inputDigitalSignature;
    private String outputDirectory;
    private String password;
    private Message message;

    public void selectFile(){
        JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int r = j.showSaveDialog(null);

        if (r == JFileChooser.APPROVE_OPTION)
            inputFile = j.getSelectedFile();
    }

    public void selectDigitalSignature(){
        JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int r = j.showSaveDialog(null);

        if (r == JFileChooser.APPROVE_OPTION)
            inputDigitalSignature = j.getSelectedFile();
    }

    public void selectOutputDirectory(){
        JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int r = j.showSaveDialog(null);

        if (r == JFileChooser.APPROVE_OPTION)
            outputDirectory = j.getSelectedFile().getAbsolutePath();
    }

    public void signFile(){
        File outputDecryptedFile = new File(outputDirectory + "/digitalno-podpisan-" + inputFile.getName());
        try (FileOutputStream outputStream = new FileOutputStream(outputDecryptedFile)){
            Pkcs12Util certificate = new Pkcs12Util(inputDigitalSignature, password);
            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initSign(certificate.getPrivateKey());

            byte[] messageBytes = Files.readAllBytes(Path.of(inputFile.getAbsolutePath()));

            signature.update(messageBytes);
            byte[] digitalSignature = signature.sign();
            outputStream.write(digitalSignature);
            Files.copy(Path.of(inputFile.getAbsolutePath()), Path.of(outputDirectory + inputFile.getName()), StandardCopyOption.REPLACE_EXISTING);
            message = new Message("Digitalno podpisovanje je bilo uspešno", MessageType.SUCCESS);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            message = new Message("Geslo digitalnega potrdila je napačno", MessageType.ERROR);
        }
    }

    public File getInputFile() {
        return inputFile;
    }

    public File getInputDigitalSignature() {
        return inputDigitalSignature;
    }

    public String getOutputDirectory() {
        return outputDirectory;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Message getMessage() {
        return message;
    }
}
