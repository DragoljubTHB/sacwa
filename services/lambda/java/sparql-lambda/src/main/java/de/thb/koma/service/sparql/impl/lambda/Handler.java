package de.thb.koma.service.sparql.impl.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import de.thb.koma.repository.sparql.SparqlFuseki;

public class Handler implements RequestHandler<String, String> {

    @Override
    public String handleRequest(String input, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("handleRequest");
        SparqlFuseki fuseki = new SparqlFuseki();
        fuseki.server();
        logger.log("server() end");

        return "done ";
    }
}
