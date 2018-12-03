package com.aravindh.commentFeedback.api;

import com.aravindh.commentFeedback.service.FeedBackValidationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import static spark.Spark.*;

import java.io.Serializable;
import java.util.List;

public class FeedBackAPI {
    private static final int PORT_NUMBER = 8080;
    public static void main(String[] args) {
        System.out.println("Starting up with PORT "+PORT_NUMBER);
        port(PORT_NUMBER);

        get("/api/health/check", "application/json", (req, res) -> {
            String ok = "OK";
            res.type("application/json");
            res.status(200);
            return buildResponse(200, ok);
        });

        post("/api/feedback", "application/json", (req, res) -> {
            List<String> comments = FeedBackValidationService.validate(req.body());
            res.type("application/json");
            res.status(200);
            return buildResponse(200, comments);
        });


    }

    private static class ResponseDTO implements Serializable {
        private static final long serialVersionUID = 1L;

        private int responseCode;
        private Object responseData;

        public ResponseDTO(int responseCode, Object responseData) {
            this.responseCode = responseCode;
            this.responseData = responseData;
        }

        public int getResponseCode() {
            return responseCode;
        }

        public void setResponseCode(int responseCode) {
            this.responseCode = responseCode;
        }

        public Object getResponseData() {
            return responseData;
        }

        public void setResponseData(Object responseData) {
            this.responseData = responseData;
        }
    }

    private static String buildResponse(int code, Object data) {
        try {
            return new ObjectMapper().writeValueAsString(new ResponseDTO(code, data));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serilaize to JSON");
        }
    }
}
