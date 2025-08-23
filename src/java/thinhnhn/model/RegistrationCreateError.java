/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thinhnhn.model;

import java.io.Serializable;

/**
 *
 * @author FGApollo
 */
public class RegistrationCreateError implements Serializable{
    private String usernameLengthErr;
    private String passwordLengthErr;
    private String fullNameLengErr;
    private String conformNotMatch;
    private String usernameIsExisted;

    public RegistrationCreateError() {
    }

    public String getUsernameLengthErr() {
        return usernameLengthErr;
    }

    public void setUsernameLengthErr(String usernameLengthErr) {
        this.usernameLengthErr = usernameLengthErr;
    }

    public String getPasswordLengthErr() {
        return passwordLengthErr;
    }

    public void setPasswordLengthErr(String passwordLengthErr) {
        this.passwordLengthErr = passwordLengthErr;
    }

    public String getFullNameLengErr() {
        return fullNameLengErr;
    }

    public void setFullNameLengErr(String fullNameLengErr) {
        this.fullNameLengErr = fullNameLengErr;
    }

    public String getConformNotMatch() {
        return conformNotMatch;
    }

    public void setConformNotMatch(String conformNotMatch) {
        this.conformNotMatch = conformNotMatch;
    }

    public String getUsernameIsExisted() {
        return usernameIsExisted;
    }

    public void setUsernameIsExisted(String usernameIsExisted) {
        this.usernameIsExisted = usernameIsExisted;
    }
    
    
    
}
