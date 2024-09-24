package com.weather.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.internal.build.AllowNonPortable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String jwttoken;

	public AuthenticationResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AuthenticationResponse(String jwttoken) {
		super();
		this.jwttoken = jwttoken;
	}

	public String getJwttoken() {
		return jwttoken;
	}

	public void setJwttoken(String jwttoken) {
		this.jwttoken = jwttoken;
	}
}
