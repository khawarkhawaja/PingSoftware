package PingSoftware.PingSoftware;

import java.util.List;

/**
 *
 * @author pc
 */
public interface PingService {
   PingResult ping(String ipOrHostname);
   List ping(List ipOrHostnameList);
}