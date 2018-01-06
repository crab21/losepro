package mvc.slice.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import mvc.slice.biz.showinfo.ShowInfoService;
import mvc.slice.pojo.BlogBriefInfo;

/**
 * Created by k on 11/1/17.
 */
@Controller
public class MainPages {

    /**
     *   注入service类 用于信息的展示
     */
    @Inject
    ShowInfoService showInfoService;

    /**
     * @param model
     *
     * @return
     */
    @RequestMapping("/welcome")
    public String index(Model model) {
        return "show_welcome";
    }

    /**
     * @param model
     *
     * @return
     */
    @RequestMapping("/showmore")
    public String showMore(Model model) {
        List<BlogBriefInfo> blogBriefInfo = showInfoService.findAllInfo();

        model.addAttribute("blogBriefInfo", blogBriefInfo);
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


