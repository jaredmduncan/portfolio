/**************************
    Author: Jared Duncan
    Class: CIT249 Java II
    Teacher: [redacted]
    Description: This program reads and writes to and from CSV files while also having an interactive system
    to create, delete, and display data. This program satisfies module 10 for CIT249.
 **************************/

/**************************
    Imports required for this class.
 **************************/
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import java.io.*;
import java.util.ArrayList;


public class volunteersPane extends GridPane {
    /**************************
        Define our buttons, textfields, and other data to be accessed from multiple functions.
     **************************/
    private Button exitButton;
    private Button loadFileButton;
    private Button saveFileButton;

    private Button displayButton;
    private Button clearButton;
    private ArrayList<Label> displayLabelArray;

    private Button addButton;
    private TextField addTextFieldFirstName;
    private TextField addTextFieldLastName;
    private TextField addTextFieldEmail;

    private Button deleteButton;
    private TextField deleteTextField;

    private ArrayList<volunteersObject> volunteers;

    public volunteersPane() {
        /**************************
            Initialize our array that contains our labels which show the csv data.
            This is how we keep track of what is on screen so we can clear it later and update with new data.
         **************************/
        displayLabelArray = new ArrayList<>();

        /**************************
            Load the volunteers file when the program starts.
         **************************/
        readVolunteers();

        /**************************
            Formatting stuff to make it look nice.
         **************************/
        setAlignment(Pos.TOP_CENTER);
        setHgap(10);
        setVgap(10);
        setStyle("-fx-padding:1em;");

        /**************************
            Add top three buttons: Exit, load File, Save File.
         **************************/
        exitButton = new Button("Exit");
        GridPane.setHalignment(exitButton, HPos.CENTER);
        exitButton.setOnAction(this::buttonHandler);
        add(exitButton, 0, 0, 1, 1);

        loadFileButton = new Button("Load File");
        GridPane.setHalignment(loadFileButton, HPos.CENTER);
        loadFileButton.setOnAction(this::buttonHandler);
        add(loadFileButton, 1, 0, 1, 1);

        saveFileButton = new Button("Save File");
        GridPane.setHalignment(saveFileButton, HPos.CENTER);
        saveFileButton.setOnAction(this::buttonHandler);
        add(saveFileButton, 2, 0, 1, 1);

        /**************************
            Add the delete index section.
         **************************/
        Label deleteLabel = new Label("Delete an index");
        GridPane.setHalignment(deleteLabel, HPos.CENTER);
        add(deleteLabel, 0, 2, 1, 1);

        deleteTextField = new TextField("0");
        GridPane.setHalignment(deleteTextField, HPos.CENTER);
        add(deleteTextField, 1, 2, 1, 1);

        deleteButton = new Button("Delete");
        GridPane.setHalignment(deleteButton, HPos.CENTER);
        deleteButton.setOnAction(this::buttonHandler);
        add(deleteButton, 2, 2, 1, 1);

        /**************************
            Add the data entry section.
         **************************/
        Label addLabel = new Label("Add using last name, first name, and email");
        GridPane.setHalignment(addLabel, HPos.CENTER);
        add(addLabel, 0, 4, 2, 1);

        addButton = new Button("Add");
        GridPane.setHalignment(addButton, HPos.CENTER);
        addButton.setOnAction(this::buttonHandler);
        add(addButton, 2, 4, 1, 1);

        addTextFieldFirstName = new TextField("John");
        GridPane.setHalignment(addTextFieldFirstName, HPos.CENTER);
        add(addTextFieldFirstName, 1, 5, 1, 1);

        addTextFieldLastName = new TextField("Doe");
        GridPane.setHalignment(addTextFieldLastName, HPos.CENTER);
        add(addTextFieldLastName, 0, 5, 1, 1);

        addTextFieldEmail = new TextField("john.doe@example.com");
        GridPane.setHalignment(addTextFieldEmail, HPos.CENTER);
        add(addTextFieldEmail, 2, 5, 1, 1);

        /**************************
            Add the data displaying section.
         **************************/
        displayButton = new Button("Display");
        GridPane.setHalignment(displayButton, HPos.CENTER);
        displayButton.setOnAction(this::buttonHandler);
        add(displayButton, 1, 7, 1, 1);

        clearButton = new Button("Clear");
        GridPane.setHalignment(clearButton, HPos.CENTER);
        clearButton.setOnAction(this::buttonHandler);
        add(clearButton, 2, 7, 1, 1);

        Label displayLabel = new Label("Display Area:");
        GridPane.setHalignment(displayLabel, HPos.CENTER);
        add(displayLabel, 0, 7, 1, 1);

        /**************************
            These labels are basically headers for the table that gets displayed.
         **************************/
        Label displayFirstNameLabel = new Label("FIRST NAME");
        GridPane.setHalignment(displayFirstNameLabel, HPos.CENTER);
        add(displayFirstNameLabel, 1, 8, 1, 1);

        Label displayLastNameLabel = new Label("LAST NAME");
        GridPane.setHalignment(displayLastNameLabel, HPos.CENTER);
        add(displayLastNameLabel, 0, 8, 1, 1);

        Label displayEmailLabel = new Label("EMAIL");
        GridPane.setHalignment(displayEmailLabel, HPos.CENTER);
        add(displayEmailLabel, 2, 8, 1, 1);

        /**************************
            Go ahead and display our data.
         **************************/
        clearDisplay();
        fillDisplay();
    }

