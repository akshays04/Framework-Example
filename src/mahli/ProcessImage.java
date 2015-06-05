package mahli;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

import javax.imageio.ImageIO;

import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ProcessImage {
	
	@SuppressWarnings("unchecked")
	public void getImageColor(File imagePath) {
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
			Integer c=0;
			String Mineral=Minfo.split(":")[1];
			//System.out.println(Mineral);
			if(hm.containsKey(Mineral))
			{
				c=(Integer)hm.get(Mineral);
				hm.put(Mineral,c+1);
			}
			else
			{
			hm.put(Mineral,1);
			}
		  }
		System.out.println(hm);
		JSONObject obj = new JSONObject(hm);
		
		try {
			FileWriter file = new FileWriter("Mineral.json");
			file.write(obj.toJSONString());
			System.out.println("Data has been written to file Mineral.JSON");
			file.flush();
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        
    }


}
