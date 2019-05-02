package com.zepp.spiskovi.controller;

import com.zepp.spiskovi.Common.PosiljkaStats;
import com.zepp.spiskovi.model.Posiljka;
import com.zepp.spiskovi.service.PosiljkaService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ControllerStart {

    private final PosiljkaService posiljkaService;
    File file;

    @Autowired
    public ControllerStart(PosiljkaService posiljkaService) {
        this.posiljkaService = posiljkaService;
    }

    @FXML
    private Button startRead;

    @FXML
    private Label labelOpenFile;

    @FXML
    private BorderPane borderPane;

    @FXML
    private Button openFile;

    Charset win1250 = Charset.forName("Windows-1250");

    @FXML
    public void openFileHandle(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Izaberi fajl");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("csv", "*.csv"),
                new FileChooser.ExtensionFilter("All files", "*.*")
        );
        file = fileChooser.showOpenDialog(null);
        if(file != null){
            labelOpenFile.setText(file.getAbsolutePath());
        }else{
            labelOpenFile.setText("Nije izabran fajl!!!");
        }
    }

    @FXML
    public void exit(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Izađi iz aplikacije");
        alert.setHeaderText("Da li želite da izađete iz aplikacije?");
        alert.setContentText("Izaberite opciju.");

        ButtonType buttonTypeOne = new ButtonType("Izađi");
        ButtonType buttonTypeTwo = new ButtonType("Odustani");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne){
            // ... user chose "One"
            System.exit(0);
        } else if (result.get() == buttonTypeTwo) {
            return;
        } else {
            return;
        }
    }

    @FXML
    public void startReadCsv(){
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ";";

        try {

            br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] posiljkaRow = line.split(cvsSplitBy);
                int postanskiBroj = Integer.parseInt(posiljkaRow[0]);
                int rejon =Integer.parseInt(posiljkaRow[1]);
                Posiljka posiljka = new Posiljka(postanskiBroj, rejon, 1);
                posiljkaService.savePosiljka(posiljka);
            }
            List<Object[]> posiljkaList = new ArrayList<>();
            posiljkaList = posiljkaService.spiskoviFinal();
            File otputFile = new File(file.getParent() + "\\" + file.getName() + "_zaPostu.csv");
            //FileWriter writer = new FileWriter(otputFile);
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(otputFile), "Windows-1250");
            writer.write("poštanski broj;rejon;količina\n");
            for(Object[] item: posiljkaList){
                writer.write(item[0] + ";" + item[1] + ";" + item[2] + "\n");
            }
            writer.flush();
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


