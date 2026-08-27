package com.drcp.payload;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApiResponseTest {

    @Test
    void testSuccessApiResponse() {
        ApiResponse<String> response = ApiResponse.success("Operation successful", "Data Payload");

        assertTrue(response.isSuccess());
        assertEquals(200, response.getStatus());
        assertEquals("Operation successful", response.getMessage());
        assertEquals("Data Payload", response.getData());
        assertNotNull(response.getTimestamp());
        assertNull(response.getErrors());
    }

    @Test
    void testErrorApiResponse() {
        ApiResponse<Object> response = ApiResponse.error(400, "Validation error", "Field errors map");

        assertFalse(response.isSuccess());
        assertEquals(400, response.getStatus());
        assertEquals("Validation error", response.getMessage());
        assertNull(response.getData());
        assertEquals("Field errors map", response.getErrors());
        assertNotNull(response.getTimestamp());
    }
}
