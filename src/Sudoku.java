import javax.swing.*;

public class Sudoku{
    public static void main(String[] args) {
        Myframe myf=new Myframe();
        myf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Name
        myf.setTitle("sudoku");

        //Size
        myf.setSize(500,500);

        //Set visibility
        myf.setVisible(true);
    }
}