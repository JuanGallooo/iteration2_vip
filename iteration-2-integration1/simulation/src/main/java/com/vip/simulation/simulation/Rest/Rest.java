package com.vip.simulation.simulation.Rest;

import java.io.IOException;

import org.springframework.http.ResponseEntity;

public interface Rest {
	public String getNewPositions() throws IOException;
}
