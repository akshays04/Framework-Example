package mahli;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

import javax.imageio.ImageIO;

import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ProcessImage {
	
	@SuppressWarnings("unchecked")
	public void getImageColor(File imagePath) throws FileNotFoundException, IOException, ParseException {
		String jsonPath = "Mineral.json";
		File jsonFile = new File(jsonPath);
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObj = (JSONObject) jsonParser.parse(new FileReader(jsonFile));
        int color=0;
        int count=0;
        ColorUtils objColorUtils=new ColorUtils();
        HashSet hs=new HashSet();
        HashMap hm=new HashMap();
		try {
			@SuppressWarnings("rawtypes")
			
			BufferedImage image = ImageIO.read(imagePath);
				color = image.getRGB(0, 0);
				for (int r = 0; r < image.getHeight(); r += 1) {
				    for (int c = 0; c < image.getWidth(); c += 1) {
				    	hs.add(new Color(image.getRGB(c, r)));
				    	count++;
				       
				        }
				    }
			    //System.out.println(count);
				//System.out.println(hs.size());
				//System.out.println(hs);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String Minfo="";
		Iterator itr = hs.iterator();
		while(itr.hasNext()){
			Color colorobj=(Color)itr.next();
			Minfo=objColorUtils.getColorNameFromRgb(colorobj.getRed(), colorobj.getGreen(), colorobj.getBlue());
			Double c=0.0;
			String Mineral=Minfo.split(":")[1];
			//System.out.println(Mineral);
			if(jsonObj.containsKey(Mineral))
			{
				c= (Double) jsonObj.get(Mineral);
				jsonObj.put(Mineral,c+1.0);
			}
			else
			{
			jsonObj.put(Mineral,1.0);
			}
		  }
		System.out.println(jsonObj);
		//JSONObject obj = new JSONObject(hm);
		
		try {
			FileWriter file = new FileWriter("Mineral.json");
			file.write(jsonObj.toJSONString());
			System.out.println("Data has been written to file Mineral.JSON");
			file.flush();
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        
    }


}
