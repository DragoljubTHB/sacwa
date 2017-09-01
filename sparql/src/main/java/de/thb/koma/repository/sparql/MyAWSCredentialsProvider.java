package de.thb.koma.repository.sparql;

import com.amazonaws.auth.AWSCredentials;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class MyAWSCredentialsProvider implements com.amazonaws.auth.AWSCredentialsProvider {

    private String credentials;
    public MyAWSCredentialsProvider(String credentials){
        this.credentials = credentials;
    }

    @Override
    public AWSCredentials getCredentials() { return new MyAWSCredentials("credentials.txt");
    }

    @Override
    public void refresh() {

    }

    private class MyAWSCredentials implements AWSCredentials {
        private String key;
        private String secret;

        MyAWSCredentials(String resourcesFile) {

        }

        private String getFile( String fileName) {

            StringBuilder result = new StringBuilder("");

            //Get file from resources folder
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource(fileName).getFile());

            try (Scanner scanner = new Scanner(file)) {

                String line = scanner.nextLine();
                setKey(line.substring(line.lastIndexOf(" ") + 1));
                line = scanner.nextLine();
                setSecret(line.substring(line.lastIndexOf(" ") + 1));

                scanner.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

            return result.toString();

        }



        @Override
        public String getAWSAccessKeyId() {
            return getKey();
        }

        @Override
        public String getAWSSecretKey() {
            return getSecret();
        }

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
