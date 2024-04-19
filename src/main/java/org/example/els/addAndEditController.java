package org.example.els;

import Bookmark.bookmarkmanagement;
import dictionary.DatabaseConnection;
import dictionary.DictionaryManagement;
import dictionary.DictionaryManagementDatabase;
import dictionary.Word;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.web.HTMLEditor;

import java.io.IOException;
import java.sql.*;

public class addAndEditController extends baseFormController {
    @FXML
    private TextField e_Textfield;
    @FXML
    private HTMLEditor v_Textfield;
    @FXML
    private Label label;


    public void addWord(ActionEvent event) {
       bookmarkmanagement.addWord(e_Textfield.getText(),v_Textfield.getHtmlText(),user);
    }
}
