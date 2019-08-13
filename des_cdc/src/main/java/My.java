import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.AlgorithmConstraints;
import java.security.Security;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Arrays;

public class My {

    public static void main(String[] args) throws Exception {

        String keyStr = "V81963DP";
        String aimData = "startimes";
        byte[] key = new byte[8];
        key[0] = int2Byte(70);
        key[1] = int2Byte(174);
        key[2] = int2Byte(182);
        key[3] = int2Byte(2);
        key[4] = int2Byte(42);
        key[5] = int2Byte(217);
        key[6] = int2Byte(49);
        key[7] = int2Byte(19);
        System.out.println(byteToHex(key));
        DESKeySpec desKeySpec = new DESKeySpec(key);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = factory.generateSecret(desKeySpec);
        Cipher enCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        enCipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] result = enCipher.doFinal(aimData.getBytes("utf-8"));
        System.out.println(byteToHex(result));
    }

    public static byte int2Byte(int i){
        byte r = (byte)i;
        return r;
    }

    /**
     * byte数组转hex
     * @param bytes
     * @return
     */
    public static String byteToHex(byte[] bytes){
        String strHex = "";
        StringBuilder sb = new StringBuilder("");
        for (int n = 0; n < bytes.length; n++) {
            strHex = Integer.toHexString(bytes[n] & 0xFF);
            sb.append((strHex.length() == 1) ? "0" + strHex : strHex); // 每个字节由两个字符表示，位数不够，高位补0
        }
        return sb.toString().trim();
    }
}
