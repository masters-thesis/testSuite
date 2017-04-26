package ViewModel;

import RestClient.RestClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import database.Database;
import de.saxsys.mvvmfx.ViewModel;
import factory.TableFactory;
import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import util.Result;

/**
 * Created by KURZRO on 29.03.2017.
 */
public class MainViewModel implements ViewModel {

    private StringProperty testExecutorUrl = new SimpleStringProperty();
    private StringProperty testContainerUrl = new SimpleStringProperty();

    private StringProperty executionCount = new SimpleStringProperty();
    private StringProperty sshUser = new SimpleStringProperty();

    private ObservableList<String> testSelectorList = FXCollections.observableArrayList();
    private ObservableList<String> platformNameList = FXCollections.observableArrayList();
    private ObservableList<String> platformVersionList = FXCollections.observableArrayList();
    private ChoiceBox<String> testSelector = new ChoiceBox<>();
    private ChoiceBox<String> platformName = new ChoiceBox<>();
    private ChoiceBox<String> platformVersion = new ChoiceBox<>();
    private TextArea resultBox = new TextArea();

    private BooleanProperty shouldPersist = new SimpleBooleanProperty();

    private String selectedTest = "Load Balancing";
    private String selectedPlatformName = "";
    private String selectedPlatformVersion = "";

    public MainViewModel() {
        testExecutorUrl.setValue( "http://localhost:8080" );
        testSelectorList.add("Load Balancing");
        testSelectorList.add("HTTP Endpoint");
        testSelectorList.add("Process Failure");
        testSelectorList.add("Node Failure");
        testSelectorList.add("Autoscaling");
        testSelector.itemsProperty().set( testSelectorList  );

        platformNameList.add("Kubernetes");
        platformNameList.add("Kontena");
        platformNameList.add("Nomad");
        platformNameList.add("Docker Swarm");
        platformNameList.add("Rancher");
        platformNameList.add("Mesos/Marathon");
        platformNameList.add("CF-Diego");
        platformNameList.add("Flynn");
        platformName.itemsProperty().set( platformNameList );

        platformVersion.itemsProperty().set( platformVersionList );

        shouldPersist.setValue(true);

        executionCount.setValue( "1" );
        sshUser.setValue( "root" );
    }

    public void goButtonAction(ActionEvent actionEvent){
        int counter = 0;
        while ( counter < Integer.parseInt(executionCount.getValue()) )
        {
            System.out.println(
                    "Executing with " +
                            " Container URL " + testContainerUrl +
                            " Executor URL " + testExecutorUrl +
                            " Test: " + selectedTest
            );
            RestClient restClient = new RestClient();

            Result result;
            if( selectedTest.equals("Node Failure") ){
                result = restClient.executeTest(testExecutorUrl.getValue(), testContainerUrl.getValue(), selectedTest, "user="+sshUser.getValue());
            }
            else{
                result = restClient.executeTest(testExecutorUrl.getValue(), testContainerUrl.getValue(), selectedTest);
            }
            System.out.println(result);

            if( isShouldPersist() )
            {
                persist(result);
            }

            ObjectMapper objectMapper = new ObjectMapper();
            try {

                resultBox.textProperty().set(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(result ) );
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }


            counter++;
        }

    }

    public void onTestSelectorChanged(ObservableValue<? extends String> observable, String oldValue, String newValue){
        selectedTest = newValue;
    }

    public void onPlatformNameChanged(ObservableValue<? extends String> observable, String oldValue, String newValue){
        selectedPlatformName = newValue;
        platformVersionList.clear();
        switch(newValue){
            case "Kubernetes":
                platformVersionList.add("1.6.2");
                break;
            case "Kontena":
                platformVersionList.add("1.1.6");
                break;
            case "Nomad":
                platformVersionList.add("0.5.6");
                break;
            case "Docker Swarm":
                platformVersionList.add("1.2.6");
                break;
            case "Rancher":
                platformVersionList.add("1.5.5");
                break;
            case "Mesos/Marathon":
                platformVersionList.add("1.2.0/1.4.1");
                break;
            case "Flynn":
                platformVersionList.add("20170321.0");
                break;
            case "CF-Diego":
                platformVersionList.add("0.1463.0");
                break;
            default:
                platformVersionList.add("N/A");
        }

    }

    public void onPlatformVersionChanged(ObservableValue<? extends String> observable, String oldValue, String newValue){
        selectedPlatformVersion = newValue;
    }

    public String getTestExecutorUrl() {
        return testExecutorUrl.get();
    }

    public StringProperty testExecutorUrlProperty() {
        return testExecutorUrl;
    }

    public void setTestExecutorUrl(String testExecutorUrl) {
        this.testExecutorUrl.set(testExecutorUrl);
    }

    public String getTestContainerUrl() {
        return testContainerUrl.get();
    }

    public StringProperty testContainerUrlProperty() {
        return testContainerUrl;
    }

    public void setTestContainerUrl(String testContainerUrl) {
        this.testContainerUrl.set(testContainerUrl);
    }

    public ObservableList<String> getTestSelectorList() {
        return testSelectorList;
    }

    public ChoiceBox<String> getTestSelector() {
        return testSelector;
    }

    public ChoiceBox<String> getPlatformNameSelector(){
        return platformName;
    }

    public ChoiceBox<String> getPlatformVersionSelector(){
        return platformVersion;
    }

    public void setTestSelector(ChoiceBox<String> testSelector) {
        this.testSelector = testSelector;
    }

    public void setTestSelectorList(ObservableList<String> testSelectorList) {
        this.testSelectorList = testSelectorList;
    }

    public TextArea getResultBox() {
        return resultBox;
    }

    public void setResultBox(TextArea resultBox) {
        this.resultBox = resultBox;
    }

    public String getExecutionCount() {
        return executionCount.get();
    }

    public StringProperty executionCountProperty() {
        return executionCount;
    }

    public String getSshUser() {
        return sshUser.get();
    }

    public StringProperty sshUserProperty() {
        return sshUser;
    }

    public boolean isShouldPersist() {
        return shouldPersist.get();
    }

    public BooleanProperty shouldPersistProperty() {
        return shouldPersist;
    }

    private void persist(Result result){

        TableFactory factory = new TableFactory();

        try{
            Object table = factory.getPreparedTable(
                                selectedTest,
                                result.getContainerInformation().size(),
                                selectedPlatformName,
                                selectedPlatformVersion,
                                result);

            Database database = new Database();
            database.save(table);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
