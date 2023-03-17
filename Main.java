import javax.swing.*;

public class Main{

    //GLOBALS
    public final static int WINDOW_WIDTH   = 1200;
    public final static int WINDOW_HEIGHT  = 1000;


    public static void main(String[] args){

        JFrame window = new JFrame();
        MazePanel mazePanel = new MazePanel(10);

        /*
         * Window Initialization
         */
        window.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        window.setLayout(null);
        window.setVisible(true);
        window.setLocation(500, 100);           //meh its better than the default location
        window.setResizable(false);
        window.setDefaultCloseOperation(3);     //Program exits when you press 'x'

        /*
         * Add components
         */
        window.add(mazePanel);

        /*
         * Maze Panel Initialization
         */
        mazePanel.setBounds(30,30,800,800);
        
    }
}