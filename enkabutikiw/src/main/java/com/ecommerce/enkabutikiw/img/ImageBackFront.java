package com.ecommerce.enkabutikiw.img;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ImageBackFront {
    public static String localhost = "http://10.0.2.2/";
    public static String serveruser = localhost + "photo/";

    public static String userLocation = "C:/xampp/htdocs/photo/";

    public static String save(MultipartFile file, String fileName) {
        String src = "";
        String server = "";
        String location = "";

        location = userLocation;
        server = serveruser;

//        if (folderName.equals("solution")) {
//            location = solutionLocation;
//            server = serverSolution;
//        }else if
//        (folderName.equals("challenge")) {
//            location = challengeLocation;
//            server = serverchallenge;
//        } else {
//            location = userLocation;
//            server = serveruser;
//        }

        try {
            Path filePath = Paths.get(location + fileName);

            if (!Files.exists(filePath)) {
                Files.createDirectories(filePath.getParent());
                Files.copy(file.getInputStream(), filePath);
                src = server + fileName;
            } else {
                Files.delete(filePath);
                Files.copy(file.getInputStream(), filePath);
                src = server + fileName;
            }
        } catch (IOException e) {
            e.printStackTrace();
            src = null;
        }

        return src;
    }
}
