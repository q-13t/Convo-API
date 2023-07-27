package edu.convo;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.JsonObject;

@RestController
public class ConversionController {

    private final Logger logger = LoggerFactory.getLogger(getClass());
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

    private static final HashMap<String, Double> weightConversionRate = new HashMap<String, Double>() {
        {
            put("milligram", 0.000001d);
            put("gram", 0.001d);
            put("kilogram", 1d);
            put("ton", 1000d);
            put("ounce", 0.02835d);
            put("pound", 0.453592d);
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
     * Converts 9 units to one another using {@code meter}s as base conversion rate.
     * </p>
     * Available units are:
     * <ul>
     * <li>Millimeter</li>
     * <li>Centimeter</li>
     * <li>Decimeter</li>
     * <li>Meter</li>
     * <li>Kilometer</li>
     * <li>Inch</li>
     * <li>Feet</li>
     * <li>Yard</li>
     * <li>Mile</li>
     * </ul>
     * 
     * @param from  String containing from which unit conversion is.
     * @param to    String containing to which unit conversion is.
     * @param value to convert.
     * @return JSON object containing result of calculation.
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

            double convertedValue = value * (lengthConversionRate.get(from) / lengthConversionRate.get(to));
            response.addProperty("result", convertedValue);
            logger.info("SUCCESS: converted length : [ " + from + " , " + to + " , " + value + " -> " + convertedValue
                    + "]");
        } catch (Exception e) {
            logger.error("ERROR in length conversion: [ " + from + " , " + to + " , " + value + " ]", e);
            response.addProperty("result", "ERROR");
        }
        return response;
    }

    /**
     * Converts 6 units to one another using {@code kilogram}s as base conversion
     * rate.
     * </p>
     * Available units are:
     * <ul>
     * <li>Milligram</li>
     * <li>Gram</li>
     * <li>Kilogram</li>
     * <li>Ton</li>
     * <li>Ounce</li>
     * <li>Pound</li>
     * </ul>
     * 
     * @param from  String containing from which unit conversion is.
     * @param to    String containing to which unit conversion is.
     * @param value to convert.
     * @return JSON object containing result of calculation.
     * @see #weightConversionRate
     */
    @RequestMapping(value = "/weight", method = RequestMethod.GET)
    JsonObject weightConverter(
            @RequestParam(value = "from") String from,
            @RequestParam(value = "to") String to,
            @RequestParam(value = "value") double value) {
        JsonObject response = new JsonObject();
        try {
            if (from.isEmpty())
                from = "kilogram";
            if (to.isEmpty())
                to = "kilogram";
            double convertedValue = value * (weightConversionRate.get(from) / weightConversionRate.get(to));
            response.addProperty("result", convertedValue);
            logger.info("SUCCESS: converted weight : [ " + from + " , " + to + " , " + value + " -> " + convertedValue
                    + "]");
        } catch (Exception e) {
            logger.error("ERROR in weight conversion: [ " + from + " , " + to + " , " + value + " ]", e);
            response.addProperty("result", "ERROR");
        }
        return response;
    }
}
