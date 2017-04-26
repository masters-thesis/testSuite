package View;

import ViewModel.MainViewModel;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by KURZRO on 29.03.2017.
 */
public class MainView implements FxmlView<MainViewModel>, Initializable {

    @FXML
    private TextField testContainerUrl;

    @FXML
    private TextField testExecutorUrl;

    @FXML
    private TextField executionCountField;

    @FXML
    private TextField sshUserField;

    @FXML
    private CheckBox persistFlag;

    @FXML
    private ChoiceBox<String> testSelectorList;

    @FXML
    private ChoiceBox<String> platformName;

    @FXML
    private ChoiceBox<String> platformVersion;

    @FXML
    private Button goButton;

    @FXML
    private TextArea resultBox;

    @InjectViewModel
    private MainViewModel mainViewModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        testExecutorUrl.textProperty().bindBidirectional( mainViewModel.testExecutorUrlProperty() );
        testContainerUrl.textProperty().bindBidirectional( mainViewModel.testContainerUrlProperty() );

        executionCountField.textProperty().bindBidirectional( mainViewModel.executionCountProperty() );
        sshUserField.textProperty().bindBidirectional( mainViewModel.sshUserProperty() );

        testSelectorList.itemsProperty().bindBidirectional( mainViewModel.getTestSelector().itemsProperty() );
        platformName.itemsProperty().bindBidirectional( mainViewModel.getPlatformNameSelector().itemsProperty() );
        platformVersion.itemsProperty().bindBidirectional( mainViewModel.getPlatformVersionSelector().itemsProperty() );

        persistFlag.selectedProperty().bindBidirectional( mainViewModel.shouldPersistProperty() );

        resultBox.textProperty().bindBidirectional( mainViewModel.getResultBox().textProperty() );
        goButton.setOnAction( mainViewModel::goButtonAction );

        testSelectorList.getSelectionModel()
                .selectedItemProperty()
                .addListener( mainViewModel::onTestSelectorChanged);

        platformName.getSelectionModel()
                .selectedItemProperty()
                .addListener( mainViewModel::onPlatformNameChanged );

        platformVersion.getSelectionModel()
                .selectedItemProperty()
                .addListener( mainViewModel::onPlatformVersionChanged );

        //taken from http://stackoverflow.com/questions/7555564/what-is-the-recommended-way-to-make-a-numeric-textfield-in-javafx
        executionCountField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    executionCountField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

    }

}
