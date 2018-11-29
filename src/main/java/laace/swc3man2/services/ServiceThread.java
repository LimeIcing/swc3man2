package laace.swc3man2.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import laace.swc3man2.models.ModelInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class ServiceThread implements Runnable {
    private JpaRepository repository;
    private TypeReference typeReference;
    private URL url;

    public ServiceThread(JpaRepository repository, TypeReference typeReference, String urlSuffix) {
        this.repository = repository;
        this.typeReference = typeReference;

        try {
            this.url = new URL("http://18.185.40.91/" + urlSuffix);
        } catch (MalformedURLException mURLE) {
            mURLE.printStackTrace();
        }
    }

    @Override
    public void run() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<ModelInterface> models;
        InputStream inputStream;

        //while (true) {
            try {
                inputStream = url.openStream();
                models = objectMapper.readValue(inputStream, typeReference);
                if (repository == null) {
                    System.out.println("repo is null");
                } else {
                    repository.saveAll(models); // this is the line that throws a NullPointerException
                    models.clear();
                }
            } catch (IOException iOE) {
                iOE.printStackTrace();
            }
/*
            try {
                Thread.sleep(3600000);
            } catch (InterruptedException iE) {
                iE.printStackTrace();
            }
        }*/
    }
}
