package com.jos.dem.jmailer.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@Component
@ConfigurationProperties("email")
public class EmailProperties {
    private List<String> spamTokens;
    private List<String> spamNames;
}
