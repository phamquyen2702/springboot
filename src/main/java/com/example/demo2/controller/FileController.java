package com.example.demo2.controller;

import com.example.demo2.dto.response.UploadFileResponse;
import com.example.demo2.model.Product;
import com.example.demo2.model.User;
import com.example.demo2.service.IProductService;
import com.example.demo2.service.IUserService;
import com.example.demo2.service.impl.FileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class FileController {
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);
    @Autowired
    FileStorageService fileStorageService;

    @Autowired
    IUserService iUserService;

    @Autowired
    IProductService iProductService;

    @PostMapping("/updateAvatar")
    public ResponseEntity<UploadFileResponse> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("id") String id) {
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/downloadFile/")
                .path(fileName)
                .toUriString();

        if (!iUserService.existsById(id)) {
            throw new RuntimeException("Not found user by Id, please try with other id!");
        }
        User user = iUserService.getUserById(id);
        user.setAvatar(fileDownloadUri);
        iUserService.save(user);
        return new ResponseEntity<>(new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize()), HttpStatus.OK);
    }

    @PostMapping("/updateImagesProduct/{id}")
    public ResponseEntity<List<UploadFileResponse>> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files, @PathVariable("id") Long id) {
        List<UploadFileResponse> uploadFileResponses = new ArrayList<>();
        List<String> imageStrings = new ArrayList<>();
        for (MultipartFile file : files) {
            String fileName = fileStorageService.storeFile(file);
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/api/downloadFile/")
                    .path(fileName)
                    .toUriString();
            imageStrings.add(fileDownloadUri);
            uploadFileResponses.add(new UploadFileResponse(fileName, fileDownloadUri,
                    file.getContentType(), file.getSize()));
        }
        if (!iProductService.existsById(id)) {
            throw new RuntimeException("Not found product by Id, please try with other id!");
        }
        Product product = iProductService.findProductById(id);
        product.setImages(imageStrings.toString());
        iProductService.save(product);
        return new ResponseEntity<>(uploadFileResponses, HttpStatus.OK);
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
