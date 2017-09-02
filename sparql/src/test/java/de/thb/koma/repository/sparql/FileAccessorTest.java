package de.thb.koma.repository.sparql;

import org.apache.jena.fuseki.embedded.FusekiServer;
import org.apache.jena.query.DatasetAccessor;
import org.apache.jena.query.DatasetAccessorFactory;
import org.apache.jena.rdf.model.Model;
import org.junit.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileAccessorTest {
    @Test
    public void getFileHTTP() {
        FusekiServer server = FusekiServer.create()
                .setPort(3030)
                .build();
        server.start();
        DatasetAccessor du = DatasetAccessorFactory.createHTTP("http://localhost:3030/ds/data");
        Model m = du.getModel("https://s3-us-west-2.amazonaws.com/ontology.thb.de/koma-complex.owl");
        m.write(new PrintWriter(System.out));
        //m.write();
        server.stop();
    }

    @Test
    public void getCreds() {
        Cred cred = new Cred();
        String searchK = "aws_access_key_id", searchS = "aws_secret_access_key";
        Path path = FileSystems.getDefault().getPath("/home/dado/.aws", "credentials");
        try (Stream<String> lines = Files.lines(path)) {
            Set<String> collect = lines
                    .filter(s -> s.contains(searchK) || s.contains(searchS))
                    //.map(s -> s.substring(s.lastIndexOf(" ")+1))
                    .collect(Collectors.toSet());
            collect.forEach(System.out::println);
            //cred.setKey(collect.get(0)); cred.setSecret(collect.get(1));
            //System.out.println(cred.getKey()+ " "+cred.getSecret());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class Cred {
        private String key;
        private String secret;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getSecret() {
            return secret;
        }

        public void setSecret(String secret) {
            this.secret = secret;
        }
    }
}
