package de.thb.koma.service.sparql.jena;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.Map;

public class Handler implements RequestHandler<RequestClass, String> {

    private final String ENV_BUCKET = "BUCKET";
    private final String INPUT_KEY_BUCKET_KEY = "bucketKey";
    private final String INPUT_KEY_QUERY = "query";

    private Map<String, Object> mappedInput;
    private RequestClass request;
    private LambdaLogger l;

    @Override
    public String handleRequest(RequestClass input, Context context) {
        l = context.getLogger();
        request = input;
        return new Controller(System.getenv(ENV_BUCKET), Regions.US_WEST_2.getName())
                .executeQuery(request.getQuery(), request.getBucketKey());
    }

}
