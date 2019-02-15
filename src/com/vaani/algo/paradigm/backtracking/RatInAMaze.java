package com.vaani.algo.paradigm.backtracking;

/**https://www.geeksforgeeks.org/rat-in-a-maze-backtracking-2/
 */
public class RatInAMaze {

    public int[][] solution;

    //initialize the solution matrix in constructor.
    public RatInAMaze(int N) {
        solution = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                solution[i][j] = 0;
            }
        }
    }

    public void solve(int[][] maze, int N) {
        if (findPath(maze, 0, 0, N)) {
            print(solution, N);
        } else {
            System.out.println("NO PATH FOUND");
        }

    }

    public boolean findPath(int[][] maze, int x, int y, int N) {
        // check if maze[x][y] is feasible to move
        if (x == N - 1 && y == N - 1) {//we have reached
            solution[x][y] = 1;
            return true;
        }
        if (canMove(maze, x, y, N)) {
            // move to maze[x][y]
            solution[x][y] = 1;
            // now rat has four options, either go right OR go down or left or go up
            if (findPath(maze, x + 1, y, N)) { //go down
                return true;
            }
            //else go down
            if (findPath(maze, x, y + 1, N)) { //go right
                return true;
            }
            //if none of the options work out BACKTRACK undo the move
            solution[x][y] = 0;
            return false;
        }
        return false;
    }

    // this function will check if mouse can move to this cell
    public boolean canMove(int[][] maze, int x, int y, int N) {
        // check if x and y are in limits and cell is not blocked
        if (x >= 0 && y >= 0 && x < N && y < N && maze[x][y] != 0) {
            return true;
        }
        return false;
    }

    public void print(int[][] solution, int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(" " + solution[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int N = 4;
        int[][] maze = {{1, 0, 0, 0},
                {1, 1, 0, 0},
                {0, 1, 0, 0},
                {0, 1, 1, 1}};
        RatInAMaze r = new RatInAMaze(N);
        r.solve(maze, N);
    }


}
