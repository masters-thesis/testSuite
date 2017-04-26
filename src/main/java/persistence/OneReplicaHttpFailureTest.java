package persistence;

import javax.persistence.*;

/**
 * Created by blackbird on 4/22/17.
 */
@Entity
@Table(name="one_replica_http_failure_tests")
public class OneReplicaHttpFailureTest {

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

    public OneReplicaHttpFailureTest(String platform, String version, String ipAddressReplicaOne, long successfulConnectionsReplicaOne, long failedConnections, long testDurationInMs) {
        this.platform = platform;
        this.version = version;
        this.ipAddressReplicaOne = ipAddressReplicaOne;
        this.successfulConnectionsReplicaOne = successfulConnectionsReplicaOne;
        this.failedConnections = failedConnections;
        this.testDurationInMs = testDurationInMs;
    }

    public OneReplicaHttpFailureTest(){}

}
