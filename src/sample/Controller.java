package sample;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    public Button fileBtn;
    public ListView textView;
    public TextField yInput;
    public TextField xInput;
    public ArrayList<String> xList = new ArrayList<>(); // arraylist because is easier to remove items
    public ArrayList<String> yList = new ArrayList<>();
    public Button clearBtn;
    public boolean fromFile;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void fileAction(ActionEvent actionEvent) {
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null); // open a window to choose file

        if(selectedFile != null){
            textView.getItems().add(selectedFile.getAbsolutePath()); // set text to listView
        }
        else{
            System.out.println("not valid file");
        }

        FileHandler fileHandler = new FileHandler(selectedFile); //to read from file
        fileHandler.readPoints();
        xList = fileHandler.setXList();
        yList = fileHandler.setYList();
        fromFile = true;
    } //end of fileAction

    public void pointAction(ActionEvent actionEvent) {
        String x = xInput.textProperty().get();
        xList.add(x);
        String y = yInput.textProperty().get();
        yList.add(y);
        textView.getItems().add("(" + x + "," + y + ")");
        xInput.textProperty().setValue("");
        yInput.textProperty().setValue("");
    } //end of pointAction

    public void splineAction(ActionEvent actionEvent) {
            Spline spline = new Spline(xList,yList);
            spline.calculate();
} //end of splineAction


    public void clearAction(ActionEvent actionEvent) {
        int index = textView.getSelectionModel().getSelectedIndex();
        textView.getItems().remove(index);
        if(fromFile){
            xList.clear(); // clear whole list
            yList.clear();
            fromFile = false;
        }
        else{
            xList.remove(index); // clear only choosen point
            yList.remove(index);
      }
    } // end of clearAction


} //end of class
