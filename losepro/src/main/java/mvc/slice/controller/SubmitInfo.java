package mvc.slice.controller;

import com.google.gson.Gson;
import mvc.slice.biz.subInfo.SubDeatilsInfo;
import mvc.slice.controller.basedata.form.SubArticleForm;
import mvc.slice.controller.basedata.inputInfo.SubFormInfo;
import org.apache.commons.io.FileUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 */
@Controller
@RequestMapping("/sub")
public class SubmitInfo {
    /**
     * 主要是用于类映射
     */
    private static DozerBeanMapper beanMapper = new DozerBeanMapper();

    @Inject
    SubDeatilsInfo subDeatilsInfo;


    /**
     * @param subArticleForm
     * @param
     * @return
     */
    @RequestMapping(value = "/subinfo", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
    public @ResponseBody
    String subinfo(@ModelAttribute SubArticleForm subArticleForm) {

        //数据库存储数据的操作和其他相关调用
        SubFormInfo subFormInfo = beanMapper.map(subArticleForm, SubFormInfo.class);

        int i = subDeatilsInfo.addDetailsInfo(subFormInfo);
        String flag = "";
        if (i > 0) {
            flag = "保存成功";
        } else {
            flag = "保存失败";
        }
        return new Gson().toJson(flag);
    }
    @RequestMapping(value = "/subImageFile", method = {RequestMethod.POST,RequestMethod.GET},produces = "text/html;charset=UTF-8")
    public @ResponseBody
    String Fileupload(@RequestParam("editormd-image-file") MultipartFile multipartFile){
        File file = new File("f:"+File.separator+multipartFile.getOriginalFilename());
        try {
            multipartFile.transferTo(file);

        } catch (IOException e) {
            e.printStackTrace();
        }

        Map map = new HashMap();
        map.put("success","1");
        map.put("message","save file in error");
        map.put("url",file.getAbsolutePath());


        return new Gson().toJson(map);
    }

}
