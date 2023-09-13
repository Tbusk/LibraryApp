package com.libraryapp.app;

import javafx.scene.control.TableCell;
import javafx.scene.text.Text;

public class BookTableCell extends TableCell<Book, String>{

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);

        if (item == null || empty) {
            setText(null);
            setStyle("");
        } else {
            Text text = new Text(item);
            text.setStyle("-fx-text-alignment:justify;");
            text.wrappingWidthProperty().bind(getTableColumn().widthProperty().subtract(2));
            setGraphic(text);
        }
    }
}
