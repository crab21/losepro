package fileloadlearn;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by k on 2018/9/20.
 */
@RestController
public class FileController {

    @RequestMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) {
        System.out.println(".......................");
        if (file.isEmpty()) {
            return "文件为空";
        }
        String originalFilename = file.getOriginalFilename();
        String filepath = null;
        try {
            filepath = new File("").getCanonicalPath() + "/temp/uploadFile";
            System.out.println(filepath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String targetPath = filepath + System.currentTimeMillis() + originalFilename;
        File file1 = new File(targetPath);
        if (!file1.getParentFile().exists()) {
            file1.getParentFile().mkdirs();
        }

        try {
            file.transferTo(file1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return originalFilename;
    }

}
