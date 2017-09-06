package de.thb.lambda.feedback;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.jr.ob.JSON;

import java.io.IOException;
import java.util.Map;

public class Handler implements RequestHandler<RequestClass, String>{
    @Override
    public String handleRequest(RequestClass input, Context context) {
        LambdaLogger l = context.getLogger();
        String INPUT = input.toString();
        l.log("INPUT: " + INPUT);
        l.log("query "+input.getQuery());
        l.log("bucketKey "+input.getBucketKey());
        try {
            Map<String, Object> req = JSON.std.mapFrom(input.toString());
            req.forEach((k, v) -> l.log(k + "-->"+v.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input.toString();
    }
}
