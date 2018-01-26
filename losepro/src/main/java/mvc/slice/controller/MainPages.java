package mvc.slice.controller;

import mvc.slice.biz.showinfo.ShowInfoService;
import mvc.slice.common.ConstantNumber;
import mvc.slice.pojo.BlogBriefInfo;
import mvc.slice.pojo.paging.PageInfoBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by k on 11/1/17.
 */
@Controller
public class MainPages {

    @ModelAttribute
    public PageInfoBean setPageInfoBean() {
        return new PageInfoBean();
    }

    /**
     * 注入service类 用于信息的展示
     */
    @Inject
    ShowInfoService showInfoService;

    /**
     * @param model
     * @return
     */
    @RequestMapping("/welcome")
    public String index(Model model) {
        List<BlogBriefInfo> briefInfo = showInfoService.findBriefInfo(ConstantNumber.COUNT_NUMBER_MAIN);
        model.addAttribute("briefInfo",briefInfo);
        return "show_welcome";
    }

    /**
     * 首页
     *
     * @param model
     * @return
     */
    @RequestMapping("/showmore")
    public String showMore(Model model) {
        PageInfoBean pageInfoBean = new PageInfoBean();
        pageInfoBean.setShowSize(6);
        List<BlogBriefInfo> blogBriefInfo = showInfoService.findAllInfo(pageInfoBean);

        //todo 判空放在helper中进行
        if (blogBriefInfo != null) {
            PageInfoBean page = showInfoService.findPageInfos(pageInfoBean);
            model.addAttribute("blogBriefInfo", blogBriefInfo);
            model.addAttribute("page", page);
            return "show_more";
        } else {
            return ConstantNumber.ERROR_PAGE;
        }
    }

    /**
     * 分页
     *
     * @param model
     * @param pageInfoBean
     * @return
     */
    @RequestMapping(value = "/showmore", params = "pageFlag")
    public String showmores(Model model, @ModelAttribute PageInfoBean pageInfoBean) {
        //todo 分页中动态展示的页数
        pageInfoBean.setShowSize(6);
        List<BlogBriefInfo> blogBriefInfo = showInfoService.findAllInfo(pageInfoBean);

        return new MainPagesHelper().showmores(model, blogBriefInfo, pageInfoBean, showInfoService);
    }

    /**
     * @return
     */
    @RequestMapping("/showinfo")
    public String subInfoJsp() {
        return "show_subinfo";
    }
}


