package de.thb.koma.repository.sparql;

import org.junit.Test;

public class GetS3ObjectTest {
    private String bucketName = "ontology.thb.de";
    private String key = "koma-complex.owl";

    @Test
    public void getObject(){
        getObjectFromS3("/home/dado/.aws/credentials", bucketName, key);
    }

    private void getObjectFromS3(String credentials, String bucket, String file){
        GetObject accessor = new GetObject(credentials);
        accessor.get(bucket, file);
    }
}
