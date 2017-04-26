package main;

import View.MainView;
import ViewModel.MainViewModel;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

/**
 * Created by KURZRO on 29.03.2017.
 */
@Controller
public class Starter extends Application {

    public static void main(String...args){
        Application.launch(args);
    }

    @Override
    public void start(Stage stage){
        stage.setTitle("Test Suite");

        ViewTuple<MainView, MainViewModel> viewTuple = FluentViewLoader.fxmlView(MainView.class).load();

        Parent root = viewTuple.getView();
        stage.setScene(new Scene(root));

        stage.show();
    }

}
