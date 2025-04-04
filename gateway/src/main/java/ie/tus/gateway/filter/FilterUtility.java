
package ie.tus.gateway.filter;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

@Component
public class FilterUtility {
    public static final String TRACE_ID = "cookbookap-trace-id";

    public String getTraceId(HttpHeaders requestHeaders) {
        if (requestHeaders.get(TRACE_ID) != null) {
            List<String> requestHeaderList = requestHeaders.get(TRACE_ID);
            return requestHeaderList.stream().findFirst().get();
        } else {
            return null;
        }
    }

    public ServerWebExchange setRequestHeader(ServerWebExchange exchange, String name, String value) {
        return exchange.mutate().request(exchange.getRequest().mutate().header(name, value).build()).build();
    }

    public ServerWebExchange setCorrelationId(ServerWebExchange exchange, String traceId) {
        return this.setRequestHeader(exchange, TRACE_ID, traceId);
    }
}
