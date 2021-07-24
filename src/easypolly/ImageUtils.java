package easypolly;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.AffineTransformOp;

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

    public static BufferedImage getBinary(final BufferedImage image, final float brightnessPercentage, boolean invert) {
        BufferedImage binarizedImage = null;
        
        if (image != null) {
            int h = image.getHeight();
            int w = image.getWidth();

            binarizedImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        
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
                        binarizedImage.setRGB(i, j, invert? Color.BLACK.getRGB() : Color.WHITE.getRGB());
                    } else {
                        // for dark color it will set black
                        binarizedImage.setRGB(i, j, invert? Color.WHITE.getRGB() : Color.BLACK.getRGB());
                    }
                }
            }
        }
        
        return binarizedImage;
    }
    
    public static BufferedImage getBinary(final BufferedImage image, 
                final float brightnessPercentage, final Color lightColor, final Color darkColor, boolean invert) {
        BufferedImage binarizedImage = null;
        
        if (image != null) {
            int h = image.getHeight();
            int w = image.getWidth();

            binarizedImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        
            for (int i = 0; i < w; i++) {
                for (int j = 0; j < h; j++) {

                    //Get RGB Value
                    int val = image.getRGB(i, j);
                    //Convert to three separate channels
                    int r = (0x00ff0000 & val) >> 16;
                    int g = (0x0000ff00 & val) >> 8;
                    int b = (0x000000ff & val);
                    int m = (r + g + b);
                    
                    //(255+255+255)/2 = 283 middle of dark and light
                    final int darknessValue = Math.round((255 * 3) * ((100 - brightnessPercentage) / 100));
                    
                    if (m >= darknessValue) {
                        //light color
                        binarizedImage.setRGB(i, j, invert? darkColor.getRGB() : lightColor.getRGB());
                    } else {
                        //dark color
                        binarizedImage.setRGB(i, j, invert? lightColor.getRGB() : darkColor.getRGB());
                    }
                }
            }
        }
        
        return binarizedImage;
    }
    
    public static BufferedImage clearColor(final BufferedImage image, final Color color) {
        BufferedImage transparentImage = null;
        
        if (image != null) {
            int h = image.getHeight();
            int w = image.getWidth();

            transparentImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        
            for (int i = 0; i < w; i++) {
                for (int j = 0; j < h; j++) {
                    //Get RGB Value
                    int val = image.getRGB(i, j);
                    
                    if(val != color.getRGB()) {
                        transparentImage.setRGB(i, j, val);
                    } else {
                        continue;
                    }
                }
            }
        }
        
        return transparentImage;
    }
    
    public static BufferedImage getScaledImage(final BufferedImage original, float scaleFactor) {
        final int newWidth = Math.round(original.getWidth() * scaleFactor);
        final int newHeight = Math.round(original.getHeight() * scaleFactor);
        
        BufferedImage resized = new BufferedImage(newWidth, newHeight, original.getType());
        Graphics2D g = resized.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
            RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(original, 0, 0, newWidth, newHeight, 0, 0, original.getWidth(),
            original.getHeight(), null);
        g.dispose();
        
        return resized;
    }
}
