package com.astro.recipeapp.controller.files;

import com.astro.recipeapp.service.FilesService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@RequestMapping("/files/recipes")
public class RecipesFileController {

    private final FilesService filesService;

    public RecipesFileController(@Qualifier("FilesServiceRecipes") FilesService filesService) {
        this.filesService = filesService;
    }

    @GetMapping(value = "/export", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InputStreamResource> downloadFile() throws FileNotFoundException {
        File file = filesService.getDataFile();
        if (file.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\" Recipes.json\"")
                    .contentLength(file.length())
                    .body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadDataFile(@RequestParam MultipartFile webFile) {
        filesService.cleanDataFile();
        File localFile = filesService.getDataFile();
        try (FileOutputStream fos = new FileOutputStream(localFile)) {
            IOUtils.copy(webFile.getInputStream(),fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        try (BufferedInputStream bis = new BufferedInputStream(webFile.getInputStream());
//             FileOutputStream fos = new FileOutputStream(localFile);
//             BufferedOutputStream bos = new BufferedOutputStream(fos)) {
//            byte[] buffer = new byte[1024];
//            while (bis.read(buffer) > 0) {
//                bos.write(buffer);
//            }
//            return ResponseEntity.ok().build();
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

}
