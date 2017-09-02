package de.thb.koma.repository.sparql;

import com.amazonaws.auth.AWSCredentials;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.stream.Stream;

public class MyAWSCredentialsProvider implements com.amazonaws.auth.AWSCredentialsProvider {

    private String credentials;

    public MyAWSCredentialsProvider(String credentialsFile) {
        this.credentials = credentialsFile;
    }

    @Override
    public AWSCredentials getCredentials() {
        return new MyAWSCredentials(credentials);
    }

    @Override
    public void refresh() {

    }

    private class MyAWSCredentials implements AWSCredentials {
        private String key;
        private String secret;

        MyAWSCredentials(String resourcesFile) {
            init(resourcesFile);
        }

        private void init(String fileName) {


            StringBuilder result = new StringBuilder("");
            File file = new File(fileName);

            try (Scanner scanner = new Scanner(file)) {

                String line;
                boolean k = false, s = false; //
                while (!k || !s) {
                    line = scanner.nextLine();
                    if (line.contains("aws_access_key_id")) {
                        setKey(line.substring(line.lastIndexOf(" ") + 1));
                        k = true;
                    }
                    if (line.contains("aws_secret_access_key")) {
                        setSecret(line.substring(line.lastIndexOf(" ") + 1));
                        s = true;
                    }
                }

                scanner.close();


            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        @Override
        public String getAWSAccessKeyId() {
            return getSecret();
        }

        @Override
        public String getAWSSecretKey() {
            return getKey();
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
