/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Freezer;


import Parking.Storage.Repository.MySQL.MySQLRepository;
import Products.Product;
import java.net.URL;
import java.util.UUID;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author noldi
 */
public class Freezer extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        URL fxmlURL = getClass().getResource("Views/MainView.fxml");
        
        Parent root = FXMLLoader.load(fxmlURL);
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Parking.Base.Storage.GetManager().Add(new MySQLRepository("192.168.1.99", "Freezer", "Freeza", "GetsKilledByTrunks"));
                        
        if (Parking.Base.Storage.GetOperator().Load()) {
            
            Product testProduct = new Product();
            testProduct.ID = UUID.randomUUID().toString();
            testProduct.Reference = "test";
            testProduct.Amount = 5;
            testProduct.Description = "Some Description";
            testProduct.Name = "Test";
            
            Parking.Base.Storage.GetOperator().Save(testProduct);
            
            Product product = Parking.Base.Storage.GetOperator().Get("test", Product.class);
            
            launch(args);
        }
    }
}
