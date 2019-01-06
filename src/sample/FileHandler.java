package sample;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {
    private Scanner scanner;
    private File file;
    ArrayList<String> yPoints = new ArrayList<String>();
    ArrayList<String> xPoints = new ArrayList<String>();
    public FileHandler(File file){
        this.file = file;
    } // end of constructor

    public void readPoints(){
        try{
            scanner = new Scanner(file);
        }
        catch(Exception e){
            System.out.println("not a valid file");
        }

        while(scanner.hasNext()){
            xPoints.add(scanner.next());
            yPoints.add(scanner.next());
        }

    } // end of read method

    public ArrayList<String> setXList() {
        return xPoints;
    }

    public ArrayList<String> setYList() {
        return yPoints;
    }
} //end of class
