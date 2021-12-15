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
   private int icmp;
   private double pingTime;
   private String ICMPEchoPacket;
   private String address;
   private boolean ping;
   
   public PingResult()
   {
	   icmp=-1;
	   pingTime=-1;
	   ICMPEchoPacket="Not Ping";
	   address="0.0.0.0";
	   ping=false;
   }
   
public boolean isPing() {
	return ping;
}

public void setPing(boolean ping) {
	this.ping = ping;
}

public String getAddress() {
	return address;
}

public void setAddress(String address) {
	this.address = address;
}

public String getICMPEchoPacket() {
	return ICMPEchoPacket;
}

public void setICMPEchoPacket(String iCMPEchoPacket) {
	ICMPEchoPacket = iCMPEchoPacket;
}

public int getIcmp() {
	return icmp;
}
public void setIcmp(int icmp) {
	this.icmp = icmp;
}
public double getPingTime() {
	return pingTime;
}
public void setPingTime(double pingTime) {
	this.pingTime = pingTime;
}
   
   
   
    
    
}
