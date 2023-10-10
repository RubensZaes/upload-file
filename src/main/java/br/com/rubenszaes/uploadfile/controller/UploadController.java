package br.com.rubenszaes.uploadfile.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class UploadController {

    private Path pathRaiz = Paths.get("./uploads");

    @GetMapping("/")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("Index");
        return modelAndView;
    }
    @PostMapping("/upload")
    public ModelAndView uploadFile(MultipartFile multipartFile) {
        System.out.println(multipartFile);
        try {
            Files.createDirectories(pathRaiz);
            Files.copy(multipartFile.getInputStream(), pathRaiz.resolve(multipartFile.getOriginalFilename()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new ModelAndView("redirect:/");
    }

}
