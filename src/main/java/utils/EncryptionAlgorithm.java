package utils;

public enum EncryptionAlgorithm {
    AES_128 (EncryptionAlgorithmName.AES, 128),
    AES_192 (EncryptionAlgorithmName.AES, 192),
    AES_256 (EncryptionAlgorithmName.AES, 256),
    RSA_1024 (EncryptionAlgorithmName.RSA, 1024),
    RSA_2048 (EncryptionAlgorithmName.RSA, 2048);

    public final EncryptionAlgorithmName name;
    public final int keyLength;

    EncryptionAlgorithm(EncryptionAlgorithmName name, int keyLength) {
        this.name = name;
        this.keyLength = keyLength;
    }
}
