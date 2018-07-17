import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.*;

public class EncryptDecrypt {

    static SecretKey getKey(){
        SecretKey key=null;
        try {

            KeyStore ks = KeyStore.getInstance("JCEKS");
            char[] password = "211221".toCharArray();
            InputStream readStream = new FileInputStream("keystore.ks");
            ks.load(readStream, password);
            key = (SecretKey)ks.getKey("deskey1", password);
            readStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return key;
    }



    public static void main(String[] a){

        SecretKey key=getKey();

        String message="";
try {
    FileReader fileReader = new FileReader("hello.txt");
    BufferedReader bufferedReader = new BufferedReader(fileReader);
    message = bufferedReader.readLine();
    bufferedReader.close();
} catch (FileNotFoundException e) {
    e.printStackTrace();
} catch (IOException e) {
    e.printStackTrace();
}

        System.out.println("Message :" + message);

        byte[] encryptedMessage = getEncrypt(message.getBytes(),key);

        StringBuffer encrypt = new StringBuffer();
        for (byte bytes : encryptedMessage) {
            encrypt.append(String.format("%02x", bytes & 0xff));
        }
        System.out.println("Encrypted Message :" + encrypt.toString());

        byte[] decryptedMessage = getDecrypt(encryptedMessage , key);

        System.out.println("Decrypted Message New :" + new String(decryptedMessage));

    }


    static byte[] getEncrypt(byte[] message,SecretKey key){

        try {
            Cipher eCipher = Cipher.getInstance("DES");
            eCipher.init(Cipher.ENCRYPT_MODE, key);

            return eCipher.doFinal(message);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    static byte[] getDecrypt(byte[] encryptedMessage,SecretKey key){
        try {
            Cipher dCipher = Cipher.getInstance("DES");
            dCipher.init(Cipher.DECRYPT_MODE, key);

            return dCipher.doFinal(encryptedMessage);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
