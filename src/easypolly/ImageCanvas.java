
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
    
    private boolean darkBackground = true;
    private static final int TILE_SIZE = 32;
    private boolean showGuides = true;
    
    
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        
        //Background
        final int horizontalTiles = this.getWidth() / TILE_SIZE + 1;
        final int verticalTiles = this.getWidth() / TILE_SIZE + 1;
        
        for(int row = 0; row <= verticalTiles; row++) {
            for(int column = 0; column <= horizontalTiles; column++) {
                if((row % 2 == 0 && column % 2 == 0) || (row % 2 != 0 && column % 2 != 0)) {
                    g.setColor(darkBackground? Color.GRAY : Color.LIGHT_GRAY);
                } else {
                    g.setColor(darkBackground? Color.DARK_GRAY : Color.WHITE);
                }
                
                //Alternative background effect
                //g.fillRect(row * TILE_SIZE, column * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                
                g.fillRect(row * TILE_SIZE, column * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
        }
        
        if(displayImage != null) {
            g.setColor(darkBackground? new Color(255, 255, 255, 60) : new Color(0, 0, 0, 90));
            g.fillRect(imageXCoordinate, imageYCoordinate, this.getImageWidth(), this.getImageHeight());
            
            //TODO: Apply transformation to rotate
            g.drawImage(displayImage, imageXCoordinate, imageYCoordinate, this.getImageWidth(), this.getImageHeight(), this);
            
            //g.setColor(Color.red);
            //g.drawRect(imageXCoordinate, imageYCoordinate, this.getImageWidth(), this.getImageHeight());
        }
        
        if(showGuides) {
            //Draw metric guides
            //Distance in pixels
            final int smallGuidesDistance = 10;
            //Distance per small guides
            final int bigGuidesDistance = 5;
            final int smallGuidesSize = 7;
            final int bigGuidesSize = 15;
            final Font guidesFont = new Font("Consolas", Font.PLAIN, 10);
            final FontMetrics guideFontMetrics = g.getFontMetrics(guidesFont);

            //Image guides
            if(this.displayImage != null) {
                g.setColor(new Color(0, 128, 255));
                if(this.getImageY() > smallGuidesSize) {
                    g.fillRect(this.getImageX(), 0, this.getImageWidth(), 5);
                }
                if(this.getImageBottom() < this.getHeight() - smallGuidesSize) {
                    g.fillRect(this.getImageX(), this.getHeight() - 5, this.getImageWidth(), 5);
                }
                if(this.getImageX() > smallGuidesSize) {
                    g.fillRect(0, this.getImageY(), 5, this.getImageHeight());
                }
                if(this.getImageRight() < this.getWidth() - smallGuidesSize) {
                    g.fillRect(this.getWidth() - 5, this.getImageY(), 5, this.getImageHeight());
                }
            }

            //Horizontal guides
            g.setColor(darkBackground? Color.WHITE : Color.BLACK);
            g.setFont(guidesFont);

            for(int i = 1; i < Math.round(this.getWidth() / smallGuidesDistance); i ++) {
                int x = i * smallGuidesDistance;

                if(i % bigGuidesDistance == 0) {
                    g.drawLine(x, 0, x, bigGuidesSize);
                    g.drawLine(x, this.getHeight(), x, this.getHeight() - bigGuidesSize);

                    g.drawString(String.valueOf(x), x + 2, bigGuidesSize + (guideFontMetrics.getHeight() / 2));
                    g.drawString(String.valueOf(x), x + 2, this.getHeight() - bigGuidesSize + (guideFontMetrics.getHeight() / 2));
                } else {
                    g.drawLine(x, 0, x, smallGuidesSize);
                    g.drawLine(x, this.getHeight(), x, this.getHeight() - smallGuidesSize);
                }
            }

            //Vertical guides
            for(int i = 1; i < Math.round(this.getHeight() / smallGuidesDistance); i++) {
                int y = i * smallGuidesDistance;

                if(i % bigGuidesDistance == 0) {
                    g.drawLine(0, y, bigGuidesSize, y);
                    g.drawLine(getWidth(), y, getWidth() - bigGuidesSize, y);

                    g.drawString(String.valueOf(y), bigGuidesSize - (guideFontMetrics.stringWidth(String.valueOf(y)) / 2), y - 2);
                    g.drawString(String.valueOf(y), this.getWidth() - bigGuidesSize - (guideFontMetrics.stringWidth(String.valueOf(y)) / 2), y - 2);
                } else {
                    g.drawLine(0, y, smallGuidesSize, y);
                    g.drawLine(getWidth(), y, getWidth() - smallGuidesSize, y);
                }
            }
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

    public boolean isDarkBackground() {
        return darkBackground;
    }

    public void setDarkBackground(boolean darkBackground) {
        final boolean isDifferent = this.darkBackground != darkBackground;
        
        if(isDifferent) {
            this.darkBackground = darkBackground;
        
            this.repaint();
        }
    }
    
    public int getImageX(){
        return this.imageXCoordinate;
    }
    
    public void setImageXCoordinate(int xCoordinate){
        this.imageXCoordinate = xCoordinate;
        
        this.repaint();
    }
    
    public int getImageY(){
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
    
    public boolean isShowingGuides() {
        return showGuides;
    }

    public void setShowGuides(boolean showGuides) {
        this.showGuides = showGuides;
    }
    
    // </editor-fold>  

    
}
