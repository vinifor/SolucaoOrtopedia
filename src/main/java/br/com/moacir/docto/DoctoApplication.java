package br.com.moacir.docto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class DoctoApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(DoctoApplication.class, args);
    }

}
