package edu.convo;

import java.util.HashMap;

import org.springframework.web.bind.annotation.*;

import com.google.gson.JsonObject;

@RestController
public class ConversionController {
    private static final HashMap<String, Double> lengthConversionRate = new HashMap<String, Double>() {
        {
            put("millimeter", 0.0011d);
            put("centimeter", 0.01d);
            put("decimeter", 0.1d);
            put("meter", 1d);
            put("kilometer", 1000d);
            put("inch", 0.0254d);
            put("feet", 0.3048d);
            put("yard", 0.9144d);
            put("mile", 1609.344d);
        }
    };

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

    /**
     * Converts 8 units to one another using {@code meter}s as base conversion rate.
     * 
     * @param from  String containing from which unit conversion is
     * @param to    String containing to which unit conversion is
     * @param value to convert
     * @return
     * @see #lengthConversionRate
     */
    @RequestMapping(value = "/length", method = RequestMethod.GET)
    JsonObject lengthConverter(
            @RequestParam(value = "from") String from,
            @RequestParam(value = "to") String to,
            @RequestParam(value = "value") double value) {
        JsonObject response = new JsonObject();
        try {
            if (from.isEmpty())
                from = "meter";
            if (to.isEmpty())
                to = "meter";
            double rate = lengthConversionRate.get(from) / lengthConversionRate.get(to);
            response.addProperty("result", value * rate);
        } catch (Exception e) {
            response.addProperty("result", "ERROR");
        }
        return response;
    }
}
