package kr.hs.dgsw.web01blog.Controller;


import kr.hs.dgsw.web01blog.Domain.Post;
import kr.hs.dgsw.web01blog.Protocol.AttachmentProtocol;
import kr.hs.dgsw.web01blog.Service.PostService;
import kr.hs.dgsw.web01blog.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
import java.util.UUID;

@RestController
public class AttachmentController {
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;

    @PostMapping("/attachment")
    public AttachmentProtocol upload(@RequestPart MultipartFile file) {
        String destFilename = "D:/Web_Code/20190326/upload/"
                + UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

        try {
            File destfile = new File(destFilename);
            destfile.getParentFile().mkdirs();
            file.transferTo(destfile);
            return new AttachmentProtocol(destFilename, file.getOriginalFilename());
        } catch (IOException ex) {
            return null;
        }
    }

    @GetMapping("/attachment/{type}/{id}")
    public void download(@PathVariable String type, @PathVariable String id, HttpServletRequest request, HttpServletResponse response){
        try{
            String filename;
            String filepath;

            if(type.equals("post")){
                Post post = this.postService.FindPost(Long.parseLong(id));
                filepath = post.getFilepath();
                filename = post.getFilename();
            }else {
                return;
            }

            File file = new File(filepath);
            if(file.exists() == false) return;

            String mimeType = URLConnection.guessContentTypeFromName(file.getName());
            if(mimeType == null) mimeType = "application/octet-stream";

            response.setContentType(mimeType);
            response.setHeader("Content-Disposition","inline: filename=\"" + filename + "\"");
            response.setContentLength((int)file.length());

            InputStream is = new BufferedInputStream(new FileInputStream(file));
            FileCopyUtils.copy(is,response.getOutputStream());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
