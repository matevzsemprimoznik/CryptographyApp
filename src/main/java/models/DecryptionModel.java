package models;

import utils.EncryptionAlgorithm;
import utils.EncryptionAlgorithmName;
import utils.Message;
import utils.MessageType;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class DecryptionModel {

    private File inputFile;
    private File inputFileWithKey;
    private String outputDirectory;
    private EncryptionAlgorithm selectedEncryptionAlgorithm;
    private Message message;


    public void selectFile(){
        JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int r = j.showSaveDialog(null);

        if (r == JFileChooser.APPROVE_OPTION)
            inputFile = j.getSelectedFile();
    }

    public void selectFileWithKey(){
        JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int r = j.showSaveDialog(null);

        if (r == JFileChooser.APPROVE_OPTION)
            inputFileWithKey = j.getSelectedFile();
    }

    public void selectOutputDirectory(){
        JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int r = j.showSaveDialog(null);

        if (r == JFileChooser.APPROVE_OPTION)
            outputDirectory = j.getSelectedFile().getAbsolutePath();
    }

    private PrivateKey readRSAKey(){
        try {
            byte[] publicKeyBytes = Files.readAllBytes(inputFileWithKey.toPath());
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            EncodedKeySpec publicKeySpec = new PKCS8EncodedKeySpec(publicKeyBytes);
            PrivateKey key = keyFactory.generatePrivate(publicKeySpec);
            System.out.println(key);
            return key;
        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }

        return null;
    }

    private SecretKey readAESKey(int keyLength){
        try(FileInputStream fin = new FileInputStream(inputFileWithKey)) {
            byte[] keybyte = new byte[keyLength / 8];
            fin.read(keybyte);
            return new SecretKeySpec(keybyte, 0, keyLength / 8, "AES");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void decryptFile(){
        Path inputFilePath = Path.of(inputFile.getAbsolutePath());
        File outputFile = new File(outputDirectory + "/decrypted.txt");

        Key key;
        if(selectedEncryptionAlgorithm.name == EncryptionAlgorithmName.AES)
            key = readAESKey(selectedEncryptionAlgorithm.keyLength);
        else
            key = readRSAKey();

        try(FileOutputStream outputStream = new FileOutputStream(outputFile)){
            Cipher cipher = Cipher.getInstance(selectedEncryptionAlgorithm.name.toString());
            cipher.init(Cipher.DECRYPT_MODE, key);

            byte[] encryptedFileBytes = Files.readAllBytes(inputFilePath);
            byte[] decryptedFileBytes = cipher.doFinal(encryptedFileBytes);

            outputStream.write(decryptedFileBytes);
            message = new Message("Dekriptiranje je bilo uspešno", MessageType.SUCCESS);
        } catch (BadPaddingException e) {
            message = new Message("Nimate pravega ključa", MessageType.ERROR);
            e.printStackTrace();
        } catch (InvalidKeyException | NoSuchAlgorithmException | IllegalBlockSizeException | NoSuchPaddingException | IOException e) {
            e.printStackTrace();
            message = new Message("Kriptiranje ni bilo uspešno", MessageType.ERROR);
        }

    }

    public File getInputFile() {
        return inputFile;
    }

    public File getInputFileWithKey() {
        return inputFileWithKey;
    }

    public String getOutputDirectory() {
        return outputDirectory;
    }

    public void setSelectedEncryptionAlgorithm(EncryptionAlgorithm selectedEncryptionAlgorithm) {
        this.selectedEncryptionAlgorithm = selectedEncryptionAlgorithm;
    }

    public Message getMessage() {
        return message;
    }
}
