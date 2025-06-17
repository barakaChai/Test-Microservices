package com.microservices10.department_service.config;

import com.microservices10.department_service.client.EmployeeClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WebClientConfig {

    /**
     * FIX 1: Create a WebClient.Builder bean annotated with @LoadBalanced.
     * This is the key change. Instead of injecting the filter function, we let
     * Spring Cloud automatically configure this builder with everything it needs
     * for client-side load balancing (including service discovery lookup).
     * This is the most reliable and standard approach.
     */
    @Bean
    @LoadBalanced
    public WebClient.Builder loadBalancedWebClientBuilder() {
        return WebClient.builder();
    }

    /**
     * FIX 2: Create the actual WebClient instance by using the pre-configured builder.
     * We inject the `WebClient.Builder` from the method above, not a raw one.
     * @param webClientBuilder The load-balanced builder.
     * @return A WebClient instance ready for load-balanced calls.
     */
    @Bean
    public WebClient employeeWebClient(WebClient.Builder webClientBuilder) {
        return webClientBuilder
                .baseUrl("http://employee-service") // The logical service name
                .build();
    }

    /**
     * FIX 3: The factory now receives the fully-formed, load-balanced WebClient.
     * No changes are needed here, but its input is now correctly configured.
     */
    @Bean
    public EmployeeClient employeeClient(WebClient employeeWebClient) {
        WebClientAdapter adapter = WebClientAdapter.create(employeeWebClient);
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builder()
                .exchangeAdapter(adapter)
                .build();
        return httpServiceProxyFactory.createClient(EmployeeClient.class);
    }
}
