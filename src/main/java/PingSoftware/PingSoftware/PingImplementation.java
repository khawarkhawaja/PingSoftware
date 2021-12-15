package PingSoftware.PingSoftware;

import java.io.IOException;
import java.net.DatagramPacket;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;

import org.opennms.core.utils.InetAddressUtils;
import org.opennms.netmgt.icmp.jni.JniIcmpMessenger;
import org.opennms.netmgt.icmp.jni.JniPingResponse;
import org.opennms.protocols.icmp.ICMPEchoPacket;
import org.opennms.protocols.icmp.IcmpSocket;

/**
 *
 * @author pc
 */
public class PingImplementation implements PingService  {
	   IcmpSocket m_socket = null;
public PingImplementation()
{


       try {
         m_socket = new IcmpSocket();

       } catch (UnsatisfiedLinkError e) {
         System.err.println("UnsatisfiedLinkError while creating an "
                            + "IcmpSocket.  Most likely failed to load "
                            + "libjicmp.so.  Try setting the property "
                            + "'opennms.library.jicmp' to point at the "
                            + "full path name of the libjicmp.so shared "
                            + "library "
                            + "(e.g. 'java -Dopennms.library.jicmp=/some/path/libjicmp.so ...')");
         e.printStackTrace();
         System.exit(1);
       } catch (NoClassDefFoundError e) {
         System.err.println("NoClassDefFoundError while creating an "
                            + "IcmpSocket.  Most likely failed to load "
                            + "libjicmp.so.");
         e.printStackTrace();
         System.exit(1);
       } catch (IOException e) {
         System.err.println("IOException while creating an "
                            + "IcmpSocket.");
         e.printStackTrace();
         System.exit(1);
       }
}
    
    public static class Stuff implements Runnable {
        private IcmpSocket m_socket;
    private short m_icmpId;
    PingResult result;
    
        public Stuff(IcmpSocket socket, short icmpId,  PingResult res) {
            m_socket = socket;
            m_icmpId = icmpId;
            result=res;
            
            
            
        }
    
        public void run() {
            try {
                while (true) {
                	
                    DatagramPacket pkt = m_socket.receive();
                    JniPingResponse reply;
                    try {
                        reply = JniIcmpMessenger.createPingResponse(pkt);
                    } catch (Throwable t) {
                        // do nothing but skip this packet
                        continue;
                    }
            
                    if (reply.isEchoReply()) {
                    	
                        double rtt = reply.elapsedTime(TimeUnit.MILLISECONDS);
                        int rr= reply.getIdentifier();
                     
                        if(rr==3) {
                        	result.setIcmp(reply.getIdentifier());
                        	result.setPingTime(rtt);
                        	result.setICMPEchoPacket(ICMPEchoPacket.getNetworkSize()+"");
                        	result.setAddress(InetAddressUtils.str(pkt.getAddress())+"");
                        	result.setPing(true);
                   
                        break;
                        }
                    }else
                    {
                    	System.out.println("Not Ping");
                    }
                }
            } catch (final Throwable t) {
             //   LogUtils.errorf(this, t, "An exception occured processing the datagram, thread exiting.");
                System.exit(1);
            }
        }
    }
    
    
    
    
    
    @Override
    public PingResult ping(String ipOrHostname) {
        PingResult pingResult = new PingResult();
     
        
     

        java.net.InetAddress addr = null;
        try {
        addr = InetAddress.getByName(ipOrHostname);
        } catch (java.net.UnknownHostException e) {
          System.err.println("UnknownHostException when looking up "
                             + ipOrHostname + ".");
          e.printStackTrace();
          System.exit(1);
        }

      //  System.out.println("PING " + ipOrHostname + " (" + InetAddressUtils.str(addr) + "): 56 data bytes");

        short m_icmpId = 3;

        PingImplementation.Stuff s = new PingImplementation.Stuff(m_socket, m_icmpId,pingResult);
        Thread t = new Thread(s, PingImplementation.class.getSimpleName());
        t.start();

        for (long m_fiberId = 0; m_fiberId<1; m_fiberId++) {
          // build a packet
          ICMPEchoPacket pingPkt = new ICMPEchoPacket(0);
          pingPkt.setIdentity(m_icmpId);
          pingPkt.computeChecksum();

          // convert it to a datagram to be sent
          byte[] buf = pingPkt.toBytes();
          DatagramPacket sendPkt =
              new DatagramPacket(buf, buf.length, addr, 0);
          buf = null;
          pingPkt = null;

          try {
          	
              m_socket.send(sendPkt);

          } catch (IOException e) {
              System.err.println("IOException received when sending packet.");
              e.printStackTrace();
              System.exit(1);
          }
          try {
              Thread.sleep(4000);
          } catch (InterruptedException e) {
              // do nothing
          }
        }
        
        
        

        return pingResult;
    }

    @Override
    public List ping(List ipOrHostnameList) {

        List returnResult = new ArrayList<PingResult>();

      
        for(int ii=0;ii<ipOrHostnameList.size();ii++)
        {
        	String ipOrHostname=(String) ipOrHostnameList.get(ii);
        	PingResult pingResult = new PingResult();
            
            
            

            java.net.InetAddress addr = null;
            try {
            addr = InetAddress.getByName(ipOrHostname);
            } catch (java.net.UnknownHostException e) {
              System.err.println("UnknownHostException when looking up "
                                 + ipOrHostname + ".");
              e.printStackTrace();
              System.exit(1);
            }

          //  System.out.println("PING " + ipOrHostname + " (" + InetAddressUtils.str(addr) + "): 56 data bytes");

            short m_icmpId = 3;

            PingImplementation.Stuff s = new PingImplementation.Stuff(m_socket, m_icmpId,pingResult);
            Thread t = new Thread(s, PingImplementation.class.getSimpleName());
            t.start();

            for (long m_fiberId = 0; m_fiberId<1; m_fiberId++) {
              // build a packet
              ICMPEchoPacket pingPkt = new ICMPEchoPacket(0);
              pingPkt.setIdentity(m_icmpId);
              pingPkt.computeChecksum();

              // convert it to a datagram to be sent
              byte[] buf = pingPkt.toBytes();
              DatagramPacket sendPkt =
                  new DatagramPacket(buf, buf.length, addr, 0);
              buf = null;
              pingPkt = null;

              try {
              	
                  m_socket.send(sendPkt);

              } catch (IOException e) {
                  System.err.println("IOException received when sending packet.");
                  e.printStackTrace();
                  System.exit(1);
              }
              try {
                  Thread.sleep(4000);
                  returnResult.add(pingResult);
              } catch (InterruptedException e) {
                  // do nothing
              }
            }
            
        }
        
        

        return returnResult;
    }


}
