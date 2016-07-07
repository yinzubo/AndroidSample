package com.bitech.androidsample.http.manager;

import java.io.InputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

/**
 * <p></p>
 * Created on 2016/7/7 14:04.
 *
 * @author Lucy
 */
public class SSlSocketFactory {

    /**
     * 返回SSLContent
     * */
    public static SSLContext getSocketFactory(InputStream... certificates){

        try {
            CertificateFactory certificateFactory=CertificateFactory.getInstance("X.509");
            KeyStore keyStore=KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null);
            int index=0;
            for(InputStream certificate:certificates){
                String certificateAlias=Integer.toString(index++);
                keyStore.setCertificateEntry(certificateAlias,certificateFactory.generateCertificate(certificate));

                try {
                    if(certificate!=null){
                        certificate.close();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            //SSL TSL协议
            SSLContext sslContext=SSLContext.getInstance("TLS");

            TrustManagerFactory trustManagerFactory=TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            sslContext.init(null,trustManagerFactory.getTrustManagers(),new SecureRandom());
            return sslContext;

            /**
             *双向正式认证，即在客户端设置key,服务端设置cert,进行认证
             *keytool可以设置密钥，导出cert公钥
             * */
            KeyStore clientKeyStore= KeyStore.getInstance(KeyStore.getDefaultType());
            clientKeyStore.load(null,"passowrd".toCharArray());
            KeyManagerFactory keyManagerFactory=KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            keyManagerFactory.init(clientKeyStore,"password".toCharArray());
            sslContext.init(keyManagerFactory.getKeyManagers(),trustManagerFactory.getTrustManagers(),new SecureRandom());


        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
