package PingSoftware.PingSoftware;
import java.util.ArrayList;
import java.util.List;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	PingService p=new PingImplementation();
        String url="8.8.8.8";
        for(int i=0;i<4;i++)
        {
       PingResult result= p.ping(url);
       String pingStatus="Replay from:"+ url+ "    Ping Status :"+result.isIsOK()+"    Response Code: "+result.getResponseCode()+"   Message:"+result.getMessage()+"     Ping Time(ms) :"+result.getPingTime();
       System.out.println(pingStatus);
        }
        System.out.println("..............................................IP Array Test...................................");
        List ipList=new ArrayList<String>();
        ipList.add("www.google.com");
        ipList.add("8.8.8.8");
        ipList.add("www.fiverr.com");
        
      List<PingResult> myList=  p.ping(ipList);
      for(int i=0;i<myList.size();i++)
      {
          PingResult result= myList.get(i);
       String pingStatus="Replay from:"+ ipList.get(i)+ "    Ping Status :"+result.isIsOK()+"    Response Code: "+result.getResponseCode()+"   Message:"+result.getMessage()+"     Ping Time(ms) :"+result.getPingTime();
       System.out.println(pingStatus);
      }
    }
}
