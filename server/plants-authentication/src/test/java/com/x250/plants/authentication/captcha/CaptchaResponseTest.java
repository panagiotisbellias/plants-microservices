package com.x250.plants.authentication.captcha;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CaptchaResponseTest {

    @Test
    void testGettersAndSetters() {
        CaptchaResponse captchaResponse = new CaptchaResponse();
        captchaResponse.setSuccess(true);
        captchaResponse.setChallengeTs("2022-01-01T12:00:00Z");
        captchaResponse.setHostname("example.com");

        Assertions.assertTrue(captchaResponse.isSuccess());
        Assertions.assertEquals("2022-01-01T12:00:00Z", captchaResponse.getChallengeTs());
        Assertions.assertEquals("example.com", captchaResponse.getHostname());
    }

}
