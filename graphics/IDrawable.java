package graphics;
import java.awt.Graphics;

import vehicles.Colors;

public interface IDrawable 
{
		 public final static String PICTURE_PATH = "C:\\Users\\Or\\Desktop\\graphics\\graphics\\"; //Enter images path
		 public void loadImages(String nm);
		 public void drawObject (Graphics g);
		 public Colors getColor();
}