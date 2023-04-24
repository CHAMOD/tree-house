import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        int gridSize = 5;
        Integer[][] treeGrid = new Integer[gridSize][gridSize];
        List<Integer> integerList = new ArrayList<>(Arrays.asList(3, 0, 3, 7, 3, 2, 5, 5, 1, 2, 6, 5, 3, 3, 2, 3, 3, 5, 4, 9, 3, 5, 3, 9, 0));

//    final List<Integer> integerList = new ArrayList<>();
//    final Random rand = new Random();
//
//    for (int i = 0; i < gridSize * gridSize; i++) {
//      integerList.add(rand.nextInt((9 - 1) + 1) + 1);
//    }


//    preparing the grid
        int counter = 0;
        for (int i = 0; i < gridSize; i++) {

            for (int j = 0; j < gridSize; j++) {
                treeGrid[i][j] = integerList.get(counter);
                counter++;
            }
        }

//    calculation start
        int visibleTreeCount = 0;
        final StringBuilder stringBuilder = new StringBuilder();

        for (int y = 0; y < gridSize; y++) {

            for (int x = 0; x < gridSize; x++) {

//        excluding trees in the edges
                if (y != 0 && y != gridSize - 1 && x != 0 && x != gridSize - 1) {

                    boolean isVisible;

                    int horizontalTrees[] = getTheLineByDirection(y, treeGrid, "H");
                    int verticalTrees[] = getTheLineByDirection(x, treeGrid, "V");

                    isVisible = isVisibleTree(horizontalTrees, verticalTrees, y, x);
                    if (isVisible) {
                        visibleTreeCount++;
                        stringBuilder.append("(" + y + "," + x + ")-" + treeGrid[y][x] + "\n");
                    }
                }
            }


        }
        System.out.println("");
        for (int y = 0; y < gridSize; y++) {
            for (int x = 0; x < gridSize; x++) {
                System.out.print(treeGrid[y][x] + " ");
            }
            System.out.println("");

        }

        System.out.println("");
        System.out.println("-Coordinates-");
        System.out.println(stringBuilder);
        System.out.println("");
        int outsideTrees = gridSize * 2 + (gridSize - 2) * 2;
        System.out.println("Outside Trees:" + outsideTrees);
        System.out.println("Inside Trees:" + visibleTreeCount);


    }


    static int[] getTheLineByDirection(int position, Integer[][] treeGrid, String direction) {

        int line[] = new int[treeGrid.length];
        switch (direction) {
            case "H": {
                for (int x = 0; x < treeGrid.length; x++) {
                    line[x] = treeGrid[position][x];
                }
                break;
            }

            case "V": {
                for (int y = 0; y < treeGrid.length; y++) {
                    line[y] = treeGrid[y][position];
                }
                break;
            }
        }

        return line;
    }

    //
    static boolean isVisibleTree(int[] horizontalLineOfTrees, int[] verticalLineOfTrees, int y, int x) {

        int tree = horizontalLineOfTrees[x];
        boolean isVisible = true;

        // check left
        for (int a = 0; a < horizontalLineOfTrees.length; a++) {
            if (tree <= horizontalLineOfTrees[a] && a != x) {
                isVisible = false;
                break;
            } else {
                if (a == x) {
                    return true;
                }

            }
        }

        //check top
        for (int a = 0; a < verticalLineOfTrees.length; a++) {
            if (tree <= verticalLineOfTrees[a] && a != y) {
                isVisible = false;
                break;
            } else {
                if (a == y) {
                    return true;
                }
            }
        }

        //check right
        for (int a = horizontalLineOfTrees.length - 1; a >= 0; a--) {
            if (tree <= horizontalLineOfTrees[a] && a != x) {
                isVisible = false;
                break;
            } else {
                if (a == x) {
                    return true;
                }
            }
        }

        //check bottom
        for (int a = verticalLineOfTrees.length - 1; a >= 0; a--) {
            if (tree <= verticalLineOfTrees[a] && a != y) {
                isVisible = false;
                break;
            } else {
                if (a == y) {
                    return true;
                }
            }
        }

        return isVisible;
    }

}
