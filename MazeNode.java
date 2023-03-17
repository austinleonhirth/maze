import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;

public class MazeNode extends JButton{
    
    /* STATUS INFORMATION
     * 0 = NORMAL NODE
     * 1 = START NODE
     * 2 = END NODE
     * 3 = WALL  
    */
    private int status = 0;
    private Color normal    = new Color(200,200,200);
    private Color end       = new Color(200,10,29);
    private Color wall      = new Color(10,10,10);
    private Color start     = new Color(20,190,10);

    public MazeNode(){
        setBackground(normal);
        setFocusPainted(false);
        setFocusable(false);
        setVisible(true);

        
    }
    public void setStatus(int n){
        if(n==0){
            setBackground(normal);
            status = n;
        }
        else if(n==1){
            setBackground(start);
            status = n;
        }
        else if(n==2){
            setBackground(end);
            status = n;
        }
        else if(n==3)
        {
            setBackground(wall);
            status = n;
        }
    }
    public int getStatus(){
        return status;
    }
    
    
}