    public void buttonHandler(ActionEvent event) {
        /**************************
            Determine what button was pressed, and what to do accordingly.
         **************************/
        Object button = event.getSource();
        if (button == exitButton) {
            Platform.exit();
            System.exit(0);
            return;
        } else if (button == loadFileButton) {
            readVolunteers();
            clearDisplay();
            fillDisplay();
        } else if (button == saveFileButton) {
            writeVolunteers();
        } else if (button == displayButton) {
            clearDisplay();
            fillDisplay();
        } else if (button == clearButton) {
            clearDisplay();
        } else if (button == addButton) {
            /**************************
                We check if empty and return because there is no point in adding this data.
             **************************/
            if (addTextFieldFirstName.getText().equals("")) {
                return;
            }
            if (addTextFieldLastName.getText().equals("")) {
                return;
            }
            if (addTextFieldEmail.getText().equals("")) {
                return;
            }
            String firstName = addTextFieldFirstName.getText();
            String lastName = addTextFieldLastName.getText();
            String email = addTextFieldEmail.getText();
            volunteers.add(new volunteersObject(lastName, firstName, email));
            clearDisplay();
            fillDisplay();
        } else if (button == deleteButton) {
            if (deleteTextField.getText().equals("")) {
                return;
            }
            int index = Integer.parseInt(deleteTextField.getText());
            volunteers.remove(index);
            clearDisplay();
            fillDisplay();
        }
    }

    public void clearDisplay() {
        /**************************
            Loop through our list of displayed labels and remove them all!
         **************************/
        while(displayLabelArray.size() > 0) {
            displayLabelArray.get(0).setText("");
            displayLabelArray.remove(0);
        }
    }

    public void fillDisplay() {
        /**************************
            Loop through our list of volunteers, add the labels as needed, store them and display them!
         **************************/
        int startingRow = 9;
        for (int y = 0; y < volunteers.size(); y++) {
            String firstName = volunteers.get(y).getFirstName();
            String lastName = volunteers.get(y).getLastName();
            String email = volunteers.get(y).getEmail();
            Label firstNameLabel = new Label(firstName);
            Label lastNameLabel = new Label(lastName);
            Label emailLabel = new Label(email);
            GridPane.setHalignment(firstNameLabel, HPos.LEFT);
            GridPane.setHalignment(lastNameLabel, HPos.LEFT);
            GridPane.setHalignment(emailLabel, HPos.LEFT);
            add(lastNameLabel, 0, startingRow + y, 1, 1);
            add(firstNameLabel, 1, startingRow + y, 1, 1);
            add(emailLabel, 2, startingRow + y, 1, 1);
            displayLabelArray.add(firstNameLabel);
            displayLabelArray.add(lastNameLabel);
            displayLabelArray.add(emailLabel);
        }
    }

