package edu.convo;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;

@RestController
public class CustomExceptionResolver implements ErrorController {

    @RequestMapping("/error")
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public JsonObject handleGenericError() {
        JsonObject errorResponse = new JsonObject();
        errorResponse.addProperty("error", "Not found");
        errorResponse.addProperty("message", "The requested resource was not found");
        return errorResponse;
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
