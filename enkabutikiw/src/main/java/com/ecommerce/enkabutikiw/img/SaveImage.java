package com.ecommerce.enkabutikiw.img;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import org.springframework.web.multipart.MultipartFile;

public class SaveImage {

    public static String localhost = "http://127.0.0.1/";
    public static String serveruser = localhost + "Images/";

    public static String Userlocation = "C:/xampp/htdocs/Images/";

    public static String save(MultipartFile file, String nomFichier) {
        String src = "";
        String server = "";
        String location = "";

        location = Userlocation;
        server = serveruser;


        /// debut de l'enregistrement
        try {
            Path chemin = Paths.get(location + nomFichier);

            if (!Files.exists(chemin)) {
                Files.createDirectories(chemin.getParent());
                Files.copy(file.getInputStream(), chemin);
                src = server + nomFichier;
            } else {
                Files.delete(chemin);
                Files.copy(file.getInputStream(), chemin);
                src = server + nomFichier;
            }
            /*int index = Objects.requireNonNull(file.getOriginalFilename()).lastIndexOf(".");

            Path chemin = Paths.get(location);
            if (!Files.exists(chemin)) {
                // si le fichier n'existe pas deja
                Files.createDirectories(chemin);
                Files.copy(file.getInputStream(), chemin
                        .resolve(nomFichier + file.getOriginalFilename()+file.getOriginalFilename().substring(index).toLowerCase()));
                src = server + nomFichier
                        + file.getOriginalFilename()+ file.getOriginalFilename().substring(index).toLowerCase();
            } else {
                // si le fichier existe pas deja
                String newPath = location + nomFichier +file.getOriginalFilename()
                        + file.getOriginalFilename().substring(index).toLowerCase();
                Path newchemin = Paths.get(newPath);
                if (!Files.exists(newchemin)) {
                    // si le fichier n'existe pas deja
                    Files.copy(file.getInputStream(), chemin
                            .resolve(
                                    nomFichier +file.getOriginalFilename()+ file.getOriginalFilename().substring(index).toLowerCase()));
                    src = server + nomFichier +file.getOriginalFilename()
                            + file.getOriginalFilename().substring(index).toLowerCase();
                } else {
                    // si le fichier existe pas deja on le suprime et le recrèe

                    Files.delete(newchemin);

                    Files.copy(file.getInputStream(), chemin
                            .resolve(
                                    nomFichier + file.getOriginalFilename()+ file.getOriginalFilename().substring(index).toLowerCase()));
                    src = server + nomFichier
                            +file.getOriginalFilename()+ file.getOriginalFilename().substring(index).toLowerCase();
                }
                }*/
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            // TODO: handle exception
            src = null;
        }

        return src;
    }
}
