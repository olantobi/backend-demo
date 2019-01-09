package com.africaprudential.backenddemo.encoder;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class CustomPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence charSequence) {
        byte[] encodedPassword = new byte[0];
        try {
            byte[] salt = Password.generateSalt();
            encodedPassword = Password.getEncryptedPassword(charSequence.toString(), salt);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return new String(encodedPassword);
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        boolean matched = false;
        try {
            byte[] salt = Password.generateSalt();
            matched = Password.authenticate(charSequence.toString(), s.getBytes(), salt);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return matched;
    }

}
