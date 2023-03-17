import java.awt.Font;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.*;
import java.awt.event.*;

public class Main{

    //GLOBALS
    public final static int WINDOW_WIDTH   = 1200;
    public final static int WINDOW_HEIGHT  = 1000;


    /*
     * STATUS
     * 1 = PLACING START NODE
     * 2 = PLACING END NODE
     * 3 = PLACING WALL 
     * 4 = DONE MODE
     */
    public static int status = 1;

    public static ArrayList<MazeNode> path = new ArrayList<MazeNode>();

    public static void main(String[] args){

        int gridSize = 15;
        String algoList[] = {"Dijkstra"}; 

        
        Pathfinding pathfinder = new Pathfinding();

        JFrame window = new JFrame();
        MazePanel mazePanel = new MazePanel(gridSize);

        JLabel header   = new JLabel("Austin's Maze Machine");
        JLabel text1    = new JLabel("Follow the steps below: ");
        JLabel text2    = new JLabel(" #SET STARTING TILE");
        JLabel text3    = new JLabel(" #SET END TILE");
        JLabel text4    = new JLabel(" #PLACE WALLS");
        JLabel text6    = new JLabel("Now Select a Pathfinding Solution");

        JLabel err      = new JLabel("placeholder");

        JLabel text2a    = new JLabel("->");
        JLabel text3a    = new JLabel("->");
        JLabel text4a    = new JLabel("->");

        JButton completeB   = new JButton("Finalize Maze");
        JButton solveB      = new JButton("Solve Maze");
    

        Font headerFont = new Font("Consolas",Font.PLAIN,29);
        Font medFont = new Font("Consolas",Font.PLAIN,17);

        JComboBox dropDownBox = new JComboBox(algoList);

        /*
         * Window Initialization
         */
        window.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        window.setLayout(null);
        window.setVisible(true);
        window.setLocation(500, 100);           //meh its better than the default location
        window.setResizable(false);
        window.getContentPane().setBackground(new Color(80,80,80));
        window.setDefaultCloseOperation(3);     //Program exits when you press 'x'

        /*
         * Maze Panel Initialization
         */
        mazePanel.setBounds(30,30,800,800);


        /*
         * Add components
         */
        window.add(mazePanel);
        window.add(header);
        window.add(text1);
        window.add(text2);
        window.add(text3);
        window.add(text4);
        window.add(text2a);
        window.add(text3a);
        window.add(text4a);
        window.add(text6);
        window.add(completeB);
        window.add(err);
        window.add(dropDownBox);
        window.add(solveB);



        /*
         * Node Button init
         */
        for(int x = 0; x < gridSize; x++){
            for(int y = 0; y < gridSize; y++){
                MazeNode c = mazePanel.nodeList.get(x).get(y);
                c.addActionListener(new ActionListener() { 
                    public void actionPerformed(ActionEvent e) { 
                        //STATUS LOGIC
                    if(status != 4){
                        if(status == 2){
                            if(c.getStatus()==1)
                            {
                                err.setText("CANT PLACE END ON START BRUH");
                                status--;
                            }
                            else
                            {
                                //SUCCESSFULL END PLACEMENT
                                c.setStatus(2);
                                text3a.setVisible(false);
                                text4.setForeground(Color.WHITE);
                                text4a.setVisible(true);
                                completeB.setVisible(true);

                            }
                        }
                        else if(status == 3){
                            if(c.getStatus()==1){
                                err.setText("CANT PLACE WALL ON START BRUH");
                                status--;
                            }
                            else if(c.getStatus()==2){
                                err.setText("CANT PLACE WALL ON END BRUH");
                                status--;
                            }
                            else if(c.getStatus() == 3){
                                c.setStatus(0);
                            }
                            else{
                                c.setStatus(3);
                            }
                        }
                        else{
                            text2a.setVisible(false);
                            text3.setForeground(Color.WHITE);
                            text3a.setVisible(true);
                            c.setStatus(status);
                        }    
                        if(status < 3)
                            status++;
                        //END STATUS LOGIC
                    }
                } 
                  } );
            }
        }




        /*
         * Maze Creation section init
         */
        header.setForeground(Color.WHITE);
        header.setLocation(840,0);
        header.setFont(headerFont);
        header.setSize(400,100);
        header.setVisible(true);

        text1.setForeground(Color.WHITE);
        text1.setLocation(840,100);
        text1.setSize(400,50);
        text1.setFont(medFont);
        text1.setVisible(true);

        text2.setForeground(Color.WHITE);
        text2.setLocation(860,150);
        text2.setSize(400,50);
        text2.setFont(medFont);
        text2.setVisible(true);

        text2a.setForeground(Color.GREEN);
        text2a.setLocation(840,150);
        text2a.setSize(400,50);
        text2a.setFont(medFont);
        text2a.setVisible(true);

        text3.setForeground(Color.GRAY);
        text3.setLocation(860,200);
        text3.setSize(400,50);
        text3.setFont(medFont);
        text3.setVisible(true);

        text3a.setForeground(Color.GREEN);
        text3a.setLocation(840,200);
        text3a.setSize(400,50);
        text3a.setFont(medFont);
        text3a.setVisible(false);

        text4.setForeground(Color.GRAY);
        text4.setLocation(860,250);
        text4.setSize(400,50);
        text4.setFont(medFont);
        text4.setVisible(true);

        text4a.setForeground(Color.GREEN);
        text4a.setLocation(840,250);
        text4a.setSize(400,50);
        text4a.setFont(medFont);
        text4a.setVisible(false);

        text6.setForeground(Color.WHITE);
        text6.setLocation(860,400);
        text6.setSize(400,50);
        text6.setFont(medFont);
        text6.setVisible(false);

        err.setForeground(Color.RED);
        err.setLocation(870,800);
        err.setSize(400,50);
        err.setFont(medFont);
        err.setVisible(true);

        completeB.setBackground(Color.WHITE);
        completeB.setFont(medFont);
        completeB.setSize(160,30);
        completeB.setLocation(930, 320);
        completeB.setFocusable(false);
        completeB.setVisible(false);
        completeB.addActionListener(new ActionListener() { 
                    public void actionPerformed(ActionEvent e) { 
                        status = 4;
                        text4a.setVisible(false);
                        text1.setForeground(Color.GRAY);
                        text2.setForeground(Color.GRAY);
                        text3.setForeground(Color.GRAY);
                        text4.setForeground(Color.GRAY);
                        text6.setVisible(true);
                        completeB.setVisible(false);
                        dropDownBox.setVisible(true);
                        solveB.setVisible(true);
                    } 
                  } );
        
        solveB.setBackground(Color.WHITE);
        solveB.setFont(medFont);
        solveB.setBounds(1010,450,130,30);
        solveB.setFocusable(false);
        solveB.setVisible(false);
        solveB.addActionListener(new ActionListener() { 
                    public void actionPerformed(ActionEvent e) { 
                        
                        path = pathfinder.DijkstraSolve(MazePanel.nodeList);
                        System.out.println(path);
                    } 
                  } );
                  
        dropDownBox.setVisible(false);
        dropDownBox.setBounds(860,450,130,30);
    
    }
}
