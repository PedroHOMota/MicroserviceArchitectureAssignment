package ie.tus.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

import jakarta.inject.Inject;

@Service
public class PerformRest {

    private EurekaClient eurekaClient;

    final RestClient restClient = RestClient.builder().build();

    @Inject
    public PerformRest(EurekaClient eurekaClient){
        this.eurekaClient = eurekaClient;
    }

    public String getBaseUrl(String service){
        final InstanceInfo instanceInfo = eurekaClient.getApplication(service).getInstances().stream().findAny().get();

        final int port = instanceInfo.getPort();
        final String ipAddr = instanceInfo.getIPAddr();

        return "http://"+ipAddr+":"+port;
    }

    public Object performPost(String url,Object body, Class returnClass){
        return restClient.post()
            .uri(url)
            .body(body)
            .retrieve()
            .body(returnClass);
    }

    public Object performGet(String url, Class returnClass){
        return restClient.get()
            .uri(url)
            .retrieve()
            .body(returnClass);
    }

    public Object performPut(String url,Object body, Class returnClass){
        return restClient.put()
            .uri(url)
            .body(body)
            .retrieve()
            .body(returnClass);
    }

    public Object performDelete(String url,Object body, Class returnClass){
        return restClient.delete()
            .uri(url)
            .retrieve()
            .body(returnClass);
    }
}
