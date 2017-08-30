package de.thb.koma.service.sparql.impl.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class Handler implements RequestHandler<Object, String> {

    @Override
    public String handleRequest(Object input, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("handleRequest "+input);
        SparqlFuseki fuseki = new SparqlFuseki();
        fuseki.server();
        logger.log("server() end");

        return "done ";
    }
}
