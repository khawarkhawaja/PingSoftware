package PingSoftware.PingSoftware;
import java.util.ArrayList;
import java.util.List;

import org.opennms.core.utils.InetAddressUtils;
import org.opennms.protocols.icmp.ICMPEchoPacket;
/**
 * Hello world!
 *
 */
public class App 
{
	
	static {
	    try {

	    	 String currentDir = System.getProperty("user.dir");
	    	System.load(currentDir+"//jicmp.dll");
	    } catch (UnsatisfiedLinkError e) {
	      System.err.println("Native code library failed to load.\n" + e);
	      System.exit(1);
	    }
	  }
    public static void main( String[] args )
    {
  	PingService p=new PingImplementation();
        String url="www.google.com";
        for(int i=0;i<4;i++)
        {
       PingResult result= p.ping(url);
       
       if(result.isPing()==true)
       {
       System.out.println(result.getICMPEchoPacket()
               + " bytes from "
               + result.getAddress()
               + ": icmp_seq="
               + result.getIcmp()
               + ". time="
               + result.getPingTime() + " ms");
       }else
       {
    	   System.out.println("Not Ping");
       }
      
        }
//        System.out.println("..............................................IP Array Test...................................");
        List ipList=new ArrayList<String>();
        ipList.add("www.google.com");
        ipList.add("8.8.8.8");
      
//        
      List<PingResult> myList=  p.ping(ipList);
      for(int i=0;i<myList.size();i++)
      {
         PingResult result= myList.get(i);
         
         if(result.isPing()==true)
         {
         System.out.println(result.getICMPEchoPacket()
                 + " bytes from "
                 + result.getAddress()
                 + ": icmp_seq="
                 + result.getIcmp()
                 + ". time="
                 + result.getPingTime() + " ms");
         }else
         {
      	   System.out.println("Not Ping");
         }

      }
    }
}
