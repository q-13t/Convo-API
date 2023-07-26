package edu.convo;

import org.springframework.web.bind.annotation.*;

import com.google.gson.JsonObject;

@RestController
public class HelloController {

    @RequestMapping("/")
    String hello() {
        return "Hello World!";
    }

    /**
     * Mapping for testing request response in browser.
     * </p>
     * Request example: http://localhost:8080/test?from=5&to=5
     * 
     * @return specific JSON response for testing purposes
     */
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    JsonObject testingMapping(Double from, Double to) {
        JsonObject response = new JsonObject();
        try {
            response.addProperty("result", from + to);
        } catch (Exception e) {
            response.addProperty("result", "ERROR");
        }
        return response;
    }
}
