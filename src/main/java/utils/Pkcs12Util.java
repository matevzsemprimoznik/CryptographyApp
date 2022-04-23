package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import java.util.Objects;

import javax.security.auth.x500.X500Principal;

public class Pkcs12Util {
    private File certificate;
    private String password;
    private KeyStore keyStore;
    private PublicKey publicKey;
    private PrivateKey privateKey;

    public Pkcs12Util(File certificate, String password) throws IOException {
        this.certificate = certificate;
        this.password = password;
        init();
    }

    private void init() throws IOException {
        try {
            keyStore = KeyStore.getInstance("pkcs12");
            keyStore.load(new FileInputStream(certificate),
                    password.toCharArray());

            Enumeration<String> e = keyStore.aliases();
            while (e.hasMoreElements()) {
                String alias = e.nextElement();
                X509Certificate certificate = (X509Certificate) keyStore
                        .getCertificate(alias);
                publicKey = certificate.getPublicKey();
                privateKey = (PrivateKey) keyStore.getKey(alias, password.toCharArray());
            }
        } catch (CertificateException | KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException e) {
            e.printStackTrace();
        }
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }
}
