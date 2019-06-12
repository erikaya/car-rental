package com.example.carrental;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.StubTrigger;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.BDDAssertions.then;
import static org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties.StubsMode.REMOTE;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureStubRunner(
        repositoryRoot = "git://https://github.com/erikaya/spring-cloud-contracts-poc.git",
        ids = "com.example:fraud-detection:0.0.1-SNAPSHOT",
        stubsMode = REMOTE
)
class FraudListenerTest {

    @Autowired
    private FraudListener fraudListener;

    @Autowired
    private StubTrigger stubTrigger;

    @Test
    void shouldStoreAFraud() {
        this.stubTrigger.trigger("trigger_fraud");

        then(fraudListener.fraud).isNotNull();
        then(fraudListener.fraud.name).isEqualTo("m");
    }
}