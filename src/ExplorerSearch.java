import java.util.ArrayList;
import java.util.List;

public class ExplorerSearch {

    /**
     * Returns how much land area an explorer can reach on a rectangular island.
     * 
     * The island is represented by a rectangular int[][] that contains
     * ONLY the following nunbers:
     * 
     * '0': represents the starting location of the explorer
     * '1': represents a field the explorer can walk through
     * '2': represents a body of water the explorer cannot cross
     * '3': represents a mountain the explorer cannot cross
     * 
     * The explorer can move one square at a time: up, down, left, or right.
     * They CANNOT move diagonally.
     * They CANNOT move off the edge of the island.
     * They CANNOT move onto a a body of water or mountain.
     * 
     * This method should return the total number of spaces the explorer is able
     * to reach from their starting location. It should include the starting
     * location of the explorer.
     * 
     * For example
     * 
     * @param island the locations on the island
     * @return the number of spaces the explorer can reach
     */
    public static int reachableArea(int[][] island) {
        // Implement your method here!
        // Please also make more test cases
        // I STRONGLY RECOMMEND testing some helpers you might make too

        boolean[][] visited = new boolean[island.length][island[0].length];
        reachableArea(island, explorerLocation(island), visited);
        int count = 0;

        for (boolean[] arr : visited){
            for(int i = 0; i < arr.length; i++){
                if(arr[i] == true){
                    count++;
                }
            }
        }
        return count;

    }
    public static void reachableArea(int[][] island, int[] loc, boolean[][] visited) {
        int currentRow = loc[0];
        int currentColumn = loc[1];
        if(island[currentRow][currentColumn] == 1)return;
        if(visited[currentRow][currentColumn]) return;

        visited[currentRow][currentColumn] = true;

        List<int[]> validLocs = possibleMoves(island, loc);
        for(int[] move: validLocs){
            reachableArea(island, move, visited);
        }

    }


    public static int[] explorerLocation(int[][] island){
        int count = 0;

        for (int[] arr : island){
            for(int i = 0; i < arr.length; i++){
                if(arr[i] == 0){
                    return new int[]{count, i};
                }
            }
            count++;
        }

        throw new IllegalArgumentException("No explorer present");
    }

    public static List<int[]> possibleMoves(int[][] island, int[] location){
        int currentRow = location[0];
        int currentColumn = location[1];
        List<int[]> validLocs = new ArrayList<>();

        //up
        int newR = currentRow-1;
        int newC = currentColumn;
        if(newR >= 0 && island[newR][newC] != 3 && island[newR][newC] != 2){
            validLocs.add(new int[]{newR, newC});
        }
        //down
        newR = currentRow+1;
        newC = currentColumn;
        if(newR < island.length && island[newR][newC] != 3 && island[newR][newC] != 2){
            validLocs.add(new int[]{newR, newC});
        }
        //right
        newR = currentRow;
        newC = currentColumn+1;
        if(newC < island[0].length && island[newR][newC] != 3 && island[newR][newC] != 2){
            validLocs.add(new int[]{newR, newC});
        }
        //left
        newR = currentRow;
        newC = currentColumn-1;
        if(newC >= 0 && island[newR][newC] != 3 && island[newR][newC] != 2){
            validLocs.add(new int[]{newR, newC});
        }
        return validLocs;


    }
}
