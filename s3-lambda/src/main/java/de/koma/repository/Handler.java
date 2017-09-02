package de.koma.repository;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

import java.io.*;
import java.util.StringJoiner;

/**
 * Config:: 512MB x 10''
 * first run ~ 7''
 * secon run ~ .3''
 */
public class Handler implements RequestHandler<Object, String> {
    String baseUrl = "https://s3.amazonaws.com";
    @Override
    public String handleRequest(Object input, Context context) {
        context.getLogger().log("intput: "+input);
        context.getLogger().log("env: "+input);
        context.getClientContext().getEnvironment().get("BUCKET");
        AmazonS3 amazonS3 = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.US_WEST_2)
                .build();
        context.getLogger().log("builded "
                +amazonS3.doesBucketExist("ontology.thb.de"));

        S3Object s3Object = amazonS3
                .getObject("ontology.thb.de", "koma-complex.owl");

        context.getLogger().log("loaded object");

        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        s3Object.getObjectContent())))
        {
            for(int i = 0; i < 10; i++){
                context.getLogger().log(br.readLine());
            }
        }
        catch (IOException e) {
            context.getLogger().log(e.getMessage());
        }
        return "done";
    }
}
