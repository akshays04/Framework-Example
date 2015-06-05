package mahli;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;

import javax.imageio.ImageIO;
import java.util.Iterator;

public class ProcessImage {
	
	@SuppressWarnings("unchecked")
	public void getImageColor(File imagePath) {
        int color=0;
        int count=0;
        ColorUtils objColorUtils=new ColorUtils();
        HashSet hs=new HashSet();
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
			
		Iterator itr = hs.iterator();
		while(itr.hasNext()){
			Color colorobj=(Color)itr.next();
			System.out.println(objColorUtils.getColorNameFromRgb(colorobj.getRed(), colorobj.getGreen(), colorobj.getBlue()));
		  }

        
    }


}
