module com.todolist {
    requires javafx.controls;
    requires javafx.fxml;
    requires mail;
    
    opens com.todolist to javafx.fxml;
    opens MainCode to javafx.base;
    exports com.todolist;
}
