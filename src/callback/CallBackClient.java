package callback;

/*	
 * Created by: 	Jonathan Young
 * Date: 		May 14, 2015
 */

import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

import generic.RoverClientRunnable;

public class CallBackClient extends RoverClientRunnable{

	public CallBackClient(int port, InetAddress host)
			throws UnknownHostException {
		super(port, host);
	}

	@Override
	public void run() {
		try{
			ObjectOutputStream outputToAnotherObject = null;
		    
            //write to socket using ObjectOutputStream
            outputToAnotherObject = new ObjectOutputStream(getRoverSocket().getNewSocket().getOutputStream());
            
            System.out.println("Call Back 1 Client: Sending request to Control Server");

            outputToAnotherObject.writeObject("CCDS_DONE");

            //close resources
            outputToAnotherObject.close();
            closeAll();
		}	        
        catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception error) {
			//System.out.println("Client: Error:" + error.getMessage());
		}
		
	}

}

