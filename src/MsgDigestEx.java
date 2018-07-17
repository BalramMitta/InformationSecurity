import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MsgDigestEx {

    public static void main(String[] args)
    {
        String data;
        MessageDigest messageDigest;
        try {

            FileReader fileReader = new FileReader("hello.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            data = bufferedReader.readLine();
            bufferedReader.close();

            messageDigest = MessageDigest.getInstance("SHA1");
            messageDigest.update(data.getBytes());

            byte[] messageDigestbyte = messageDigest.digest();
            StringBuffer stringBuffer = new StringBuffer();
            for (byte bytes : messageDigestbyte) {
                stringBuffer.append(String.format("%02x", bytes & 0xff));
            }

            System.out.println("data:" + data);
            System.out.println("MessageDigestSHA1(hex):" + stringBuffer.toString());
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

    }

}