import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

public class KeyGenerationDES {

    public static void main(String[] a){



        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("DES");
            SecretKey key = keyGen.generateKey();
            byte[] keyBytes = key.getEncoded();
            StringBuffer stringBuffer = new StringBuffer();
            for (byte bytes : keyBytes) {
                stringBuffer.append(String.format("%02x", bytes & 0xff));
            }


            System.out.println("DES Key in hexadecimal : "+stringBuffer.toString()+"\n"+key);


            KeyStore ks = KeyStore.getInstance("JCEKS");
            char[] password = "211221".toCharArray();
            ks.load(null, password);

            ks.setKeyEntry("deskey1", key, password, null);
            OutputStream writeStream = new FileOutputStream("keystore.ks");
            ks.store(writeStream, password);
            writeStream.close();


        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
