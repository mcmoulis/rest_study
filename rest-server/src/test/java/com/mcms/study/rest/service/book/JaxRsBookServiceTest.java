package com.mcms.study.rest.service.book;

import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSBindingFactory;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.mcms.study.rest.model.book.Book;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/spring/jaxrs-cxf-servlet-test.xml" })
public class JaxRsBookServiceTest extends Assert {

    @Value("#{systemProperties['bookServicePath'] ?: \"http://localhost:9999/study/services/\"}")
    private String basePath;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private JAXRSServerFactoryBean serverFactory;

    private Server server;
    private String bookServicePath;

    @Before
    public void beforeMethod() {
        serverFactory.setBindingId(JAXRSBindingFactory.JAXRS_BINDING_ID);
        serverFactory.setAddress(basePath);
        server = serverFactory.create();
        server.start();
        bookServicePath = basePath + "/book";
    }

    @After
    public void afterMethod() {
        server.stop();
        server.destroy();
    }

    @Test
    public void testReadOk() {
        ResponseEntity<Book> entity = restTemplate.getForEntity(bookServicePath + "/1", Book.class);
        assertNotNull(entity.getBody());
    }

    @Test
    public void testReadFail() {
        try {
            restTemplate.getForEntity(bookServicePath + "/4", Book.class);
            fail("Entity must not exist");
        } catch (HttpStatusCodeException hcee) {
            assertEquals(HttpStatus.NOT_FOUND, hcee.getStatusCode());
        }
    }

    @Test
    public void testCreateOk() {
        ResponseEntity<Book> entity = restTemplate.postForEntity(bookServicePath, Book.builder().withBookId(3L).build(), Book.class);
        assertEquals(HttpStatus.NO_CONTENT, entity.getStatusCode());
    }

    @Test
    public void testCreateFail() {
        try {
            restTemplate.postForEntity(bookServicePath, Book.builder().withBookId(1L).build(), Book.class);
            fail("Create must fail");
        } catch (HttpStatusCodeException hcee) {
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, hcee.getStatusCode());
        }
    }

}