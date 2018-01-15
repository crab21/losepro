package mvc.slice.controller;

import com.google.gson.Gson;
import mvc.slice.biz.subInfo.SubDeatilsInfo;
import mvc.slice.controller.basedata.form.SubArticleForm;
import mvc.slice.controller.basedata.inputInfo.SubFormInfo;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;


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
     * @return
     */
    @ModelAttribute
    public SubArticleForm setBlogArticleInfo() {
        return new SubArticleForm();
    }

    /**
     * @param SubArticleForm
     * @param model
     * @return
     */
    @RequestMapping(value = "/subinfo", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
    public @ResponseBody
    String subinfo(@ModelAttribute SubArticleForm SubArticleForm, Model model) {
        //数据库存储数据的操作和其他相关调用
        SubFormInfo subFormInfo = beanMapper.map(SubArticleForm, SubFormInfo.class);

        System.out.println(SubArticleForm.getArtDetaInfo() + "-------" + SubArticleForm.getArtTitle());
        int i = subDeatilsInfo.addDetailsInfo(subFormInfo);
        String flag = "";
        if (i > 0) {
            flag = "保存成功";
        } else {
            flag = "保存失败";
        }
        return new Gson().toJson(flag);
    }
}
