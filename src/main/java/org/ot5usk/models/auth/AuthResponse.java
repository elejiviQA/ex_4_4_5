package org.ot5usk.models.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthResponse {

    private String jwtToken;
}