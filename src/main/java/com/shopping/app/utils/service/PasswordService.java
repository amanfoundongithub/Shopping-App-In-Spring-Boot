package com.shopping.app.utils.service;
import org.mindrot.jbcrypt.BCrypt;


public class PasswordService {

    public PasswordService() {

    }

    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

    public boolean checkPassword(String rawPassword, String hashedPassword) {
        return BCrypt.checkpw(rawPassword, hashedPassword);
    }
    
}
