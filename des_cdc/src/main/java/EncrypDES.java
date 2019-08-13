import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Base64;

public class EncrypDES {

    static AlgorithmParameterSpec iv = null;// �����㷨�Ĳ����ӿڣ�IvParameterSpec������һ��ʵ��
    private static SecretKey key = null;


    public EncrypDES(String desKey, String desIv) throws Exception {
        byte[] DESkey = desKey.getBytes();
        byte[] DESIV = desIv.getBytes();
        DESKeySpec keySpec = new DESKeySpec(DESkey);// ������Կ����
        iv = new IvParameterSpec(DESIV);// ��������
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");// �����Կ����
        key = keyFactory.generateSecret(keySpec);// �õ���Կ����
    }

    /**
     * ����
     * @param data �����ܵ�����
     * @return ���ܺ������
     * @throws Exception
     */
    public String encode(String data) throws Exception {
        Cipher enCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");// �õ����ܶ���Cipher
        enCipher.init(Cipher.ENCRYPT_MODE, key, iv);// ���ù���ģʽΪ����ģʽ��������Կ������
        byte[] pasByte = enCipher.doFinal(data.getBytes("utf-8"));
        //BASE64Encoder base64Encoder = new BASE64Encoder();
        return Base64.getEncoder().encodeToString(pasByte);
        //return base64Encoder.encode(pasByte);
    }

    /**
     * ����
     * @param data  ����ǰ������
     * @return ���ܺ������
     * @throws Exception
     */
    public String decode(String data) throws Exception {
        Cipher deCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        deCipher.init(Cipher.DECRYPT_MODE, key, iv);
        //BASE64Decoder base64Decoder = new BASE64Decoder();
        //byte[] pasByte = deCipher.doFinal(base64Decoder.decodeBuffer(data));
        byte[] pasByte = deCipher.doFinal(Base64.getDecoder().decode(data));
        return new String(pasByte, "UTF-8");
    }

