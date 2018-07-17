import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;

public class MsgAuthenticationCode {

    public static void main(String[] args) {

        try {

            //Key Generated using DES
            KeyGenerator keyGen = KeyGenerator.getInstance("DES");
            SecretKey key = keyGen.generateKey();

            //Mac Using HmacSHA1
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(key);


            FileReader fileReader = new FileReader("hello.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String message = bufferedReader.readLine();
            bufferedReader.close();

            // get the string as UTF-8 bytes
            byte[] b = message.getBytes("UTF-8");

            // create a digest from the byte array
            byte[] digest = mac.doFinal(b);

            StringBuffer stringBuffer = new StringBuffer();
            for (byte bytes : digest) {
                stringBuffer.append(String.format("%02x", bytes & 0xff));
            }

            System.out.println("data:" + message);
            System.out.println("MsgAuthenticationCode:" + stringBuffer.toString());

        }
        catch (NoSuchAlgorithmException e) {
            System.out.println("No Such Algorithm:" + e.getMessage());
            return;
        }
        catch (UnsupportedEncodingException e) {
            System.out.println("Unsupported Encoding:" + e.getMessage());
            return;
        }
        catch (InvalidKeyException e) {
            System.out.println("Invalid Key:" + e.getMessage());
            return;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
