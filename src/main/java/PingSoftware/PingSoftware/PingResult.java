package PingSoftware.PingSoftware;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author pc
 */
public class PingResult {
    private int responseCode;
    private boolean isOK;
    private long pingTime;
    private String message;

    public void setPingTime(long pingTime) {
        this.pingTime = pingTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getPingTime() {
        return pingTime;
    }



    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public boolean isIsOK() {
        return isOK;
    }

    public void setIsOK(boolean isOK) {
        this.isOK = isOK;
    }
    
    
}
