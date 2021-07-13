package easypolly;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 *
 * @author Barry S.
 */
public class ImageUtils {
    
    public static BufferedImage getBufferedImage(Image image) {
        BufferedImage newBufferImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        Graphics2D bufferGraphics = newBufferImage.createGraphics();
        bufferGraphics.drawImage(image, 0, 0, null);
        bufferGraphics.dispose();
        
        return newBufferImage;
    }

    public static BufferedImage getBinary(final BufferedImage image, final float brightnessPercentage) {
        int h = image.getHeight();
        int w = image.getWidth();

        BufferedImage binarizedImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        if (image != null) {
            for (int i = 0; i < w; i++) {
                for (int j = 0; j < h; j++) {

                    //Get RGB Value
                    int val = image.getRGB(i, j);
                    //Convert to three separate channels
                    int r = (0x00ff0000 & val) >> 16;
                    int g = (0x0000ff00 & val) >> 8;
                    int b = (0x000000ff & val);
                    int m = (r + g + b);
                    
                    final int darknessValue = Math.round((255 * 3) * ((100 - brightnessPercentage) / 100));
                    //(255+255+255)/2 = 283 middle of dark and light
                    if (m >= darknessValue) {
                        // for light color it sets white
                        binarizedImage.setRGB(i, j, Color.WHITE.getRGB());
                    } else {
                        // for dark color it will set black
                        binarizedImage.setRGB(i, j, 0);
                    }
                }
            }
        }
        
        return binarizedImage;
    }
}
