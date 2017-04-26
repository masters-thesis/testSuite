package domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;

/**
 * Created by KURZRO on 07.04.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Container {

    private String id;
    private String ip;
    private long responsesFromContainer;

    public Container(){}

    public Container(String id, String ip, long responsesFromContainer) {
        this.id = id;
        this.ip = ip;
        this.responsesFromContainer = responsesFromContainer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void addResponse(){
        responsesFromContainer ++;
    }

    public long getResponsesFromContainer() {
        return responsesFromContainer;
    }

    public void setResponsesFromContainer(long responsesFromContainer) {
        this.responsesFromContainer = responsesFromContainer;
    }

    @Override
    public boolean equals(Object obj) {
        if( obj == null ){
            return false;
        }
        if ( !Container.class.isAssignableFrom( obj.getClass() ) ){
            return false;
        }
        final Container container = (Container) obj;
        if( !id.equals(container.id) ){
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Container{" +
                "id='" + id + '\'' +
                ", ip='" + ip + '\'' +
                ", responsesFromContainer=" + responsesFromContainer +
                '}';
    }
}
