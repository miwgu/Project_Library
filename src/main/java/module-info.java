module Bibliotek {

    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;

    opens Client;
    opens Client.Controller;
    opens Client.Controller.Employee;
    opens Client.Controller.Visitor;
    opens view;
    opens view.employee;
    opens view.visitor;
    opens img;
}