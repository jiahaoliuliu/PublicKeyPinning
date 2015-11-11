package com.jiahaoliuliu.publickeypinning;

import android.util.Base64;
import android.util.Log;

import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;

import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/**
 * Many thanks to Nikolay Elenkov for feedback.
 * Shamelessly based upon Moxie's example code (AOSP/Google did not offer code)
 * http://www.thoughtcrime.org/blog/authenticity-is-broken-in-ssl-but-your-app-ha/
 *
 * @see <a href="https://www.owasp.org/index.php/Certificate_and_Public_Key_Pinning">
 *     This article from OWASP for more information about the public pinning and the
 *     code itself</a>
 */
public final class PubKeyManager implements X509TrustManager {

    private static final String TAG = "PubKeyManager";
    private static final String AUTH_TYPE = "ECDHE_RSA";

    // DER encoded public key
    private static String PUB_KEY =
            "MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEAx0dhmq74bQd77tVeZsr0" +
            "zYrkbXYNSa5mLr1bprVoaHRt1xHRUzygrhi9llHswbCWF1Gl/Ys0Lyy1jwbfjrsk" +
            "sR8lBnqf27gtK7YCKLtbZ1TRVWI5bC4fIM3pJEwt4LfxJlC6ciynJDBvfcUiDZvG" +
            "Sx9te50IHpuvIrA8/OMOuzI3JNYSjUJfmeSoH9g4gRRPuAqUfyODtOwyJ2TW0DaU" +
            "pMnOBeHrzRp7oocdjvuSFKjBXM72+niTVPGb25bCFkQq+emg5/rV+e7Pihc3gasc" +
            "EJedFTRFLl9SiLFae6cv4npZWVcMjWniRdDOcyBLRqZ969E+MMZB6e3o9dt19dmS" +
            "xwtmsCqugyys3WDVddYfOq/9pQn7LgGY2hzIx6BxqRgW16OxYJhHSyKDcBZT8zdR" +
            "Alpt1YRa8syKAoRJNRNknbBMfXnbGHPpDouPdbjSskmx3/HplLHn8X0y61utbCgY" +
            "BQ4KDaLuTtto0mzPz/lQ+kczS2duTW3tmPaxt0MnML7poO2UB4QnRYJw/el1tl2D" +
            "o7Y5XHiN+Ars0+TE/p0sTMRJQIUCg5MXef+s+TqfuvIiPJ18SBjUTqpCMqrcSvfY" +
            "saWk+jLIFqDHlZIEp0VksRUGDBUiSmNiRWpWPzD2rmYXKhjLjo9oNh82jYGh3M9i" +
            "cLO5naewLanwc1n6tGUs9FMCAwEAAQ==";

    public void checkServerTrusted(X509Certificate[] chain, String authType)
            throws CertificateException {

        if (chain == null) {
            throw new IllegalArgumentException(
                    "checkServerTrusted: X509Certificate array is null");
        }

        if (!(chain.length > 0)) {
            throw new IllegalArgumentException(
                    "checkServerTrusted: X509Certificate is empty");
        }

        if (!(null != authType && authType.equalsIgnoreCase(AUTH_TYPE))) {
            throw new CertificateException(
                    "checkServerTrusted: AuthType is " + authType + "when we expect" +
                            AUTH_TYPE + ".");
        }

        // Perform customary SSL/TLS checks
        TrustManagerFactory tmf;
        try {
            tmf = TrustManagerFactory.getInstance("X509");
            tmf.init((KeyStore) null);

            for (TrustManager trustManager : tmf.getTrustManagers()) {
                ((X509TrustManager) trustManager).checkServerTrusted(
                        chain, authType);
            }

        } catch (Exception e) {
            throw new CertificateException(e);
        }

        RSAPublicKey pubkey = (RSAPublicKey) chain[0].getPublicKey();
        // Solution found in
        // http://stackoverflow.com/questions/28667575/pinning-public-key-in-my-app
        String base64Encoded = Base64.encodeToString(pubkey.getEncoded(), Base64.DEFAULT)
                .replace("\n", "");
        Log.v(TAG, "The public key encoded with base 64 is " + base64Encoded);

        // Pin it!
        final boolean expected = PUB_KEY.equalsIgnoreCase(base64Encoded);
        if (!expected) {
            throw new CertificateException(
                    "checkServerTrusted: Expected public key: " + PUB_KEY
                            + ", got public key:" + base64Encoded);
        }
    }

    public void checkClientTrusted(X509Certificate[] xcs, String string) {
        // throw new
        // UnsupportedOperationException("checkClientTrusted: Not supported yet.");
    }

    public X509Certificate[] getAcceptedIssuers() {
        // throw new
        // UnsupportedOperationException("getAcceptedIssuers: Not supported yet.");
        return null;
    }
}
