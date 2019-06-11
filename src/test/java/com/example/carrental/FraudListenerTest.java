package com.example.carrental;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.StubTrigger;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.BDDAssertions.then;
import static org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties.StubsMode.LOCAL;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureStubRunner(
        ids = "com.example:fraud-detection",
        stubsMode = LOCAL
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
        then(fraudListener.fraud.surname).isEqualTo("m");
    }
}