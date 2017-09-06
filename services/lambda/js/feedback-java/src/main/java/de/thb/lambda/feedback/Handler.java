package de.thb.lambda.feedback;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.jr.ob.JSON;

import java.io.IOException;
import java.util.Map;

public class Handler implements RequestHandler<Object, String>{
    @Override
    public String handleRequest(Object input, Context context) {
        context.getLogger().log(input.toString());
        try {
            Map<String, Object> req = JSON.std.mapFrom(input.toString());
            context.getLogger().log(req.get("body").toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input.toString();
    }
}
