package com.config.springrest.h2db.springbootrestinmemoryh2db;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withServerError;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import com.config.springrest.h2db.springbootrestinmemoryh2db.helper.ConfigRestHelper;

@ContextConfiguration(locations = {"classpath:/applicationContext-test.xml"})
public class ConfigControllerUnitTest extends AbstractJUnit4SpringContextTests{
	@Autowired
    private ConfigRestHelper configRestHelper;

    @Autowired
    private RestTemplate restTemplate;

    private MockRestServiceServer mockServer;

    @Before
    public void setUp() {

        mockServer = MockRestServiceServer.createServer(restTemplate);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetConfigByAppCodeMessage() {
        mockServer.expect(requestTo("http://localhost:8090/api/APPCODE/config")).andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("resultSuccess", MediaType.TEXT_PLAIN));

        String result = configRestHelper.getConfigByAppCodeMessage();

        mockServer.verify();
        assertThat(result, allOf(containsString("SUCCESS"), containsString("resultSuccess")));
    }

    @Test
    public void testGetConfigByAppCodeMessage_500() {
        mockServer.expect(requestTo("http://localhost:8090/api/APPCODE/config")).andExpect(method(HttpMethod.GET))
                .andRespond(withServerError());

        String result = configRestHelper.getConfigByAppCodeMessage();

        mockServer.verify();
        assertThat(result, allOf(containsString("FAILED"), containsString("500")));
    }

    @Test
    public void testGetConfigByAppCodeMessage_404() {
        mockServer.expect(requestTo("http://localhost:8090/api/APPCODE/config")).andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.NOT_FOUND));

        String result = configRestHelper.getConfigByAppCodeMessage();

        mockServer.verify();
        assertThat(result, allOf(containsString("FAILED"), containsString("404")));
    }
    
    @Test
    public void testGetConfigByAppCodeAndVersionMessage() {
        mockServer.expect(requestTo("http://localhost:8090/api/APPCODE/config/VERSION")).andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("resultSuccess", MediaType.TEXT_PLAIN));

        String result = configRestHelper.getConfigByAppCodeAndVersionMessage();

        mockServer.verify();
        assertThat(result, allOf(containsString("SUCCESS"), containsString("resultSuccess")));
    }
    
    @Test
    public void testGetConfigByAppCodeAndVersionMessage_500() {
        mockServer.expect(requestTo("http://localhost:8090/api/APPCODE/config/VERSION")).andExpect(method(HttpMethod.GET))
                .andRespond(withServerError());

        String result = configRestHelper.getConfigByAppCodeAndVersionMessage();

        mockServer.verify();
        assertThat(result, allOf(containsString("FAILED"), containsString("500")));
    }
    
    @Test
    public void testGetConfigByAppCodeAndVersionMessage_404() {
        mockServer.expect(requestTo("http://localhost:8090/api/APPCODE/config/VERSION")).andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.NOT_FOUND));

        String result = configRestHelper.getConfigByAppCodeAndVersionMessage();

        mockServer.verify();
        assertThat(result, allOf(containsString("FAILED"), containsString("404")));
    }
    
    @Test
    public void tesPostMessage() {
        mockServer.expect(requestTo("http://localhost:8090/api/APPCODE/config/VERSION")).andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess("resultSuccess", MediaType.TEXT_PLAIN));

        String result = configRestHelper.postMessage();

        mockServer.verify();
        assertThat(result, allOf(containsString("SUCCESS"), containsString("resultSuccess")));
    }
    
    @Test
    public void testPostMessage_500() {
        mockServer.expect(requestTo("http://localhost:8090/api/APPCODE/config/VERSION")).andExpect(method(HttpMethod.POST))
                .andRespond(withServerError());

        String result = configRestHelper.postMessage();

        mockServer.verify();
        assertThat(result, allOf(containsString("FAILED"), containsString("500")));
    }

    @Test
    public void testPostMessage_404() {
        mockServer.expect(requestTo("http://localhost:8090/api/APPCODE/config/VERSION")).andExpect(method(HttpMethod.POST))
                .andRespond(withStatus(HttpStatus.NOT_FOUND));

        String result = configRestHelper.postMessage();

        mockServer.verify();
        assertThat(result, allOf(containsString("FAILED"), containsString("404")));
    }
    
    @Test
    public void tesPutMessage() {
        mockServer.expect(requestTo("http://localhost:8090/api/APPCODE/config/VERSION")).andExpect(method(HttpMethod.PUT))
                .andRespond(withSuccess("resultSuccess", MediaType.TEXT_PLAIN));

        String result = configRestHelper.putMessage();

        mockServer.verify();
        assertThat(result, allOf(containsString("SUCCESS"), containsString("resultSuccess")));
    }
    
    @Test
    public void testPutMessage_500() {
        mockServer.expect(requestTo("http://localhost:8090/api/APPCODE/config/VERSION")).andExpect(method(HttpMethod.PUT))
                .andRespond(withServerError());

        String result = configRestHelper.putMessage();

        mockServer.verify();
        assertThat(result, allOf(containsString("FAILED"), containsString("500")));
    }
    
    @Test
    public void testPutMessage_404() {
        mockServer.expect(requestTo("http://localhost:8090/api/APPCODE/config/VERSION")).andExpect(method(HttpMethod.PUT))
                .andRespond(withStatus(HttpStatus.NOT_FOUND));

        String result = configRestHelper.putMessage();

        mockServer.verify();
        assertThat(result, allOf(containsString("FAILED"), containsString("404")));
    }
}
