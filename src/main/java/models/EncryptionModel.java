package models;

import utils.EncryptionAlgorithm;
import utils.EncryptionAlgorithmName;
import utils.Message;
import utils.MessageType;

import javax.crypto.*;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class EncryptionModel {

    private File inputFile;
    private String outputDirectory;
    private EncryptionAlgorithm selectedEncryptionAlgorithm;
    private Message message;

    public void selectFile(){
        JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int r = j.showSaveDialog(null);

        if (r == JFileChooser.APPROVE_OPTION)
            inputFile = j.getSelectedFile();
    }

    public void selectOutputDirectory(){
        JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int r = j.showSaveDialog(null);

        if (r == JFileChooser.APPROVE_OPTION)
            outputDirectory = j.getSelectedFile().getAbsolutePath();
    }

    private Key generateAESKey(EncryptionAlgorithm encryptionAlgorithm) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(encryptionAlgorithm.name.toString());
            keyGenerator.init(encryptionAlgorithm.keyLength);
            SecretKey secretKey = keyGenerator.generateKey();
            saveSecretKey(secretKey);
            return secretKey;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Key generateRSAKey(EncryptionAlgorithm selectedEncryptionAlgorithm) {
        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(selectedEncryptionAlgorithm.keyLength);
            KeyPair pair = generator.generateKeyPair();
            saveSecretKey(pair.getPrivate());
            System.out.println(pair.getPublic().toString());
            return pair.getPublic();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void saveSecretKey(Key secretKey) {
        try (FileOutputStream fos = new FileOutputStream(outputDirectory + "/key.txt")) {
            fos.write(secretKey.getEncoded());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void encryptFile() {
        Path inputFilePath = Path.of(inputFile.getAbsolutePath());
        System.out.println(inputFile.getName()
        );
        File outputDecryptedFile = new File(outputDirectory + "/sifriran" + inputFile.getName());

        Key secretKey;
        if(selectedEncryptionAlgorithm.name == EncryptionAlgorithmName.AES)
            secretKey = generateAESKey(selectedEncryptionAlgorithm);

        else {
            secretKey = generateRSAKey(selectedEncryptionAlgorithm);

        }
        try(FileOutputStream outputStream = new FileOutputStream(outputDecryptedFile)) {
            Cipher cipher = Cipher.getInstance(selectedEncryptionAlgorithm.name.toString());
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            byte[] fileBytes = Files.readAllBytes(inputFilePath);
            byte[] encryptedFileBytes = cipher.doFinal(fileBytes);

            outputStream.write(encryptedFileBytes);
            message = new Message("Kriptiranje je bilo uspešno", MessageType.SUCCESS);
        } catch (InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException | NoSuchAlgorithmException | IOException | BadPaddingException e) {
            e.printStackTrace();
            message = new Message("Kriptiranje ni bilo uspešno", MessageType.ERROR);
        }
    }


    public void setInputFile(File inputFile) {
        this.inputFile = inputFile;
    }

    public void setOutputDirectory(String outputDirectory) {
        this.outputDirectory = outputDirectory;
    }

    public void setSelectedEncryptionAlgorithm(EncryptionAlgorithm selectedEncryptionAlgorithm) {
        this.selectedEncryptionAlgorithm = selectedEncryptionAlgorithm;
    }


    public File getInputFile() {
        return inputFile;
    }

    public String getOutputDirectory() {
        return outputDirectory;
    }

    public Message getMessage() {
        return message;
    }
}
