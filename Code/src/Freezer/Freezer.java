/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Freezer;


import Parking.Ontology.Categories.Category;
import Parking.Ontology.Optics.Barcode;
import Parking.Storage.Repository.MySQL.MySQLRepository;
import Parking.Ontology.Products.Product;
import java.net.URL;
import java.util.ArrayList;
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
                        
        if (Parking.Base.Storage.GetOperator().Load()) 
        {
            Category testCategory = Parking.Base.Storage.GetOperator().Get("test", Category.class);
            
            if (testCategory == null)
            {
                testCategory = new Category();
                testCategory.ID = UUID.randomUUID().toString();
                testCategory.Reference = "test";
                testCategory.Name = "Test Category";
                
                Parking.Base.Storage.GetOperator().Save(testCategory);
            }
            
            Product testProduct = Parking.Base.Storage.GetOperator().Get("test", Product.class);
                                    
            if (testProduct == null)
            {
                testProduct = new Product();
                testProduct.ID = UUID.randomUUID().toString();
                testProduct.Reference = "test";
                testProduct.Amount = 5;
                testProduct.Description = "Test";
                testProduct.Name = "Test";
                testProduct.Category = testCategory;
                
                Parking.Base.Storage.GetOperator().Save(testProduct);
            }
            
            if (testProduct.Codes == null)
            {
                testProduct.Codes = new ArrayList<>();
            }
            
            if (testProduct.Codes.isEmpty())
            {
                Barcode testBarcode = Parking.Base.Storage.GetOperator().Get("test", Barcode.class);
                
                if (testBarcode == null)
                {
                    testBarcode = new Barcode();

                    testBarcode.ID = UUID.randomUUID().toString();
                    testBarcode.Reference = "test";
                    testBarcode.Code = "test";

                    Parking.Base.Storage.GetOperator().Save(testBarcode);
                }
                
                testProduct.Codes.add(testBarcode);
                Parking.Base.Storage.GetOperator().Save(testProduct);
            }
            
            launch(args);
        }
    }
}
