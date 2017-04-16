package com.luckyhua.demo.utils;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.*;

/**
 * 加密工具类
 *
 * @author    qinxiaoqing
 * @version   1.0
 * date      2016/04/08
 * @since     1.0
 */
public class EncryptUtils {

    public static KeyPair createPairKey(String algorithm) throws Exception {
        // 根据特定的算法algorithm获取一个密钥对生成器
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);

        // 加密随机数生成器 (RNG),(可以不写)
        SecureRandom secureRandom = new SecureRandom();

        // 重新设置此随机对象的种子
        String key = "pachira_psae_qinxiaoqing";
        secureRandom.setSeed(key.getBytes());

        // 使用给定的随机源（和默认的参数集合）初始化确定密钥大小的密钥对生成器
        keyPairGenerator.initialize(1024, secureRandom);

        // 生成一个密钥对象,返回
        return keyPairGenerator.generateKeyPair();
    }

    public static byte[] base64Decode(String base64Code) {
        return Base64.decode(base64Code);
    }

    /**
     * base 64 encode
     * @param bytes 待编码的byte[]
     * @return 编码后的base 64 code
     */
    public static String base64Encode(byte[] bytes){
        return Base64.encode(bytes);
    }

    /**
     * * 加密 *
     * 加密的密钥 *
     * @param data
     * 待加密的明文数据 *
     * @return 加密后的数据 *
     * @throws Exception
     */
    public static byte[] encrypt(PublicKey pk, byte[] data) throws Exception {
        try {
            Cipher cipher = Cipher.getInstance("RSA/NONE/NoPadding",
                    new BouncyCastleProvider());
            cipher.init(Cipher.ENCRYPT_MODE, pk);
            // 获得加密块大小，如：加密前数据为128个byte，而key_size=1024
            int blockSize = cipher.getBlockSize();
            // 加密块大小为127
            // byte,加密后为128个byte;因此共有2个加密块，第一个127
            // byte第二个为1个byte
            int outputSize = cipher.getOutputSize(data.length);// 获得加密块加密后块大小
            int leavedSize = data.length % blockSize;
            int blocksSize = leavedSize != 0 ? data.length / blockSize+ 1 : data.length / blockSize;
            byte[] raw = new byte[outputSize * blocksSize];
            int i = 0;
            while (data.length - i * blockSize > 0) {
                if (data.length - i * blockSize > blockSize) {
                    cipher.doFinal(data, i * blockSize, blockSize, raw, i * outputSize);
                } else {
                    cipher.doFinal(data, i * blockSize, data.length - i * blockSize, raw, i * outputSize);
                }
                i++;
            }
            return raw;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * * 解密 *
     *
     * 解密的密钥 *
     * @param raw
     * 已经加密的数据 *
     * @return 解密后的明文 *
     * @throws Exception
     */
    @SuppressWarnings("static-access")
    public static byte[] decrypt(PrivateKey pk, byte[] raw) {
        try {
            Cipher cipher = Cipher.getInstance("RSA/NONE/NoPadding",
                    new BouncyCastleProvider());
            cipher.init(cipher.DECRYPT_MODE, pk);
            int blockSize = cipher.getBlockSize();
            ByteArrayOutputStream bout = new ByteArrayOutputStream(64);
            int j = 0;
            while (raw.length - j * blockSize > 0) {
                bout.write(cipher.doFinal(raw, j * blockSize, blockSize));
                j++;
            }
            return bout.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}