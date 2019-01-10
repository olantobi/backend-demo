package com.africaprudential.backenddemo.encoder;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class CustomPasswordEncoder implements PasswordEncoder {

    private static byte[] defaultSalt = null;

    {
        try {
            defaultSalt = Password.generateSalt();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String encode(CharSequence charSequence) {
        byte[] encodedPassword = new byte[0];
        try {
            encodedPassword = Password.getEncryptedPassword(charSequence.toString(), defaultSalt);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        String encoded = Hex.encodeHexString(encodedPassword);
        return encoded;
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        boolean matched = false;

        try {
            byte[] passwordBytes = Hex.decodeHex(s);

            matched = Password.authenticate(charSequence.toString(), passwordBytes, defaultSalt);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (DecoderException e) {
            e.printStackTrace();
        }
        return matched;
    }

}
