package mahli;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import generic.RoverServerRunnable;
import generic.RoverThreadHandler;

public class MAHLIServer extends RoverServerRunnable {

	public MAHLIServer(int port) throws IOException {
		super(port);
	}

	@Override
	public void run() {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Boolean camOnStatus = false;
		Boolean nightIlluminationStatus = false;
		Boolean autoFocusStatus = false;
		Boolean videoStatus = false;
		Boolean imageCaptureStatus = false;
		Boolean imageStoreStatus = false;
		Boolean imageReadStatus = false;
		Boolean exitStatus = false;
		int port_power = 9013;

		try {
			while (true) {
				System.out.println("MAHLI Server: Waiting for client request");
				int command;
				
				// creating socket and waiting for client connection
				getRoverServerSocket().openSocket();
				
				// read from socket to ObjectInputStream object
				ObjectInputStream inputFromAnotherObject = new ObjectInputStream(getRoverServerSocket().getSocket().getInputStream());
				
				// convert ObjectInputStream object to String
				String message = (String) inputFromAnotherObject.readObject();
				System.out.println("MAHLI Server: Command Received from Client - "+ message);
				if(message.equals("MAHLI_Camera_ON"))
					command = 1;
				else if(message.equals("MAHLI_Camera_OFF"))
					command = 2;
				else if(message.equals("MAHLI_NightIllumination_ON"))
					command = 3;
				else if(message.equals("MAHLI_NightIllumination_OFF"))
					command = 4;
				else if(message.equals("MAHLI_AutoFocus_ON"))
					command = 5;
				else if(message.equals("MAHLI_AutoFocus_OFF"))
					command = 6;
				else if(message.equals("MAHLI_Video_ON"))
					command = 7;
				else if(message.equals("MAHLI_Video_OFF"))
					command = 8;
				else if(message.equals("MAHLI_Image_Capture"))
					command = 9;
				else if(message.equals("MAHLI_Image_store"))
					command = 10;
				else if(message.equals("MAHLI_Image_read"))
					command = 11;
				else if(message.equals("EXIT"))
					command = 12;
				else 
					command = 13;
				
				// create ObjectOutputStream object
				ObjectOutputStream outputToAnotherObject = new ObjectOutputStream(getRoverServerSocket().getSocket().getOutputStream());
				
				// write object to Socket
				
				// getRoverServerSocket().closeSocket();
				
		        switch (command) {
		            case 1:  
		            		if(camOnStatus){
		            			//System.out.println("Camera is already On");
		            			outputToAnotherObject.writeObject("MAHLI Server response - Camera is already On");
		            		}
		            		else{
		            			//System.out.println("Camera turned On");
		            			camOnStatus = true;
		            			MAHLIClient clientMahli = new MAHLIClient(port_power, null);
		            			Thread client_3 = RoverThreadHandler.getRoverThreadHandler().getNewThread(clientMahli);
		            			outputToAnotherObject.writeObject("MAHLI Server response - Camera turned On ");
		            			client_3.start();
		            			}
		                     break;
		            case 2:  
		            		if(camOnStatus)
		            		{
		            			camOnStatus = false;
		            			//System.out.println("Camera turned off");
		            			outputToAnotherObject.writeObject("MAHLI Server response - Camera turned off");
		            		}
		            		else
		            		{
		            			//System.out.println("Camera is already turned off");
		            			outputToAnotherObject.writeObject("MAHLI Server response - Camera is already turned off");
		            		}
		                     break;
		            case 3:  
		            	if(camOnStatus)
	            		{
	            			if(nightIlluminationStatus){
	            				//System.out.println("Night Illumination is already tuned on");
	            				outputToAnotherObject.writeObject("MAHLI Server response - Night Illumination is already tuned on");
	            			}
	            			else
	            			{
	            				nightIlluminationStatus = true;
	            				//System.out.println("Night Illumination turned On");
	            				outputToAnotherObject.writeObject("MAHLI Server response - Night Illumination turned On");
	            			}
	            				
	            		}
	            		else
	            		{
	            			// System.out.println("Please turn on the camera to proceed");
	            			outputToAnotherObject.writeObject("Please turn on the camera to proceed");
	            		}
		                     break;
		            case 4:  if(camOnStatus)
            		{
            			if(nightIlluminationStatus){
            				nightIlluminationStatus = false;
            				// System.out.println("Night Illumination is turned off");
            			    outputToAnotherObject.writeObject("Night Illumination is turned off");

            			}
            			else
            			{
            				// System.out.println("Night Illumination already turned off");
            				outputToAnotherObject.writeObject("Night Illumination already turned off");

            			}
            				
            		}
            		else
            		{
            			// System.out.println("Please turn on the camera to proceed");
            			outputToAnotherObject.writeObject("Please turn on the camera to proceed");
            		}
		                     break;
		                     
		            case 5:  if(camOnStatus)
            		{
            			if(autoFocusStatus){
            				// System.out.println("Auto Focus is already turned ON");
            				outputToAnotherObject.writeObject("Auto Focus is already turned ON");
            			}
            			else
            			{
            				// System.out.println("Auto Focus is now turned ON");
            				outputToAnotherObject.writeObject("Auto Focus is now turned ON");
            				autoFocusStatus = true;
            			}
            				
            		}
            		else
            		{
            			// System.out.println("Please turn on the camera to proceed");
            			outputToAnotherObject.writeObject("Please turn on the camera to proceed");
            		}
		            
		                     break;
		            case 6:  
		            	if(camOnStatus)
	            		{
	            			if(autoFocusStatus){
	            				autoFocusStatus = false;
	            				// System.out.println("Auto Focus is now turned OFF");
	            				outputToAnotherObject.writeObject("Auto Focus is now turned OFF");
	            			}
	            			else
	            			{
	            				// System.out.println("Auto Focus is already turned OFF");
	            				outputToAnotherObject.writeObject("Auto Focus is already turned OFF");
	            			}
	            				
	            		}
	            		else
	            		{
	            			// System.out.println("Please turn on the camera to proceed");
	            		    outputToAnotherObject.writeObject("Please turn on the camera to proceed");

	            		}
		                     break;
		            case 7:  if(camOnStatus)
            		{
            			if(videoStatus){
            				// System.out.println("Video is already turned ON");
            				outputToAnotherObject.writeObject("Video is already turned ON");
            			}
            			else
            			{
            				// System.out.println("Video is now turned ON");
            				outputToAnotherObject.writeObject("ideo is now turned ON");
            				videoStatus = true;
            			}	
            		}
            		else
            		{
            			// System.out.println("Please turn on the camera to proceed");
            			outputToAnotherObject.writeObject("Please turn on the camera to proceed");
            		}
		                     break;
		            case 8:  if(camOnStatus)
            		{
            			if(videoStatus){
            				videoStatus = false;
            				// System.out.println("Video is now turned OFF");
            				outputToAnotherObject.writeObject("Video is now turned OFF");
            			}
            			else
            			{
            				System.out.println("Video is already turned OFF");
            				outputToAnotherObject.writeObject("Video is already turned OFF");

            			}
            				
            		}
            		else
            		{
            			// System.out.println("Please turn on the camera to proceed");
            			outputToAnotherObject.writeObject("Please turn on the camera to proceed");
            		}
		                     break;
		            case 9:  if(camOnStatus)
            		{
		            	imageCaptureStatus = true;
		            	// System.out.println("Image Captured");
		            	outputToAnotherObject.writeObject("Image Captured");

            		}
            		else
            		{
            			// System.out.println("Please turn on the camera to proceed");
            			outputToAnotherObject.writeObject("Please turn on the camera to proceed");
            		}
		                     break;
		            case 10: if(camOnStatus)
            		{
		            	imageStoreStatus = true;
		            	// System.out.println("Image Stored");
		            	outputToAnotherObject.writeObject("Image Stored");
            		}
		            else
            		{
            			// System.out.println("Please turn on the camera to proceed");
            			outputToAnotherObject.writeObject("IPlease turn on the camera to proceed");
            		}
		                     break;
		            case 11: if(camOnStatus)
            		{
		            	File file = new File("CS540ColorTest.jpg");
		    		    ProcessImage objprocessImage=new ProcessImage();
		            	imageReadStatus = true;
		            	// System.out.println("Image Read");
		            	//outputToAnotherObject.writeObject("Image Read");
		            	outputToAnotherObject.writeObject("COLOR OF IMAGE IS "+objprocessImage.getImageColor(file));
            		}
		            else
            		{
            			// System.out.println("Please turn on the camera to proceed");
            			outputToAnotherObject.writeObject("Please turn on the camera to proceed");
            		}
		                     break;
		            case 12: exitStatus = true;
		                     break;
		            case 13: 
		            	outputToAnotherObject.writeObject("Invalid Command");
                    		 break;
		            default: 
		            		outputToAnotherObject.writeObject("Exit");
		                     break;
		        }
		        
		     // terminate the server if client sends exit request
				if (message.equalsIgnoreCase("exit"))
					break;
		        
		     // close resources
				inputFromAnotherObject.close();
				outputToAnotherObject.close();
			}
			System.out.println("Server: Shutting down Socket server MAHLI!!");
			// close the ServerSocket object
			closeAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
