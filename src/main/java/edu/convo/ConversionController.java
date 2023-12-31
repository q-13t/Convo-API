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

    private static final HashMap<String, Double> areaConversionRate = new HashMap<String, Double>() {
        {
            put("millimeter", 0.000001d);
            put("centimeter", 0.0001d);
            put("meter", 1d);
            put("acre", 4046.8564224d);
            put("hectare", 10000d);
            put("kilometer", 1000000d);
            put("inch", 0.00064516d);
            put("feet", 0.09290304d);
            put("yard", 0.83612736d);
            put("mile", 2589988.110336d);
        }
    };

    private static final HashMap<String, Double> speedConversionRate = new HashMap<String, Double>() {
        {
            put("cps", 0.01d);
            put("mps", 1d);
            put("kph", 0.2777777778d);
            put("fps", 0.3048d);
            put("mph", 0.44704d);
            put("k", 0.5144444444d);
            put("m", 295.0464000003d);
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
     * @param from  String containing {@code from} which unit conversion is.
     * @param to    String containing {@code to} which unit conversion is.
     * @param value to convert.
     * @return JSON object containing result of calculation.
     * @see #lengthConversionRate
     */
    @RequestMapping(value = "/length", method = RequestMethod.GET)
    JsonObject lengthConverter(
            @RequestParam(value = "from") String from,
            @RequestParam(value = "to") String to,
            @RequestParam(value = "value") double value) {
        from = from.toLowerCase();
        to = to.toLowerCase();
        JsonObject response = new JsonObject();
        try {
            if (from.isEmpty())
                from = "meter";
            if (to.isEmpty())
                to = "meter";

            double convertedValue = value * (lengthConversionRate.get(from) / lengthConversionRate.get(to));
            response.addProperty("result", convertedValue);
            logger.info("SUCCESS: converted length: [ " + from + " , " + to + " , " + value + " -> " + convertedValue
                    + "]");
        } catch (Exception e) {
            logger.error("ERROR in length conversion: [ " + from + " , " + to + " , " + value + " ]", e.getCause());
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
     * @param from  String containing {@code from} which unit conversion is.
     * @param to    String containing {@code to} which unit conversion is.
     * @param value to convert.
     * @return JSON object containing result of calculation.
     * @see #weightConversionRate
     */
    @RequestMapping(value = "/weight", method = RequestMethod.GET)
    JsonObject weightConverter(
            @RequestParam(value = "from") String from,
            @RequestParam(value = "to") String to,
            @RequestParam(value = "value") double value) {
        from = from.toLowerCase();
        to = to.toLowerCase();
        JsonObject response = new JsonObject();
        try {
            if (from.isEmpty())
                from = "kilogram";
            if (to.isEmpty())
                to = "kilogram";
            double convertedValue = value * (weightConversionRate.get(from) / weightConversionRate.get(to));
            response.addProperty("result", convertedValue);
            logger.info("SUCCESS: converted weight: [ " + from + " , " + to + " , " + value + " -> " + convertedValue
                    + "]");
        } catch (Exception e) {
            logger.error("ERROR in weight conversion: [ " + from + " , " + to + " , " + value + " ]", e.getCause());
            response.addProperty("result", "ERROR");
        }
        return response;
    }

    /**
     * Converts 3 units to one another .
     * </p>
     * Available units are:
     * <ul>
     * <li>Kelvin</li>
     * <li>Fahrenheit</li>
     * <li>Celsius</li>
     * </ul>
     * 
     * @param from  String containing {@code from} which unit conversion is.
     * @param to    String containing {@code to} which unit conversion is.
     * @param value to convert.
     * @return JSON object containing result of calculation.
     * @see #weightConversionRate
     */
    @RequestMapping(value = "/temperature")
    JsonObject temperatureConverter(
            @RequestParam(value = "from") String from,
            @RequestParam(value = "to") String to,
            @RequestParam(value = "value") double value) {
        from = from.toLowerCase();
        to = to.toLowerCase();
        JsonObject response = new JsonObject();
        try {
            if (from.isEmpty())
                from = "kelvin";
            if (to.isEmpty())
                to = "kelvin";
            double convertedValue = 0;
            if (from.equals(to)) {
                convertedValue = value;
            } else if (from.equals("fahrenheit") && to.equals("celsius")) { // fahrenheit -> celsius
                convertedValue = (value - 32) * (5 / 9);
            } else if (from.equals("fahrenheit") && to.equals("kelvin")) { // fahrenheit -> kelvin
                convertedValue = (value + 459.67) * (5 / 9);
            } else if (from.equals("celsius") && to.equals("fahrenheit")) {// celsius -> fahrenheit
                convertedValue = value * 9 / 5 + 32;
            } else if (from.equals("celsius") && to.equals("kelvin")) {// celsius -> kelvin
                convertedValue = value + 273.15;
            } else if (from.equals("kelvin") && to.equals("celsius")) {// kelvin -> celsius
                convertedValue = value - 273.15;
            } else if (from.equals("kelvin") && to.equals("fahrenheit")) {// kelvin -> fahrenheit
                convertedValue = (value * 9 / 5) - 459.67;
            }
            response.addProperty("result", convertedValue);
            logger.info(
                    "SUCCESS: converted temperature: [ " + from + " , " + to + " , " + value + " -> " + convertedValue
                            + "]");
        } catch (Exception e) {
            logger.error("ERROR in temperature conversion: [ " + from + " , " + to + " , " + value + " ]",
                    e.getCause());
            response.addProperty("result", "ERROR");
        }
        return response;
    }

    /**
     * Converts 10 units to one another using {@code meter}s as base conversion
     * rate.
     * </p>
     * Available units are:
     * <ul>
     * <li>Millimeter</li>
     * <li>Centimeter</li>
     * <li>Meter</li>
     * <li>Acre</li>
     * <li>Hectare</li>
     * <li>Kilometer</li>
     * <li>Inch</li>
     * <li>Feet</li>
     * <li>Yard</li>
     * <li>Mile</li>
     * </ul>
     * 
     * @param from  String containing {@code from} which unit conversion is.
     * @param to    String containing {@code to} which unit conversion is.
     * @param value to convert.
     * @return JSON object containing result of calculation.
     * @see #areaConversionRate
     */
    @RequestMapping(value = "/area", method = RequestMethod.GET)
    JsonObject areaConverter(
            @RequestParam(value = "from") String from,
            @RequestParam(value = "to") String to,
            @RequestParam(value = "value") double value) {
        from = from.toLowerCase();
        to = to.toLowerCase();
        JsonObject response = new JsonObject();
        try {
            if (from.isEmpty())
                from = "meter";
            if (to.isEmpty())
                to = "meter";
            double convertedValue = value * (areaConversionRate.get(from) / areaConversionRate.get(to));
            response.addProperty("result", convertedValue);
            logger.info("SUCCESS: converted area: [ " + from + " , " + to + " , " + value + " -> " + convertedValue
                    + "]");
        } catch (Exception e) {
            logger.error("ERROR in area conversion: [ " + from + " , " + to + " , " + value + " ]", e.getCause());
            response.addProperty("result", "ERROR");
        }
        return response;
    }

    /**
     * Converts 7 units to one another using {@code meters per second} as base
     * conversion rate.
     * </p>
     * Available units are:
     * <ul>
     * <li>Meters Per Second</li>
     * <li>Centimeters Per Second</li>
     * <li>Kilometers Per Hour</li>
     * <li>Feet Per Second</li>
     * <li>Miles Per Hour</li>
     * <li>Knots</li>
     * <li>Mach (SI standard)</li>
     * </ul>
     * 
     * @param from  String containing {@code from} which unit conversion is.
     * @param to    String containing {@code to} which unit conversion is.
     * @param value to convert.
     * @return JSON object containing result of calculation.
     * @see #areaConversionRate
     */
    @RequestMapping(value = "/speed", method = RequestMethod.GET)
    JsonObject speedConverter(
            @RequestParam(value = "from") String from,
            @RequestParam(value = "to") String to,
            @RequestParam(value = "value") double value) {
        from = from.toLowerCase();
        to = to.toLowerCase();
        JsonObject response = new JsonObject();
        try {
            if (from.isEmpty())
                from = "mps";
            if (to.isEmpty())
                to = "mps";
            double convertedValue = value * (speedConversionRate.get(from) / speedConversionRate.get(to));
            response.addProperty("result", convertedValue);
            logger.info("SUCCESS: converted speed: [ " + from + " , " + to + " , " + value + " -> " + convertedValue
                    + "]");
        } catch (Exception e) {
            logger.error("ERROR in speed conversion: [ " + from + " , " + to + " , " + value + " ]", e.getCause());
            response.addProperty("result", "ERROR");
        }
        return response;
    }
}
