package PingSoftware.PingSoftware;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class PingImplementationTest {

	@Test
	public void testPing_String() {
		   System.out.println("Ping Test");
	        String ipOrHostname = "8.8.8.2";
	        PingImplementation instance = new PingImplementation();
	        PingResult expResult = new PingResult();

	        expResult.setPing(true);
	  
	        PingResult result = instance.ping(ipOrHostname);
	        assertEquals(expResult.isPing(), result.isPing());
	     

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
        obj1.setPing(true);
          PingResult obj2=new PingResult();
        obj2.setPing(true);
        expResult.add(obj1);
        expResult.add(obj2);
        
        List result = instance.ping(ipOrHostnameList);
        
        for(int i=0;i<expResult.size();i++)
        {
            PingResult exp=(PingResult)expResult.get(i);
            PingResult res=(PingResult)result.get(i);
            assertEquals(exp.isPing(), res.isPing());

            
        }
        
     
      
    }

}
