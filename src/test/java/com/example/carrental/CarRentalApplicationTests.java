package com.example.carrental;

import org.assertj.core.api.BDDAssertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.junit.StubRunnerRule;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties.StubsMode.LOCAL;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarRentalApplicationTests {

	@Rule
	public StubRunnerRule rule = new StubRunnerRule()
			.downloadStub("com.example:fraud-detection:0.0.1-SNAPSHOT:stubs")
			.withPort(9876)
			.stubsMode(LOCAL);

	@Test
	public void shouldReturnAListOfFrauds() {
		String response = "[\"marcin\",\"josh\"]";

		ResponseEntity<String> responseEntity = new RestTemplate()
				.getForEntity("http://localhost:9876/fraud", String.class);

		BDDAssertions.then(responseEntity.getBody()).isEqualTo(response);
	}

}
