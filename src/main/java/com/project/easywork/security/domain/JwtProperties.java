package com.project.easywork.security.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="jwt")
public record JwtProperties(
    String secret,
    long accessTokenValidity,
    long refreshTokenValidity
) {
}
