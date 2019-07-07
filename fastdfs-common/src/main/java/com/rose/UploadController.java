package com.rose;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
public class UploadController {

    @RequestMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) throws IOException {

        FastDFSFile fastDFSFile = new FastDFSFile();
        fastDFSFile.setName(file.getOriginalFilename());
        fastDFSFile.setExt(file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1));
        InputStream in = file.getInputStream();
        byte[] content = new byte[in.available()];
        in.read(content);
        in.close();
        fastDFSFile.setContent(content);
        String[] uploadResult = FastDFSClient.uploadFile(fastDFSFile);
        return JSONObject.toJSONString(uploadResult);
    }
}
