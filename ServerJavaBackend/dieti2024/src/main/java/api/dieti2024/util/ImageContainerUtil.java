package api.dieti2024.util;


import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class ImageContainerUtil {

    @Value("${azure.connectionString}")
    private  static String connectionString;

    @Value("${azure.containerName}")
    private  static String containerName;

    @Autowired
    private    BlobContainerClient blobContainerClient;

     public void uploadImage(MultipartFile file,String nomePath) throws IOException {

        // Get the image file name
        BlobClient blob = blobContainerClient.getBlobClient(nomePath);

        // Get a BlobClient to upload the image file
        blob.upload(file.getInputStream(),file.getSize(),true);

    }
}
