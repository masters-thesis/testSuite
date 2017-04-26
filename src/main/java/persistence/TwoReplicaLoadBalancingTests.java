package persistence;

import javax.persistence.*;

/**
 * Created by blackbird on 4/22/17.
 */
@Entity
@Table(name="two_replica_load_balancing_tests")
public class TwoReplicaLoadBalancingTests {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id", nullable=false, unique=true)
    public long id;

    @Column(name = "platform")
    public String platform;

    @Column(name = "version")
    public String version;

    @Column(name = "ip_address_replica_one")
    public String ipAddressReplicaOne;

    @Column(name = "successful_connections_replica_one")
    public long successfulConnectionsReplicaOne;

    @Column(name = "ip_address_replica_two")
    public String ipAddressReplicaTwo;

    @Column(name = "successful_connections_replica_two")
    public long successfulConnectionsReplicaTwo;

    @Column(name = "failed_connections")
    public long failedConnections;

    @Column(name = "test_duration_in_ms")
    public long testDurationInMs;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TwoReplicaLoadBalancingTests(String platform, String version, String ipAddressReplicaOne, long successfulConnectionsReplicaOne, String ipAddressReplicaTwo, long successfulConnectionsReplicaTwo, long failedConnections, long testDurationInMs) {
        this.platform = platform;
        this.version = version;
        this.ipAddressReplicaOne = ipAddressReplicaOne;
        this.successfulConnectionsReplicaOne = successfulConnectionsReplicaOne;
        this.ipAddressReplicaTwo = ipAddressReplicaTwo;
        this.successfulConnectionsReplicaTwo = successfulConnectionsReplicaTwo;
        this.failedConnections = failedConnections;
        this.testDurationInMs = testDurationInMs;
    }

    public TwoReplicaLoadBalancingTests(){}

}
