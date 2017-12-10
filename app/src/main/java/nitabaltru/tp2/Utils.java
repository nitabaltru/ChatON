package nitabaltru.tp2;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * a class with utils to be used internally
 * Created by nitabaltru on 06/12/2017.
 */

class Utils {

    /**
     * url of gravatar
     */
    static final String GRAVATAR_PREFIX = "https://www.gravatar.com/avatar/";

    /**
     * allows to convert an string to MD5
     * @param s the string to be converted
     * @return string
     */
    static String md5(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = MessageDigest.getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                StringBuilder h = new StringBuilder(Integer.toHexString(0xFF & aMessageDigest));
                while (h.length() < 2)
                    h.insert(0, "0");
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }


}
