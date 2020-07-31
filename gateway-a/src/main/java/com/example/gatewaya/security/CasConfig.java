package com.example.gatewaya.security;

import org.jasig.cas.client.validation.Cas20ServiceTicketValidator;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.cas.web.CasAuthenticationFilter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configurable
public class CasConfig {

    @Bean
    public ServiceProperties serviceProperties() {
        ServiceProperties serviceProperties = new ServiceProperties();
        // Cas服务器地址
        serviceProperties.setService("https://localhost:8443/cas-sample/login/cas");
        serviceProperties.setSendRenew(false);
        return serviceProperties;
    }

    @Bean
    public CasAuthenticationFilter casAuthenticationFilter(AuthenticationManager authenticationManager) {
        CasAuthenticationFilter casAuthenticationFilter = new CasAuthenticationFilter();
        casAuthenticationFilter.setAuthenticationManager(authenticationManager);
        return casAuthenticationFilter;
    }

    @Bean
    public CasAuthenticationEntryPoint casAuthenticationEntryPoint(ServiceProperties serviceProperties) {
        CasAuthenticationEntryPoint casAuthenticationEntryPoint = new CasAuthenticationEntryPoint();
        casAuthenticationEntryPoint.setServiceProperties(serviceProperties);
        casAuthenticationEntryPoint.setLoginUrl("https://localhost:9443/cas/login"); // cas登录服务器
        return casAuthenticationEntryPoint;
    }

    @Bean
    public CasAuthenticationProvider casAuthenticationProvider(Cas20ServiceTicketValidator cas20ServiceTicketValidator,
                                                               ServiceProperties serviceProperties,
                                                               UserDetailsService userDetailsService) {
        CasAuthenticationProvider casAuthenticationProvider = new CasAuthenticationProvider();
        casAuthenticationProvider.setTicketValidator(cas20ServiceTicketValidator);
        casAuthenticationProvider.setServiceProperties(serviceProperties);
        casAuthenticationProvider.setUserDetailsService(userDetailsService);
        return casAuthenticationProvider;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
        UserDetails jim = new User("jim","123456",null);
        UserDetails bob = new User("bob","123456",null);
        inMemoryUserDetailsManager.createUser(jim);
        inMemoryUserDetailsManager.createUser(bob);
        return inMemoryUserDetailsManager;
    }

    @Bean
    public Cas20ServiceTicketValidator cas20ServiceTicketValidator() {
        // 调用 cas server 验证ticket
        Cas20ServiceTicketValidator cas20ServiceTicketValidator =
                new Cas20ServiceTicketValidator("http://v5test.histudy.com:20900");
        return cas20ServiceTicketValidator;
    }
}
