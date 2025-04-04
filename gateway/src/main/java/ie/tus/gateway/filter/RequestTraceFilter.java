package ie.tus.gateway.filter;


import static ie.tus.gateway.filter.FilterUtility.TRACE_ID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Order(1)
@Component
public class RequestTraceFilter implements GlobalFilter {

    private static final Logger logger = LoggerFactory.getLogger(RequestTraceFilter.class);

    @Autowired
    FilterUtility filterUtility;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        HttpHeaders requestHeaders = exchange.getRequest().getHeaders();
        if (isCorrelationIdPresent(requestHeaders)) {
            logger.debug("{} found in RequestTraceFilter : {}", TRACE_ID,
                    filterUtility.getTraceId(requestHeaders));
        } else {
            String correlationID = generateTraceId();
            exchange = filterUtility.setCorrelationId(exchange, correlationID);
            logger.debug("{} generated in RequestTraceFilter : {}", TRACE_ID, correlationID);
        }
        return chain.filter(exchange);
    }

    private boolean isCorrelationIdPresent(HttpHeaders requestHeaders) {
        if (filterUtility.getTraceId(requestHeaders) != null) {
            return true;
        } else {
            return false;
        }
    }

    private String generateTraceId() {
        return java.util.UUID.randomUUID().toString();
    }
}