package com.code.mallservice.mall.controller;

import com.code.mallservice.mall.entity.ImageEntity;
import com.code.mallservice.mall.service.IImageService;
import com.code.mallservice.mall.utils.Result;
import com.code.mallservice.mall.uuid.UuidUtils;
import org.jcodec.api.FrameGrab;
import org.jcodec.common.model.Picture;
import org.jcodec.scale.AWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

@RestController
public class ImgController {
    @Value("${image.upload.path}")
    private String uploadPath;

    @Value("${image.upload.baseurl}")
    private String domainPath;

    @Autowired
    private IImageService iImageService;

    /**
     * 图片上传
     *
     * @return
     */
    @RequestMapping("/upload")
    public Result upload(MultipartFile file) {
        InputStream in =null;
        OutputStream out =null;
        try {
            String fileName = file.getOriginalFilename();
            String s = fileName.substring(fileName.indexOf("."));
            String newFileName = UuidUtils.compressedUuid() + s;
            File img = new File(uploadPath+File.separator+newFileName);
            if(img.exists()){
                img.delete();
            }
            in = file.getInputStream();
            int item;
            out = new FileOutputStream(img);
            while ((item = in.read()) != -1){
                out.write(item);
            }
            ImageEntity entity = new ImageEntity();
            entity.setUrl(domainPath+File.separator+newFileName);
            if((".mp4").equals(s.toLowerCase().trim())){
                Picture picture = FrameGrab.getFrameFromFile(img,2);
                BufferedImage bufferedImage = AWTUtil.toBufferedImage(picture);
                String imageName = UUID.randomUUID().toString().replace("_","")+".png";
                File img1 = new File(uploadPath+File.separator+imageName);
                ImageIO.write(bufferedImage,"png",img1);
                entity.setMin_url(domainPath+File.separator+imageName);
            }
            iImageService.add(entity);
            return Result.ok(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(in != null)in.close();
            }catch (Exception e){}
            try {
                if(out != null) out.close();
            }catch (Exception e){}
        }
        return Result.error();
    }
}
