package com.nibado.project.pi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@SpringBootApplication
public class PIApplication {
    public static final String HOSTNAME = hostname();

    public static void main(String[] args) {
        SpringApplication.run(PIApplication.class, args);
    }

    public static String hostname() {
        InputStream ins;

        try {
            ins = Runtime.getRuntime().exec("hostname").getInputStream();
        } catch (IOException e) {
            return "unknown";
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(ins))) {
            return reader.readLine().trim();
        } catch (IOException e) {
            return "unknown";
        }
    }
}
