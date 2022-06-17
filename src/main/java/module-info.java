module com.example.gflv1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires MathParser.org.mXparser;
    requires java.xml.bind;


    opens com.example.gflv1 to javafx.fxml;
    exports com.example.gflv1;
}