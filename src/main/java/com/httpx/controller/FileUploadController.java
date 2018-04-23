package com.httpx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.io.File;
import java.io.IOException;

@Controller
public class FileUploadController {

    @GetMapping(value = "/index")
    public  String index(){
        return "index";
    }


    @PostMapping(value = "/upload")
    public ModelAndView fileUpload(MultipartFile file){
        System.out.println("file="+file);
        ModelAndView mv = new ModelAndView();
        //定义json视图
        mv.setView(new MappingJackson2JsonView());
        //获取文件原始名
        String fileName = file.getOriginalFilename();
        File dest = new File(fileName);

        try {
            file.transferTo(dest);
            mv.addObject("success",true);
            mv.addObject("msg","上传成功");
        } catch (IOException e) {
            mv.addObject("success",false);
            mv.addObject("msg","上传失败");
            e.printStackTrace();
        }
        return mv;
    }
}
