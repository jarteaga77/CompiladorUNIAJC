package compiladoruniajc;

/**
 *
 * @author Jonathan
 */
import java.awt.*;


/**
 */
public class JPColor extends javax.swing.JPanel{
    private Color color1 = new Color(0,0,0);
    private Color color2 = new Color(0,0,0);
    
    public JPColor(){
    }
    
    public JPColor(Color c1, Color c2){
        color1 = c1;
        color2 = c2;        
    }
    
    @Override
    public void paint(Graphics g) {    
        Graphics2D g2 = (Graphics2D) g.create();
        Rectangle clip = g2.getClipBounds();                        
        g2.setPaint(new GradientPaint(0.0f, 0.0f, color1.darker(),0.0f ,getHeight(), color2.darker()));        
        g2.fillRect(clip.y, clip.x, clip.width, clip.width);        
    }
    
    
    public void setVistaColor(){        
        this.color1 = new Color(66,111,166);
        this.color2 = new Color(103,174,118);        
    }
    
}