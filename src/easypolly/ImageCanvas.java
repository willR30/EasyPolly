
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
    private float imageScale = 100f;
    
    private static final int TILE_SIZE = 32;
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        
        //Background
        final int horizontalTiles = this.getWidth() / TILE_SIZE + 1;
        final int verticalTiles = this.getWidth() / TILE_SIZE + 1;
        
        for(int row = 0; row <= verticalTiles; row++) {
            for(int column = 0; column <= horizontalTiles; column++) {
                if((row % 2 == 0 && column % 2 == 0) || (row % 2 != 0 && column % 2 != 0)) {
                    g.setColor(Color.GRAY);
                } else {
                    g.setColor(Color.DARK_GRAY);
                }
                
                //Alternate background effect
                //g.fillRect(row * TILE_SIZE, column * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                
                g.fillRect(row * TILE_SIZE, column * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
        }
        
        if(displayImage != null) {
            //TODO: Apply transformation to rotate
            g.drawImage(displayImage, imageXCoordinate, imageYCoordinate, this.getImageWidth(), this.getImageHeight(), this);
        }
    }
    
    public void centerImage() {
        this.imageXCoordinate = Math.round(this.getWidth() / 2f) - Math.round(this.getImageWidth() / 2f);
        this.imageYCoordinate = Math.round(this.getHeight() / 2f) - Math.round(this.getImageHeight() / 2f);
        
        this.repaint();
    }
    
    public void centerImageVertically() {
        this.imageYCoordinate = Math.round(this.getHeight() / 2f) - Math.round(this.getImageHeight() / 2f);
        
        this.repaint();
    }
    
    public void centerImageHorizontally() {
        this.imageXCoordinate = Math.round(this.getWidth() / 2f) - Math.round(this.getImageWidth() / 2f);
        
        this.repaint();
    }
    
    // <editor-fold defaultstate="collapsed" desc="Setters and Getters">
    public BufferedImage getDisplayImage() {
        return this.displayImage;
    }
    
    
    public void setDisplayImage(BufferedImage displayImage) {
        this.displayImage = displayImage;
        
        this.repaint();
    }
    
    public void setDisplayImage(Image displayImage) {
        BufferedImage newBufferImage = ImageUtils.getBufferedImage(displayImage);
        
        this.setDisplayImage(displayImage);
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
        return this.displayImage == null? 0 : this.imageXCoordinate + this.getImageWidth();
    }
    
    public int getImageBottom() {
        return this.displayImage == null? 0 : this.imageYCoordinate + this.getImageHeight();
    }
    
    public int getImageWidth() {
        return this.displayImage == null? 0 : Math.round(this.displayImage.getWidth() * this.imageScale / 100f);
    }
    
    public int getImageHeight() {
        return this.displayImage == null? 0 : Math.round(this.displayImage.getHeight() * this.imageScale / 100f);
    }
    
    public Rectangle getImageBounds(){
        return this.displayImage == null? null : new Rectangle(this.imageXCoordinate, this.imageYCoordinate, this.getImageWidth(), this.getImageHeight());
    }
    
    public float getImageRotationAngle(){
        return this.imageRotationAngle;
    }
    
    public void setImageRotationAngle(float rotationAngle){
        this.imageRotationAngle = rotationAngle;
        
        this.repaint();
    }
    
    public float getImageScale() {
        return imageScale;
    }

    public void setImageScale(float imageScale) {
        this.imageScale = imageScale;
        
        this.repaint();
    }
    
    // </editor-fold>  
}
