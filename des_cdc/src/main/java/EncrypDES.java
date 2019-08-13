import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Base64;

public class EncrypDES {

    static AlgorithmParameterSpec iv = null;// 加密算法的参数接口，IvParameterSpec是它的一个实现
    private static SecretKey key = null;


    public EncrypDES(String desKey, String desIv) throws Exception {
        byte[] DESkey = desKey.getBytes();
        byte[] DESIV = desIv.getBytes();
        DESKeySpec keySpec = new DESKeySpec(DESkey);// 设置密钥参数
        iv = new IvParameterSpec(DESIV);// 设置向量
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");// 获得密钥工厂
        key = keyFactory.generateSecret(keySpec);// 得到密钥对象
    }

    /**
     * 加密
     * @param data 待加密的数据
     * @return 加密后的数据
     * @throws Exception
     */
    public String encode(String data) throws Exception {
        Cipher enCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");// 得到加密对象Cipher
        enCipher.init(Cipher.ENCRYPT_MODE, key, iv);// 设置工作模式为加密模式，给出密钥和向量
        byte[] pasByte = enCipher.doFinal(data.getBytes("utf-8"));
        //BASE64Encoder base64Encoder = new BASE64Encoder();
        return Base64.getEncoder().encodeToString(pasByte);
        //return base64Encoder.encode(pasByte);
    }

    /**
     * 解密
     * @param data  解密前的数据
     * @return 解密后的数据
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
        System.out.println("加密:" + tools.encode(data));

        String data1 = tools.encode(data);

        System.out.println("解密:" + tools.decode(data1));
    }

//    /**
//     * @param key  密钥
//     * @param data 明文
//     * @return Base64编码的密文
//     * @throws Exception
//     * @Description ECB加密，不要IV
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
//     * @param key  密钥
//     * @param data Base64编码的密文
//     * @return 明文
//     * @throws Exception
//     * @Description ECB解密，不要IV
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
//     * @param key   密钥
//     * @param data  明文
//     * @return Base64编码的密文
//     * @throws Exception
//     * @Description CBC加密
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
//     * @param key   密钥
//     * @param data  Base64编码的密文
//     * @return 明文
//     * @throws Exception
//     * @Description CBC解密
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
//     * @Description 调试方法
//     */
//    public static void main(String[] args) throws Exception {
//        byte[] key = new BASE64Decoder().decodeBuffer("YWJjZGVmZ2hpamtsbW5vcHFyc3R1dnd4");
//        byte[] data = "startimes".getBytes("UTF-8");
//
//        System.out.println("CBC加密解密");
//        byte[] str5 = des3EncodeCBC(key, data);
//        byte[] str6 = des3DecodeCBC(key, str5);
//        System.out.println(byteToHex(str5));
//        System.out.println(new String(str6, "UTF-8"));
//
//    }

//    //KeyGenerator 提供对称密钥生成器的功能，支持各种算法
//    private KeyGenerator keygen;
//    //SecretKey 负责保存对称密钥
//    private SecretKey deskey;
//    //Cipher负责完成加密或解密工作
//    private Cipher c;
//    //该字节数组负责保存加密的结果
//    private byte[] cipherByte;
//
//    public EncrypDES() throws NoSuchAlgorithmException, NoSuchPaddingException{
//        Security.addProvider(new com.sun.crypto.provider.SunJCE());
//        //实例化支持DES算法的密钥生成器(算法名称命名需按规定，否则抛出异常)
//        keygen = KeyGenerator.getInstance("DES");
//        //生成密钥
//        deskey = keygen.generateKey();
//        //生成Cipher对象,指定其支持的DES算法
//        c = Cipher.getInstance("DES");
//    }
//
//    /**
//     * 对字符串加密
//     *
//     * @param str
//     * @return
//     * @throws InvalidKeyException
//     * @throws IllegalBlockSizeException
//     * @throws BadPaddingException
//     */
//    public byte[] Encrytor(String str) throws InvalidKeyException,
//            IllegalBlockSizeException, BadPaddingException {
//        // 根据密钥，对Cipher对象进行初始化，ENCRYPT_MODE表示加密模式
//        c.init(Cipher.ENCRYPT_MODE, deskey);
//        byte[] src = str.getBytes();
//        // 加密，结果保存进cipherByte
//        cipherByte = c.doFinal(src);
//        return cipherByte;
//    }
//
//    /**
//     * 对字符串解密
//     *
//     * @param buff
//     * @return
//     * @throws InvalidKeyException
//     * @throws IllegalBlockSizeException
//     * @throws BadPaddingException
//     */
//    public byte[] Decryptor(byte[] buff) throws InvalidKeyException,
//            IllegalBlockSizeException, BadPaddingException {
//        // 根据密钥，对Cipher对象进行初始化，DECRYPT_MODE表示加密模式
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
//        System.out.println("明文是:" + msg);
//        System.out.println("加密后:" + byteToHex(encontent));
//        System.out.println("解密后:" + new String(decontent));
//    }

//    /**
//     * @param key   密钥
//     * @param data  明文
//     * @return Base64编码的密文
//     * @throws Exception
//     * @Description CBC加密
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
//     * @param key   密钥
//     * @param keyiv IV
//     * @param data  Base64编码的密文
//     * @return 明文
//     * @throws Exception
//     * @Description CBC解密
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
//     * @Description 调试方法
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
//        System.out.println("CBC加密解密");
//        byte[] str5 = desEncodeCBC(key, data);
//        byte[] str6 = desDecodeCBC(key, keyiv, str5);
//        System.out.println(byteToHex(str5));
//
//    }

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
