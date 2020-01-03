import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.security.Provider;
import java.security.SecureRandom;

public class Main {

    public static void main(String[] args) {

        System.out.println("Hello World!");
        String toDec = "halloWelt!";
        String finalKey = "Y29tLmV0ZWtjaXR5LnZlc3luYw==";
        //byte[] toDecByte = ;

        byte[] hallo = decrypt(toByte(finalKey),toByte(toDec));
        System.out.println(hallo);
    }
    private static byte[] decrypt(byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(paramArrayOfbyte1, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(2, secretKeySpec);
        return cipher.doFinal(paramArrayOfbyte2);
    }

    private static byte[] encrypt(byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(paramArrayOfbyte1, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(1, secretKeySpec);
        return cipher.doFinal(paramArrayOfbyte2);
    }
    private static String fromHex(String paramString) { return new String(toByte(paramString)); }

    private static byte[] getRawKey(byte[] paramArrayOfbyte) throws Exception {
        SecureRandom secureRandom;
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        if (true) {
            secureRandom = SecureRandom.getInstance("SHA1PRNG", (Provider)new CryptoProvider());
        } else if (false) {
            secureRandom = SecureRandom.getInstance("SHA1PRNG", "Crypto");
        } else {
            secureRandom = SecureRandom.getInstance("SHA1PRNG");
        }
        secureRandom.setSeed(paramArrayOfbyte);
        keyGenerator.init(128, secureRandom);
        return keyGenerator.generateKey().getEncoded();
    }

    private static byte[] toByte(String paramString) {
        int j = paramString.length() / 2;
        byte[] arrayOfByte = new byte[j];
        for (int i = 0; i < j; i++) {
            int k = i * 2;
            arrayOfByte[i] = Integer.valueOf(paramString.substring(k, k + 2), 16).byteValue();
        }
        return arrayOfByte;
    }

    private static String toHex(String paramString) { return toHex(paramString.getBytes()); }

    private static String toHex(byte[] paramArrayOfbyte) {
        if (paramArrayOfbyte == null)
            return "";
        StringBuffer stringBuffer = new StringBuffer(paramArrayOfbyte.length * 2);
        int j = paramArrayOfbyte.length;
        for (int i = 0; i < j; i++)
            //appendHex(stringBuffer, paramArrayOfbyte[i]);
        return stringBuffer.toString();
        return "a";
    }

}
