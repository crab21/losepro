package mvc.slice.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import com.google.gson.Gson;
import mvc.slice.pojo.paging.PageInfoBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import mvc.slice.biz.showinfo.ShowInfoService;
import mvc.slice.pojo.BlogBriefInfo;

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

        PageInfoBean page = showInfoService.findPageInfos(pageInfoBean);
        model.addAttribute("blogBriefInfo", blogBriefInfo);
        model.addAttribute("page", page);
        return "show_more";
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
        Collections.sort(blogBriefInfo, new Comparator<BlogBriefInfo>() {

            public int compare(BlogBriefInfo o1, BlogBriefInfo o2) {
                if (o1.getId() > o2.getId()) {
                    return 1;
                }
                if (o1.getId() == o2.getId()) {
                    return 0;
                }
                return -1;
            }
        });
        PageInfoBean page = showInfoService.findPageInfos(pageInfoBean);
        model.addAttribute("blogBriefInfo", blogBriefInfo);
        model.addAttribute("page", page);
        return "show_more";
    }

    /**
     * @return
     */
    @RequestMapping("/showinfo")
    public String subInfoJsp() {
        return "show_subinfo";
    }
}


