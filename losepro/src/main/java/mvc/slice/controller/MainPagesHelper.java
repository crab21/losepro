package mvc.slice.controller;

import mvc.slice.biz.showinfo.ShowInfoService;
import mvc.slice.common.ConstantNumber;
import mvc.slice.pojo.BlogBriefInfo;
import mvc.slice.pojo.paging.PageInfoBean;
import org.springframework.ui.Model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainPagesHelper {
    public String showmores(Model model, List<BlogBriefInfo> blogBriefInfo, PageInfoBean pageInfoBean, ShowInfoService showInfoService) {
        if (blogBriefInfo != null) {
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
        }else{
            return ConstantNumber.ERROR_PAGE;
        }
    }
}
