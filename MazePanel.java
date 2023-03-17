import javax.swing.*;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

public class MazePanel extends JPanel{

    public ArrayList<ArrayList<MazeNode>> nodeList = new ArrayList<ArrayList<MazeNode>>();

    public MazePanel(int gridSize){
        
        super(new GridLayout(gridSize,gridSize));
        setBackground(new Color(152,152,152));

        //Adds nodes in a gridSize square
        for(int x = 0; x < gridSize; x++){
            nodeList.add(new ArrayList<MazeNode>());
            for(int y = 0; y < gridSize; y++){
                nodeList.get(x).add(new MazeNode());
                add(nodeList.get(x).get(y));
            }
        }
        //Adding done


    }

}