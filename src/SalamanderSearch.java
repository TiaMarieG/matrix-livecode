import java.util.ArrayList;
import java.util.List;

public class SalamanderSearch {
    public static void main(String[] args) {
        char[][] enclosure1 = {
            {'.','.','.','.','.','.'},
            {'W','.','W','W','.','.'},
            {'.','.','W','.','.','W'},
            {'f','W','.','.','W','.'},
            {'W','.','W','s','.','.'},
        };

        char[][] enclosure2 = {
            {'.','.','.','.','.','.'},
            {'W','W','W','W','s','.'},
            {'.','.','W','.','.','W'},
            {'f','W','.','.','W','.'},
            {'W','.','W','.','.','.'},
        };
    }

    /**
     * Returns whether a salamander can reach the food in an enclosure.
     * 
     * The enclosure is represented by a rectangular char[][] that contains
     * ONLY the following characters:
     * 
     * 's': represents the starting location of the salamander
     * 'f': represents the location of the food
     * 'W': represents a wall
     * '.': represents an empty space the salamander can walk through
     * 
     * The salamander can move one square at a time: up, down, left, or right.
     * It CANNOT move diagonally.
     * It CANNOT move off the edge of the enclosure.
     * It CANNOT move onto a wall.
     * 
     * This method should return true if there is any sequence of steps that
     * the salamander could take to reach food.
     * 
     * @param enclosure
     * @return whether the salamander can reach the food
     */
    public static boolean canReach(char[][] enclosure) {
        
        // Finding the salamander
        int[] start = salamanderLocation(enclosure);

        // Boolean array to check if a node has been visited
        // Automatically starts with everything false
        boolean[][] visited = new boolean[enclosure.length][enclosure[0].length];

        return canReach(enclosure, start, visited);
    }

    // Helper method to check if an index in the 2D array has been visited
    public static boolean canReach(char[][] enclosure, int[]current, boolean[][] visited) {

        // Base cases - If salamander has been here, return false
        int curR = current[0];
        int curC = current[1];

        // If already visited (visited == true)
        if (visited[curR][curC]) return false;

        // If food is found
        if(enclosure[curR][curC] == 'f') return true;

        // Handles printing arrays
        PrettyPrint.prettyPrintln(current);

        // Mark the locations as visited
        visited[curR][curC] = true;

        // Look at neighbors
        List<int[]> possibleMoves = possibleMoves(enclosure, current);

        PrettyPrint.prettyPrintln(possibleMoves);

        // Do a for loop to look at each surrounding position
        for (int[] move : possibleMoves) {
            // If path can be moved to, no need to keep checking - move
            if (canReach(enclosure, move, visited)) return true;
        }

        // If salamander can't move
        return false;
    }

    //Helper method to determine where the salamander can move given its current location
    public static List<int[]> possibleMoves(char[][] enclosure, int[] currentLocation) {

        // 0/1 are the indexes of a standard array
        int curR = currentLocation[0];
        int curC = currentLocation[1];

        // Create a new list of integer arrays
        // Stores up to 4 pair values of where salamander can move
        List<int[]> moves = new ArrayList<>();

        // Checking UP
        int newR = curR - 1;
        int newC = curC;

        // Checking to see if it's okay to move up
        if (newR >= 0 && enclosure[newR][newC] != 'W') {
            moves.add(new int[]{newR, newC});
        }

        // Checking DOWN
        newR = curR + 1;
        newC = curC;

        if (newR < enclosure.length && enclosure[newR][newC] != 'W') {
            moves.add((new int[]{newR, newC}));
        }

        // Checking LEFT
        newR = curR;
        newC = curC - 1;

        if (newC >= 0 && enclosure[newR][newC] != 'W') {
            moves.add(new int[]{newR, newC});
        }

        // Checking Right
        newR = curR;
        newC = curC + 1;

        if (newC < enclosure[0].length && enclosure[newR][newC] != 'W') {
            moves.add(new int[]{newR, newC});
        }

        return moves;
    }

    // Helper method to find where the salamander is as within the 2D array
    public static int[] salamanderLocation(char[][] enclosure) {

        // Use a nested for loop to check through rows/columns to find the salamander
        for (int r = 0; r < enclosure.length; r++) {
            // For rectangular arrays you can use enclosure[0]
            // For a jagged array, use enclosure[r].length
            for (int c = 0; c < enclosure[0].length; c++) {
                if (enclosure[r][c] == 's') {
                    return new int[]{r, c};
                }
            }
        }

        throw new IllegalArgumentException("No salamander present");
    }
}
