package de.thb.koma.repository.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;


/**
 * Index the files in S3 as a TripleStore
 */
public class LambdaHandler implements RequestHandler<Object, String> {
    @Override
    public String handleRequest(Object o, Context context) {
        return null;
    }
}
