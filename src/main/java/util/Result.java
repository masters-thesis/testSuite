package util;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import domain.Container;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by KURZRO on 29.03.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {

    private Status status;
    private String message;
    private long testDurationInMilliseconds;
    private long waitForStateDurationInMilliseconds;
    private long numberOfContainers;
    private List<Container> containerInformation;
    private long numberOfSuccessfulRequests;
    private long numberOfFailedRequests;
    private List<String> logs;

    private final String newline = System.getProperty("line.separator");

    public Result(){}

    public Result(
            Status status,
            String message,
            long testDurationInMilliseconds,
            long waitForStateDurationInMilliseconds,
            long numberOfContainers,
            List<Container> containerInformation,
            long numberOfTotalRequests,
            long numberOfFailedRequests,
            List<String> logs) {
        this.status = status;
        this.message = message;
        this.testDurationInMilliseconds = testDurationInMilliseconds;
        this.waitForStateDurationInMilliseconds = waitForStateDurationInMilliseconds;
        this.numberOfContainers = numberOfContainers;
        this.containerInformation = containerInformation;
        this.numberOfSuccessfulRequests = numberOfTotalRequests;
        this.numberOfFailedRequests = numberOfFailedRequests;
        this.logs = logs;
    }

    public Result(long duration, Result previousResult) {
        this(
                previousResult.status,
                previousResult.message,
                duration,
                previousResult.waitForStateDurationInMilliseconds,
                previousResult.numberOfContainers,
                previousResult.containerInformation,
                previousResult.numberOfSuccessfulRequests,
                previousResult.numberOfFailedRequests,
                previousResult.logs
        );
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTestDurationInMilliseconds() {
        return testDurationInMilliseconds;
    }

    public void setTestDurationInMilliseconds(long testDurationInMilliseconds) {
        this.testDurationInMilliseconds = testDurationInMilliseconds;
    }

    public long getWaitForStateDurationInMilliseconds() {
        return waitForStateDurationInMilliseconds;
    }

    public void setWaitForStateDurationInMilliseconds(long waitForStateDurationInMilliseconds) {
        this.waitForStateDurationInMilliseconds = waitForStateDurationInMilliseconds;
    }

    public long getNumberOfContainers() {
        return numberOfContainers;
    }

    public void setNumberOfContainers(long numberOfContainers) {
        this.numberOfContainers = numberOfContainers;
    }

    public List<Container> getContainerInformation() {
        return containerInformation;
    }

    public void setContainerInformation(List<Container> containerInformation) {
        this.containerInformation = containerInformation;
    }

    public long getNumberOfSuccessfulRequests() {
        return numberOfSuccessfulRequests;
    }

    public void setNumberOfSuccessfulRequests(long numberOfSuccessfulRequests) {
        this.numberOfSuccessfulRequests = numberOfSuccessfulRequests;
    }

    public long getNumberOfFailedRequests() {
        return numberOfFailedRequests;
    }

    public void setNumberOfFailedRequests(long numberOfFailedRequests) {
        this.numberOfFailedRequests = numberOfFailedRequests;
    }

    public List<String> getLogs() {
        return logs;
    }

    public void setLogs(List<String> logs) {
        this.logs = logs;
    }

    @Override
    public String toString() {
        return "Result{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", testDurationInMilliseconds=" + testDurationInMilliseconds +
                ", waitForStateDurationInMilliseconds=" + waitForStateDurationInMilliseconds +
                ", numberOfContainers=" + numberOfContainers +
                ", containerInformation='" + containerInformation + '\'' +
                ", numberOfSuccessfulRequests=" + numberOfSuccessfulRequests +
                ", numberOfFailedRequests=" + numberOfFailedRequests +
                ", logs=" + logs +
                '}';
    }
}

