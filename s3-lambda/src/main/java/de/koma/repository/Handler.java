package de.koma.repository;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

import java.io.*;
import java.util.StringJoiner;

public class Handler implements RequestHandler<Object, String> {

    @Override
    public String handleRequest(Object input, Context context) {
        context.getLogger().log("start");
        context.getLogger().log("intput: "+input);
        S3Object s3Object = AmazonS3ClientBuilder.standard()
                .build().getObject("ontology.thb.de", "koma-complex.owl");

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
