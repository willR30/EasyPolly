
package easypolly;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author Robert MedLo
 */
public class ImageCanvas extends JPanel {
    
    //The image going to be drawed
    private BufferedImage displayImage = null;
    private int imageXCoordinate = 0;
    private int imageYCoordinate = 0;
    private float imageRotationAngle = 0;
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        
        if(displayImage != null) {
            //TODO: Apply transformation to rotate
            g.drawImage(displayImage, imageXCoordinate, imageYCoordinate, this);
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="Setters and Getters">
    public Image getDisplayImage() {
        return this.displayImage;
    }
    
    
    public void setDisplayImage(BufferedImage displayImage) {
        this.displayImage = displayImage;
        this.imageXCoordinate = 0;
        this.imageYCoordinate = 0;
        
        this.repaint();
    }
    
    public void setDisplayImage(Image displayImage) {
        BufferedImage newBufferImage = new BufferedImage(displayImage.getWidth(null), displayImage.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        Graphics2D bufferGraphics = newBufferImage.createGraphics();
        bufferGraphics.drawImage(displayImage, 0, 0, null);
        bufferGraphics.dispose();
        
        this.displayImage = newBufferImage;
        this.imageXCoordinate = 0;
        this.imageYCoordinate = 0;
        
        this.repaint();
    }
    
    public int getImageXCoordinate(){
        return this.imageXCoordinate;
    }
    
    public void setImageXCoordinate(int xCoordinate){
        this.imageXCoordinate = xCoordinate;
        
        this.repaint();
    }
    
    public int getImageYCoordinate(){
        return this.imageYCoordinate;
    }
    
    public void setImageYCoordinate(int yCoordinate){
        this.imageYCoordinate = yCoordinate;
        
        this.repaint();
    }
    
    public int getImageRight() {
        return this.displayImage == null? 0 : this.imageXCoordinate + this.displayImage.getWidth();
    }
    
    public int getImageLeft() {
        return this.displayImage == null? 0 : this.imageYCoordinate + this.displayImage.getHeight();
    }
    
    public int getImageWidth() {
        return this.displayImage == null? 0 : this.displayImage.getWidth();
    }
    
    public int getImageHeight() {
        return this.displayImage == null? 0 : this.displayImage.getHeight();
    }
    
    public Rectangle getImageBounds(){
        return this.displayImage == null? null : new Rectangle(this.imageXCoordinate, this.imageYCoordinate, this.displayImage.getWidth(), this.displayImage.getHeight());
    }
    
    public float getImageRotationAngle(){
        return this.imageRotationAngle;
    }
    
    public void setImageRotationAngle(float rotationAngle){
        this.imageRotationAngle = rotationAngle;
        
        this.repaint();
    }
    // </editor-fold>  
}
