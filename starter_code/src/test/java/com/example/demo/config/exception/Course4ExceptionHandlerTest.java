package com.example.demo.config.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import javax.servlet.FilterChain;

import org.apache.catalina.core.DummyRequest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

class Course4ExceptionHandlerTest {
    /**
     * Method under test: {@link Course4ExceptionHandler#handleInternalServerError(Exception, WebRequest)}
     */
    @Test
    void testHandleInternalServerError() {
        Course4ExceptionHandler course4ExceptionHandler = new Course4ExceptionHandler();
        Exception ex = new Exception();
        ResponseEntity<Object> actualHandleInternalServerErrorResult = course4ExceptionHandler
                .handleInternalServerError(ex,
                        new ServletWebRequest(new DummyRequest("Context Path", "Decoded URI", "Query String")));
        assertTrue(actualHandleInternalServerErrorResult.hasBody());
        assertTrue(actualHandleInternalServerErrorResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, actualHandleInternalServerErrorResult.getStatusCode());
        assertNull(((Course4ExceptionHandler.ErrorResponse) actualHandleInternalServerErrorResult.getBody())
                .getError());
        assertNull(((Course4ExceptionHandler.ErrorResponse) actualHandleInternalServerErrorResult.getBody()).getData());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,
                ((Course4ExceptionHandler.ErrorResponse) actualHandleInternalServerErrorResult.getBody()).getStatus());
        assertEquals(500,
                ((Course4ExceptionHandler.ErrorResponse) actualHandleInternalServerErrorResult.getBody())
                        .getStatusCode());
    }

    /**
     * Method under test: {@link Course4ExceptionHandler#handleInternalServerError(Exception, WebRequest)}
     */
    @Test
    void testHandleInternalServerError2() {

        Course4ExceptionHandler course4ExceptionHandler = new Course4ExceptionHandler();

        course4ExceptionHandler.handleInternalServerError(new RuntimeException("ahihi"),
                new ServletWebRequest(new DummyRequest("Context Path", "Decoded URI", "Query String")));
    }

    /**
     * Method under test: {@link Course4ExceptionHandler#handleInternalServerError(Exception, WebRequest)}
     */
    @Test
    void testHandleInternalServerError3() {

        Course4ExceptionHandler course4ExceptionHandler = new Course4ExceptionHandler();
        course4ExceptionHandler.handleInternalServerError(new Exception(), new ServletWebRequest(new DummyRequest()));
    }

    /**
     * Method under test: {@link Course4ExceptionHandler#handleInternalServerError(Exception, WebRequest)}
     */
    @Test
    void testHandleInternalServerError4() {
        Course4ExceptionHandler course4ExceptionHandler = new Course4ExceptionHandler();
        Exception ex = new Exception();

        DummyRequest request = new DummyRequest("Context Path", "Decoded URI", "Query String");
        request.setFilterChain(mock(FilterChain.class));
        ResponseEntity<Object> actualHandleInternalServerErrorResult = course4ExceptionHandler
                .handleInternalServerError(ex, new ServletWebRequest(request));
        assertTrue(actualHandleInternalServerErrorResult.hasBody());
        assertTrue(actualHandleInternalServerErrorResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, actualHandleInternalServerErrorResult.getStatusCode());
        assertNull(((Course4ExceptionHandler.ErrorResponse) actualHandleInternalServerErrorResult.getBody())
                .getError());
        assertNull(((Course4ExceptionHandler.ErrorResponse) actualHandleInternalServerErrorResult.getBody()).getData());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,
                ((Course4ExceptionHandler.ErrorResponse) actualHandleInternalServerErrorResult.getBody()).getStatus());
        assertEquals(500,
                ((Course4ExceptionHandler.ErrorResponse) actualHandleInternalServerErrorResult.getBody())
                        .getStatusCode());
    }

    /**
     * Method under test: {@link Course4ExceptionHandler#handleCourse4Exception(Course4Exception, WebRequest)}
     */
    @Test
    void testHandleCourse4Exception() {
        Course4ExceptionHandler course4ExceptionHandler = new Course4ExceptionHandler();
        Course4Exception ex = new Course4Exception("An error occurred");
        ResponseEntity<Object> actualHandleCritterExceptionResult = course4ExceptionHandler.handleCourse4Exception(ex,
                new ServletWebRequest(new DummyRequest("Context Path", "Decoded URI", "Query String")));
        assertTrue(actualHandleCritterExceptionResult.hasBody());
        assertTrue(actualHandleCritterExceptionResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.PRECONDITION_FAILED, actualHandleCritterExceptionResult.getStatusCode());
        assertEquals("An error occurred",
                ((Course4ExceptionHandler.ErrorResponse) actualHandleCritterExceptionResult.getBody()).getError());
        assertNull(((Course4ExceptionHandler.ErrorResponse) actualHandleCritterExceptionResult.getBody()).getData());
        assertEquals(HttpStatus.PRECONDITION_FAILED,
                ((Course4ExceptionHandler.ErrorResponse) actualHandleCritterExceptionResult.getBody()).getStatus());
        assertEquals(412,
                ((Course4ExceptionHandler.ErrorResponse) actualHandleCritterExceptionResult.getBody()).getStatusCode());
    }

    /**
     * Method under test: {@link Course4ExceptionHandler#handleCourse4Exception(Course4Exception, WebRequest)}
     */
    @Test
    void testHandleCourse4Exception2() {

        Course4ExceptionHandler course4ExceptionHandler = new Course4ExceptionHandler();
        course4ExceptionHandler.handleCourse4Exception(new Course4Exception("ahihi"),
                new ServletWebRequest(new DummyRequest("Context Path", "Decoded URI", "Query String")));
    }

    /**
     * Method under test: {@link Course4ExceptionHandler#handleCourse4Exception(Course4Exception, WebRequest)}
     */
    @Test
    void testHandleCourse4Exception3() {
        Course4ExceptionHandler course4ExceptionHandler = new Course4ExceptionHandler();
        Course4Exception ex = new Course4Exception("An error occurred");

        DummyRequest request = new DummyRequest("Context Path", "Decoded URI", "Query String");
        request.setFilterChain(mock(FilterChain.class));
        ResponseEntity<Object> actualHandleCritterExceptionResult = course4ExceptionHandler.handleCourse4Exception(ex,
                new ServletWebRequest(request));
        assertTrue(actualHandleCritterExceptionResult.hasBody());
        assertTrue(actualHandleCritterExceptionResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.PRECONDITION_FAILED, actualHandleCritterExceptionResult.getStatusCode());
        assertEquals("An error occurred",
                ((Course4ExceptionHandler.ErrorResponse) actualHandleCritterExceptionResult.getBody()).getError());
        assertNull(((Course4ExceptionHandler.ErrorResponse) actualHandleCritterExceptionResult.getBody()).getData());
        assertEquals(HttpStatus.PRECONDITION_FAILED,
                ((Course4ExceptionHandler.ErrorResponse) actualHandleCritterExceptionResult.getBody()).getStatus());
        assertEquals(412,
                ((Course4ExceptionHandler.ErrorResponse) actualHandleCritterExceptionResult.getBody()).getStatusCode());
    }
}
