package bt.be.bddapplication.Controler;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by rome03 on 5/07/2016.
 */
public class Md5 {


    private String code;

    public Md5(String md5) throws NoSuchAlgorithmException {
        passe(md5);
    }

    public void passe(String pass) throws NoSuchAlgorithmException {
        byte[] passBytes = pass.getBytes();
        try {
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(passBytes);
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(passBytes);
            BigInteger number = new BigInteger(1, messageDigest);
            this.code = number.toString(16);
        } catch (NoSuchAlgorithmException e) {
            throw new Error("invalid JRE: have not 'MD5' impl.", e);
        }
    }

    public String getCode() {
        return code;
    }
}