    public static void main(String[] args) throws Exception {
        EncrypDES tools = new EncrypDES("V81963DP", "V81963DP");
        String data = "startimes";
        System.out.println("����:" + tools.encode(data));

        String data1 = tools.encode(data);

        System.out.println("����:" + tools.decode(data1));
    }

//    /**
//     * @param key  ��Կ
//     * @param data ����
//     * @return Base64���������
//     * @throws Exception
//     * @Description ECB���ܣ���ҪIV
//     */
//    public static byte[] des3EncodeECB(byte[] key, byte[] data) throws Exception {
//        Key deskey = null;
//        DESedeKeySpec spec = new DESedeKeySpec(key);
//        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
//        deskey = keyfactory.generateSecret(spec);
//        Cipher cipher = Cipher.getInstance("desede" + "/ECB/PKCS5Padding");
//        cipher.init(Cipher.ENCRYPT_MODE, deskey);
//        byte[] bOut = cipher.doFinal(data);
//        return bOut;
//    }
//
//    /**
//     * @param key  ��Կ
//     * @param data Base64���������
//     * @return ����
//     * @throws Exception
//     * @Description ECB���ܣ���ҪIV
//     */
//    public static byte[] ees3DecodeECB(byte[] key, byte[] data) throws Exception {
//        Key deskey = null;
//        DESedeKeySpec spec = new DESedeKeySpec(key);
//        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
//        deskey = keyfactory.generateSecret(spec);
//        Cipher cipher = Cipher.getInstance("desede" + "/ECB/PKCS5Padding");
//        cipher.init(Cipher.DECRYPT_MODE, deskey);
//        byte[] bOut = cipher.doFinal(data);
//        return bOut;
//    }
//
//    /**
//     * @param key   ��Կ
//     * @param data  ����
//     * @return Base64���������
//     * @throws Exception
//     * @Description CBC����
//     */
//    public static byte[] des3EncodeCBC(byte[] key, byte[] data) throws Exception {
//        Key deskey = null;
//        DESedeKeySpec spec = new DESedeKeySpec(key);
//        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
//        deskey = keyfactory.generateSecret(spec);
//        Cipher cipher = Cipher.getInstance("desede" + "/CBC/PKCS5Padding");
//        cipher.init(Cipher.ENCRYPT_MODE, deskey);
//        byte[] bOut = cipher.doFinal(data);
//        return bOut;
//    }
//
//    /**
//     * @param key   ��Կ
//     * @param data  Base64���������
//     * @return ����
//     * @throws Exception
//     * @Description CBC����
//     */
//    public static byte[] des3DecodeCBC(byte[] key, byte[] data) throws Exception {
//        Key deskey = null;
//        DESedeKeySpec spec = new DESedeKeySpec(key);
//        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
//        deskey = keyfactory.generateSecret(spec);
//        Cipher cipher = Cipher.getInstance("desede" + "/CBC/PKCS5Padding");
//        cipher.init(Cipher.DECRYPT_MODE, deskey);
//        byte[] bOut = cipher.doFinal(data);
//        return bOut;
//    }

//    /**
//     * @param args
//     * @throws Exception
//     * @Description ���Է���
//     */
//    public static void main(String[] args) throws Exception {
//        byte[] key = new BASE64Decoder().decodeBuffer("YWJjZGVmZ2hpamtsbW5vcHFyc3R1dnd4");
//        byte[] data = "startimes".getBytes("UTF-8");
//
//        System.out.println("CBC���ܽ���");
//        byte[] str5 = des3EncodeCBC(key, data);
//        byte[] str6 = des3DecodeCBC(key, str5);
//        System.out.println(byteToHex(str5));
//        System.out.println(new String(str6, "UTF-8"));
//
//    }

//    //KeyGenerator �ṩ�Գ���Կ�������Ĺ��ܣ�֧�ָ����㷨
//    private KeyGenerator keygen;
//    //SecretKey ���𱣴�Գ���Կ
//    private SecretKey deskey;
//    //Cipher������ɼ��ܻ���ܹ���
//    private Cipher c;
//    //���ֽ����鸺�𱣴���ܵĽ��
//    private byte[] cipherByte;
//
//    public EncrypDES() throws NoSuchAlgorithmException, NoSuchPaddingException{
//        Security.addProvider(new com.sun.crypto.provider.SunJCE());
//        //ʵ����֧��DES�㷨����Կ������(�㷨���������谴�涨�������׳��쳣)
//        keygen = KeyGenerator.getInstance("DES");
//        //������Կ
//        deskey = keygen.generateKey();
//        //����Cipher����,ָ����֧�ֵ�DES�㷨
//        c = Cipher.getInstance("DES");
//    }
//
//    /**
//     * ���ַ�������
//     *
//     * @param str
//     * @return
//     * @throws InvalidKeyException
//     * @throws IllegalBlockSizeException
//     * @throws BadPaddingException
//     */
//    public byte[] Encrytor(String str) throws InvalidKeyException,
//            IllegalBlockSizeException, BadPaddingException {
//        // ������Կ����Cipher������г�ʼ����ENCRYPT_MODE��ʾ����ģʽ
//        c.init(Cipher.ENCRYPT_MODE, deskey);
//        byte[] src = str.getBytes();
//        // ���ܣ���������cipherByte
//        cipherByte = c.doFinal(src);
//        return cipherByte;
//    }
//
//    /**
//     * ���ַ�������
//     *
//     * @param buff
//     * @return
//     * @throws InvalidKeyException
//     * @throws IllegalBlockSizeException
//     * @throws BadPaddingException
//     */
//    public byte[] Decryptor(byte[] buff) throws InvalidKeyException,
//            IllegalBlockSizeException, BadPaddingException {
//        // ������Կ����Cipher������г�ʼ����DECRYPT_MODE��ʾ����ģʽ
//        c.init(Cipher.DECRYPT_MODE, deskey);
//        cipherByte = c.doFinal(buff);
//        return cipherByte;
//    }
//
//    /**
//     * @param args
//     * @throws NoSuchPaddingException
//     * @throws NoSuchAlgorithmException
//     * @throws BadPaddingException
//     * @throws IllegalBlockSizeException
//     * @throws InvalidKeyException
//     */
//    public static void main(String[] args) throws Exception {
//        EncrypDES de1 = new EncrypDES();
//        String msg ="startimes";
//        byte[] encontent = de1.Encrytor(msg);
//        byte[] decontent = de1.Decryptor(encontent);
//        System.out.println("������:" + msg);
//        System.out.println("���ܺ�:" + byteToHex(encontent));
//        System.out.println("���ܺ�:" + new String(decontent));
//    }

//    /**
//     * @param key   ��Կ
//     * @param data  ����
//     * @return Base64���������
//     * @throws Exception
//     * @Description CBC����
//     */
//    public static byte[] desEncodeCBC(byte[] key, byte[] data) throws Exception {
//        Key deskey = null;
//        DESedeKeySpec spec = new DESedeKeySpec(key);
//        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
//        deskey = keyfactory.generateSecret(spec);
//        Cipher cipher = Cipher.getInstance("desede" + "/CBC/PKCS5Padding");
//        cipher.init(Cipher.ENCRYPT_MODE, deskey);
//        byte[] bOut = cipher.doFinal(data);
//        return bOut;
//    }
//
//    /**
//     * @param key   ��Կ
//     * @param keyiv IV
//     * @param data  Base64���������
//     * @return ����
//     * @throws Exception
//     * @Description CBC����
//     */
//    public static byte[] desDecodeCBC(byte[] key, byte[] keyiv, byte[] data) throws Exception {
//        Key deskey = null;
//        DESedeKeySpec spec = new DESedeKeySpec(key);
//        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
//        deskey = keyfactory.generateSecret(spec);
//        Cipher cipher = Cipher.getInstance("desede" + "/CBC/PKCS5Padding");
//        IvParameterSpec ips = new IvParameterSpec(keyiv);
//        cipher.init(Cipher.DECRYPT_MODE, deskey, ips);
//        byte[] bOut = cipher.doFinal(data);
//        return bOut;
//    }
//
//    /**
//     * @param args
//     * @throws Exception
//     * @Description ���Է���
//     */
//    public static void main(String[] args) throws Exception {
//        byte[] key = new byte[24];
//        byte[] key2 = "V81963DP".getBytes();
//        for(int i = 0; i< 8; i++){
//            key[i] = key2[i];
//        }
//        byte[] keyiv = "V81963DP".getBytes();
//        byte[] data = "startimes".getBytes("UTF-8");
//
//        System.out.println("CBC���ܽ���");
//        byte[] str5 = desEncodeCBC(key, data);
//        byte[] str6 = desDecodeCBC(key, keyiv, str5);
//        System.out.println(byteToHex(str5));
//
//    }

    /**
     * byte����תhex
     * @param bytes
     * @return
     */
    public static String byteToHex(byte[] bytes){
        String strHex = "";
        StringBuilder sb = new StringBuilder("");
        for (int n = 0; n < bytes.length; n++) {
            strHex = Integer.toHexString(bytes[n] & 0xFF);
            sb.append((strHex.length() == 1) ? "0" + strHex : strHex); // ÿ���ֽ��������ַ���ʾ��λ����������λ��0
        }
        return sb.toString().trim();
    }
}
