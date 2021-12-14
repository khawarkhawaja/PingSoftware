package PingSoftware.PingSoftware;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class PingImplementationTest {

	@Test
	public void testPing_String() {
		   System.out.println("Ping Test");
	        String ipOrHostname = "www.google.com";
	        PingImplementation instance = new PingImplementation();
	        PingResult expResult = new PingResult();

	        expResult.setResponseCode(200);
	  
	        PingResult result = instance.ping(ipOrHostname);
	        assertEquals(expResult.getResponseCode(), result.getResponseCode());
	     
//	       String pingStatus="Replay from:"+ ipOrHostname+ "    Ping Status :"+result.isIsOK()+"    Response Code: "+result.getResponseCode()+"   Message:"+result.getMessage()+"     Ping Time(ms) :"+result.getPingTime();
//	       System.out.println(pingStatus);
	       //Ping Test for 8.8.8.8.........................................
	       
	         ipOrHostname = "8.8.8.8";
	        PingImplementation instance2 = new PingImplementation();
	         expResult = new PingResult();

	        expResult.setResponseCode(200);
	  
	         result = instance2.ping(ipOrHostname);
	        assertEquals(expResult.getResponseCode(), result.getResponseCode());
	}
	@Test
    public void testPing_List() {
        System.out.println("Array Ping Test");
        List ipOrHostnameList = new ArrayList<String>();
        ipOrHostnameList.add("www.google.com");
        ipOrHostnameList.add("8.8.8.8");
        
    
        PingImplementation instance = new PingImplementation();
        List expResult = new ArrayList<PingResult>();
        PingResult obj1=new PingResult();
        obj1.setResponseCode(200);
          PingResult obj2=new PingResult();
        obj2.setResponseCode(200);
        expResult.add(obj1);
        expResult.add(obj2);
        
        List result = instance.ping(ipOrHostnameList);
        
        for(int i=0;i<expResult.size();i++)
        {
            PingResult exp=(PingResult)expResult.get(i);
            PingResult res=(PingResult)result.get(i);
            assertEquals(exp.getResponseCode(), res.getResponseCode());
//          String  pingStatus="Replay from:"+ ipOrHostnameList.get(i)+ "    Ping Status :"+res.isIsOK()+"    Response Code: "+res.getResponseCode()+"   Message:"+res.getMessage()+"     Ping Time(ms) :"+res.getPingTime();
//       System.out.println(pingStatus);
            
        }
        
     
      
    }

}
