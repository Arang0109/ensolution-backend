package com.project.easywork.auth.security.domain;

import java.util.List;

public record JwtToken(
    String grantType,
    String username,
    String accessToken,
    String refreshToken,
    List<String> roles
) {}