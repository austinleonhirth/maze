import java.util.*;

public class Pathfinding{

    private static final int INF = Integer.MAX_VALUE;

    ArrayList<MazeNode> path = new ArrayList<MazeNode>();
    int v[][]; 

    public Pathfinding(){

    }

    public List<int[]> DijkstraSolve(ArrayList<ArrayList<MazeNode>> nodeList){
        
        /*
         * Setup nodeList in the form of a 2D array of ints
         * this 2D array of ints will be much simpler to run dijkstras on
         * each element at (x,y) in the matrix is the status of node(x,y)
         * solve dijkstras by pathing along all nodes of status zero
         */
        v = new int[nodeList.size()][nodeList.size()];

        for(int x = 0; x < nodeList.size(); x++){
            for(int y = 0; y < nodeList.get(x).size(); y++){
                v[x][y] = nodeList.get(x).get(y).status;
            }
        }
        
        //Get starting and ending points
        int[] start = new int[2];
        int[] end   = new int[2];
        
        for(int x = 0; x < v.length; x++){
            for(int y = 0; y < v.length; y++){
                if(v[x][y]==1)
                {
                    start[0]=x;
                    start[1]=y; 
                }
                if(v[x][y]==2)
                {
                    end[0]=x;
                    end[1]=y; 
                }
            }
        }
        //Obtained start and end

        List<int[]> shortestPath = dijkstra(v, start, end);
        int maxLength = 0;
        for (int[] arr : shortestPath) {
            if (arr.length > maxLength) {
                maxLength = arr.length;
            }
        }

        // Print the elements in matrix format
        for (int[] arr : shortestPath) {
            for (int i = 0; i < maxLength; i++) {
                if (i < arr.length) {
                    System.out.print(arr[i] + " ");
                } else {
                    System.out.print("  "); // Print two spaces to maintain spacing in the matrix
                }
            }
            System.out.println(); // Print a new line after each int array is printed
        }
        
        return shortestPath;

    }
    
    public static List<int[]> dijkstra(int[][] v, int[] start, int[] end) {
        int n = v.length;
        int m = v[0].length;
    
        double[][] dist = new double[n][m];
        boolean[][] visited = new boolean[n][m];
        int[][] prev = new int[n][m];
    
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Double.POSITIVE_INFINITY);
        }
    
        dist[start[0]][start[1]] = 0;
    
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingDouble(a -> dist[a[0]][a[1]]));
        pq.offer(start);
    
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
    
            if (visited[curr[0]][curr[1]]) {
                continue;
            }
    
            visited[curr[0]][curr[1]] = true;
    
            if (curr[0] == end[0] && curr[1] == end[1]) {
                break;
            }
    
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (i == 0 && j == 0) {
                        continue;
                    }
    
                    int row = curr[0] + i;
                    int col = curr[1] + j;
    
                    if (row < 0 || row >= n || col < 0 || col >= m) {
                        continue;
                    }
    
                    if (v[row][col] == 3) {
                        continue;
                    }
    
                    double cost = 1.0;
                    if (i != 0 && j != 0) {
                        cost = Math.sqrt(2);
                    }
    
                    double alt = dist[curr[0]][curr[1]] + cost;
    
                    if (alt < dist[row][col]) {
                        dist[row][col] = alt;
                        prev[row][col] = curr[0] * m + curr[1];
                        pq.offer(new int[] {row, col});
                    }
                }
            }
        }
    
        List<int[]> path = new ArrayList<>();
        int curr = end[0] * m + end[1];
    
        while (curr != start[0] * m + start[1]) {
            int row = curr / m;
            int col = curr % m;
            path.add(0, new int[] {row, col});
            curr = prev[row][col];
        }
    
        path.add(0, start);
    
        return path;
    }

    private void printArray(int a[][]){
        for(int x = 0; x < a.length; x++){
            for(int y = 0; y < a.length; y++){
                System.out.print(" "+a[x][y]);
            }
            System.out.print("\n");
        }
    }
}