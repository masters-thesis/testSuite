package RestClient;

import org.springframework.web.client.RestTemplate;
import util.Result;

/**
 * Created by KURZRO on 07.04.2017.
 */
public class RestClient {

    private final String serviceIp = "?serviceIp=";
    RestTemplate restTemplate = new RestTemplate();

    public Result executeTest(String testExecutorUrl, String testContainerUrl, String testToRun){
        String finalUrl = testExecutorUrl + getEndpointForTest(testToRun) + serviceIp + testContainerUrl;
        System.out.println(finalUrl);
        return restTemplate.getForObject( finalUrl, Result.class );
    }

    public Result executeTest(String testExecutorUrl, String testContainerUrl, String testToRun, String additionalParameters){
        String finalUrl = testExecutorUrl + getEndpointForTest(testToRun) + serviceIp + testContainerUrl + "&" + additionalParameters;
        System.out.println(finalUrl);
        return restTemplate.getForObject( finalUrl, Result.class );
    }

    private String getEndpointForTest(String testToRun){
        switch (testToRun){
            case "Load Balancing":
                return "/testLoadBalancing";
            case "HTTP Endpoint":
                return "/testHttpEndpoint";
            case "Process Failure":
                return "/testProcessFailure";
            case "Node Failure":
                return "/testNodeFailure";
            case "Autoscaling":
                return "/testAutoScaling";
            default:
                return "/testLoadBalancing";
        }
    }

}
