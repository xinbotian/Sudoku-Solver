import java.util.ArrayList;
import java.util.TreeSet;

class Calculate implements Runnable {
    // boo ues to determine the slot is empty
    public static boolean[][] boo = new boolean[9][9];

    //计算指定行的值
    public static int upRow = 0;

    //计算指定列值
    public static int upColumn = 0;

    //将存储九宫格中的数据
    public static int[][] b = new int[9][9];

    //查找没有填入数值的空格
    public static void flyBack(boolean[][] judge,int row,int column){}

    //遍历所有可能的值
    public static void arrayAdd(ArrayList<Integer> array, TreeSet<Integer> tree){}
    public static ArrayList<Integer> assume(int row,int column){}

    //添加每格可能的选项
    public void run(){}

    //分析九宫格是否完成
    public void judge(){}
}