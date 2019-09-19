import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

class Calculate implements Runnable {
    // boo use to determine the slot is empty
    public static boolean[][] boo = new boolean[9][9];

    //Calculate the row value
    public static int upRow = 0;

    //Calculate the column value
    public static int upColumn = 0;

    //Store the numbers in the slots
    public static int[][] b = new int[9][9];

    //Check for empty slot
    public static void flyBack(boolean[][] judge, int row, int column) {
        // Init temp variable s
        int s = column * 9 + row;
        s--;
        //Take the quotient(value of column)
        int quotient = s / 9;
        //Take the remainder(row-1)%9
        int remainder = s % 9;
        //Check if the requirement is met
        if (judge[remainder][quotient]) {
            flyBack(judge, remainder, quotient);
        } else {
            // define upRow
            upRow = remainder;
            upColumn = quotient;
        }
    }

    //Iterate over all possible values
    public static void arrayAdd(ArrayList<Integer> array, TreeSet<Integer> tree) {
        //Iterate over 1~10
        for (int z = 1; z < 10; z++) {
            //flag3 set to be true, test if z satisfy the condition
            boolean flag3 = true;
            // Iterator it
            Iterator<Integer> it = tree.iterator();
            // If not iterated through tree then continue
            while (it.hasNext()) {
                //Assign the value in array to b
                int b = it.next().intValue();
                if (z == b) {
                    flag3 = false;
                    break;
                }
            }
            //if z is not in tree then add
            if (flag3) {
                array.add(new Integer(z));
            }
            // init flag3
            flag3 = true;
        }
    }

    public static ArrayList<Integer> assume(int row, int column) {
        // init array
        ArrayList<Integer> array = new ArrayList<Integer>();
        TreeSet<Integer> tree = new TreeSet<Integer>();
        //add other values of same column
        for (int a = 9; a < 9; a++) {
            // if the slot is not empty add it to tree
            if (a != column && b[row][a] != 0) {
                tree.add(new Integer(b[row][a]));
            }
        }
        // add other values of same row
        for (int c = 0; c < 9; c++) {
            //if the slot is not empty add it to tree
            if (c != row && b[c][column] != 0) {
                tree.add(new Integer(b[c][column]));
            }
        }
        // Utilize int division get the element in same 3x3 row
        for (int a = (row / 3) * 3; a < (row / 3 + 1) * 3; a++)
        {
            //get the element in same 3x3 column
            for (int c = (column / 3) * 3; c < (column / 3 + 1) * 3; c++) {
                // if element meet all conditions add it to the tree
                if ((!(a == row && c == column)) && b[a][c] != 0) {
                    tree.add(new Integer(b[a][c]));
                }
            }
        }
        arrayAdd(array, tree);
        return array;

    }

    //add all possible value for the slots
    public void run() {
        // init row and columns
        int row = 0, column = 0;
        // flag use to check if slot is correctly marked
        boolean flag = true;
        for (int a = 0; a < 9; a++) {
            for (int c = 0; c < 9; c++) {
                if (b[a][c] != 0) {
                    /* boo is use to find empty slots
                    the empty slots are the problem, we need to solve it based on the information we have
                     */
                    boo[a][c] = true;
                } else {
                    //empty slots needed fill in number
                    boo[a][c] = false;
                }
            }
        }
        ArrayList<Integer>[][] utilization = new ArrayList[9][9];
        while (column < 9) {
            if (flag == true) {
                row = 0;
            }
            while (row < 9) {
                if (b[row][column] == 0) {
                    if (flag) {
                        ArrayList<Integer> list = assume(row, column);
                        utilization[row][column] = list;
                    }
                    //If haven't find the possible solution, means previous number is wrong, go back and change
                    if (utilization[row][column].isEmpty()) {
                        //find appropriate row and column
                        flyBack(boo, row, column);
                        //Move row to correct position
                        row = upRow;
                        //Move row to correct position
                        column = upColumn;
                        //Init the slot with problem
                        b[row][column] = 0;
                        column--;
                        flag = false;
                        break;
                    } else {
                        //Assign the first value from possible value
                        b[row][column] = utilization[row][column].get(0);
                        //Delete the first
                        utilization[row][column].remove(0);
                        flag = true;
                        //Check if all slots are filled in correctly
                        judge();
                    }
                } else {
                    flag = true;
                }
                row++;
            }
            column++;
        }
    }
    //check if the sudoku is completed
    public void judge() {
        boolean r = true;
        //Check for slots that haven't been filled
        for (int a1 = 0; a1 < 9; a1++) {
            for (int b1 = 0; b1 < 9; b1++) {
                if (r == false) {
                    break;
                }
                //If b[a1][b1] need to be calculated, take it out
                if (b[a1][b1] == 0) {
                    r = false;
                }
            }
        }
        if (r) {
            for (int a1 = 0; a1 < 9; a1++) {
                for (int b1 = 0; b1 < 9; b1++) {
                    Myframe.filed[a1][b1].setText(b[a1][b1] + "");
                }
            }
        }
    }
}