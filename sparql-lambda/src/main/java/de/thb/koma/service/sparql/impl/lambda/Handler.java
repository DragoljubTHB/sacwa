package de.thb.koma.service.sparql.impl.lambda;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

/**
 * config:: 512 x ~ 18s first run
 */
public class Handler implements RequestHandler<OntologyKey, String> {
    String expectedEnv = "BUCKET";

    @Override
    public String handleRequest(OntologyKey input, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("handleRequest " + input);

        FusekiController ctrl = new FusekiController();
        ctrl.initServer();
        logger.log("server init");

        ctrl.uploadModelToServer(ctrl.getModelFrom(
                AmazonS3ClientBuilder.standard()
                        .withRegion(Regions.US_WEST_2)
                        .build()
                        .getObject(System.getenv(expectedEnv)
                                , input.getKey()).getObjectContent()));

        ctrl.stopServer();


        return "done ";
    }

}