    static public ArrayList< ArrayList<String> > readFile(String fileName) {
        /**************************
            Load our specified CSV file and return a 2D arraylist of strings (row,column).
         **************************/
        ArrayList< ArrayList<String> > fileData = new ArrayList< ArrayList<String> >();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            final int MAX_COUNT = 1000;
            int count = 0;
            while (count < MAX_COUNT) {
                ArrayList<String> lineData = new ArrayList<>();
                try {
                    String rawLineData = reader.readLine();
                    if (rawLineData != null) {
                        String[] lines = rawLineData.split(",");
                        for (int i = 0; i < lines.length; i++) {
                            lineData.add(lines[i]);
                        }
                        fileData.add(lineData);
                    } else {
                        break;
                    }
                    count++;
                } catch (IOException ignored) {
                }
            }
        } catch (FileNotFoundException ignored) {}
        return fileData;
    }
    static public void writeFile(String fileName, ArrayList< ArrayList<String> > fileData) {
        /**************************
            Take a 2D array of strings and save it as a CSV file (row,column) / (y,x).
         **************************/
        String data = "";
        for (int y = 0; y < fileData.size(); y++) {
            for (int x = 0; x < fileData.get(y).size(); x++) {
                data += fileData.get(y).get(x);
                if (x != fileData.get(y).size()-1) {
                    data += ",";
                } else {
                    if (y != fileData.size()-1) {
                        data += "\n";
                    }
                }
            }
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            /**************************
                If you want to see an output of what data is being written to second file, add this line in:

            System.out.print("Attempting to write:\n" + data + "\n\tTo file: " + fileName);
            **************************/
            writer.write(data);
            writer.close();
        } catch (IOException ignored) {}
    }
    static public ArrayList<volunteersObject> getVolunteersObjectsFromFileData(ArrayList< ArrayList<String> > fileData) {
        /**************************
            Convert a 2D string array of volunteers to an array of volunteer objects for displaying.
         **************************/
        ArrayList<volunteersObject> _volunteers = new ArrayList<>();
        for (int y = 0; y < fileData.size(); y++) {
            String firstName = fileData.get(y).get(1);
            String lastName = fileData.get(y).get(0);
            String email = fileData.get(y).get(2);
            _volunteers.add(new volunteersObject(lastName, firstName, email));
        }
        return _volunteers;
    }
    static public ArrayList< ArrayList<String> > getFileDataFromVolunteersObjects(ArrayList<volunteersObject> volunteers) {
        /**************************
            Convert an array of volunteer objects to a 2D string array of volunteers for saving in files.
         **************************/
        ArrayList< ArrayList<String> > fileData = new ArrayList< ArrayList<String> >();
        for (int y = 0; y < volunteers.size(); y++) {
            String firstName = volunteers.get(y).getFirstName();
            String lastName = volunteers.get(y).getLastName();
            String email = volunteers.get(y).getEmail();
            ArrayList<String> recordData = new ArrayList<String>();
            recordData.add(lastName);
            recordData.add(firstName);
            recordData.add(email);
            fileData.add(recordData);
        }
        return fileData;
    }
    public void readVolunteers() {
        /**************************
            Utility function which simply uses our converting and file functions together to fetch data.
         **************************/
        this.volunteers = getVolunteersObjectsFromFileData(readFile("Volunteers.csv"));
    }

    public void writeVolunteers() {
        /**************************
            Utility function which simply uses our converting and file functions together to save data.
         **************************/
        writeFile("Volunteers2.csv", getFileDataFromVolunteersObjects(this.volunteers));
    }
}
