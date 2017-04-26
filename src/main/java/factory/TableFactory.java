package factory;

import persistence.*;
import util.Result;

/**
 * Created by blackbird on 4/23/17.
 */
public class TableFactory {

    public Object getPreparedTable(
            String test,
            int replicaAmount,
            String platform,
            String version,
            Result result) throws Exception {

        switch (test){
            case "Load Balancing":
                switch (replicaAmount){
                    case 2:
                        return new TwoReplicaLoadBalancingTests(
                                platform,
                                version,
                                result.getContainerInformation().get(0).getIp(),
                                result.getContainerInformation().get(0).getResponsesFromContainer(),
                                result.getContainerInformation().get(1).getIp(),
                                result.getContainerInformation().get(1).getResponsesFromContainer(),
                                result.getNumberOfFailedRequests(),
                                result.getWaitForStateDurationInMilliseconds()
                        );
                    case 3:
                        return new ThreeReplicaLoadBalancingTest(
                                platform,
                                version,
                                result.getContainerInformation().get(0).getIp(),
                                result.getContainerInformation().get(0).getResponsesFromContainer(),
                                result.getContainerInformation().get(1).getIp(),
                                result.getContainerInformation().get(1).getResponsesFromContainer(),
                                result.getContainerInformation().get(2).getIp(),
                                result.getContainerInformation().get(2).getResponsesFromContainer(),
                                result.getNumberOfFailedRequests(),
                                result.getWaitForStateDurationInMilliseconds()
                        );
                    default:
                        throw new Exception();
                }

            case "Process Failure":
                switch (replicaAmount){
                    case 1:
                        return new OneReplicaProcessFailureTest(
                                platform,
                                version,
                                result.getContainerInformation().get(0).getIp(),
                                result.getContainerInformation().get(0).getResponsesFromContainer(),
                                result.getNumberOfFailedRequests(),
                                result.getWaitForStateDurationInMilliseconds()
                        );
                    case 2:
                        return new TwoReplicaProcessFailureTest(
                                platform,
                                version,
                                result.getContainerInformation().get(0).getIp(),
                                result.getContainerInformation().get(0).getResponsesFromContainer(),
                                result.getContainerInformation().get(1).getIp(),
                                result.getContainerInformation().get(1).getResponsesFromContainer(),
                                result.getNumberOfFailedRequests(),
                                result.getWaitForStateDurationInMilliseconds()
                        );
                    default:
                        throw new Exception();
                }
            case "HTTP Failure":
                switch (replicaAmount){
                    case 1:
                        return new OneReplicaHttpFailureTest(
                                platform,
                                version,
                                result.getContainerInformation().get(0).getIp(),
                                result.getContainerInformation().get(0).getResponsesFromContainer(),
                                result.getNumberOfFailedRequests(),
                                result.getWaitForStateDurationInMilliseconds()
                        );
                    case 2:
                        return new TwoReplicaHttpFailureTest(
                                platform,
                                version,
                                result.getContainerInformation().get(0).getIp(),
                                result.getContainerInformation().get(0).getResponsesFromContainer(),
                                result.getContainerInformation().get(1).getIp(),
                                result.getContainerInformation().get(1).getResponsesFromContainer(),
                                result.getNumberOfFailedRequests(),
                                result.getWaitForStateDurationInMilliseconds()
                        );
                    default:
                        throw new Exception();
                }
            case "Node Failure":
                switch (replicaAmount){
                    case 1:
                        return new OneReplicaNodeFailureTest(
                                platform,
                                version,
                                result.getContainerInformation().get(0).getIp(),
                                result.getContainerInformation().get(0).getResponsesFromContainer(),
                                result.getNumberOfFailedRequests(),
                                result.getWaitForStateDurationInMilliseconds()
                        );
                    case 2:
                        return new TwoReplicaNodeFailureTest(
                                platform,
                                version,
                                result.getContainerInformation().get(0).getIp(),
                                result.getContainerInformation().get(0).getResponsesFromContainer(),
                                result.getContainerInformation().get(1).getIp(),
                                result.getContainerInformation().get(1).getResponsesFromContainer(),
                                result.getNumberOfFailedRequests(),
                                result.getWaitForStateDurationInMilliseconds()
                        );
                    default:
                        throw new Exception();
                }
            case "Autoscaling":
                switch (replicaAmount){
                    case 1:
                        return new OneReplicaAutoScalingTest(
                                platform,
                                version,
                                result.getContainerInformation().get(0).getIp(),
                                result.getContainerInformation().get(0).getResponsesFromContainer(),
                                result.getContainerInformation().get(1).getIp(),
                                result.getContainerInformation().get(1).getResponsesFromContainer(),
                                result.getNumberOfFailedRequests(),
                                result.getWaitForStateDurationInMilliseconds()
                        );
                    default:
                        throw new Exception();
                }
                default:
                    throw new Exception();
        }

    }

}