package mvc.slice.controller;


import com.google.gson.Gson;
import mvc.slice.biz.showinfo.ShowInfoService;
import mvc.slice.pojo.BlogArticleInfo;
import mvc.slice.pojo.BlogBriefInfo;
import mvc.slice.pojo.BlogTypeInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/show")
public class ShowInfo {
    @Inject
    ShowInfoService showInfoService;

    /**
     * @param articleId
     * @param model
     * @return
     * @throws SQLException
     */
    @RequestMapping("/{articleId}")
    public String showOne(@PathVariable String articleId, Model model) {

        model.addAttribute("articleId", articleId);
        return "show/show_one";
    }


    /**
     * 通过artId查询某篇文章
     * [ajax]
     *
     * @param articleId
     * @return
     * @throws SQLException
     */
    @RequestMapping(value = "/{articleId}", params = "artId", produces = "text/html;charset=utf-8")
    public @ResponseBody
    String showOneFind(@PathVariable String articleId) {
        BlogArticleInfo blogInfoDetails = showInfoService.findBlogInfoDetails(articleId);
        return new Gson().toJson(blogInfoDetails);
    }

    /**
     * 查找所有的类型 用户文章头部
     * [ajax]
     *
     * @param artId
     * @return
     */
    @RequestMapping(value = "/findType", params = "artId", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
    public @ResponseBody
    String findAllType(@RequestParam String artId) {
        if (!StringUtils.isEmpty(artId)) {
            List<BlogTypeInfo> allType = showInfoService.findAllArticleType();
            return new Gson().toJson(allType);
        } else {
            return new Gson().toJson("no");
        }
    }

    /**
     * 通过选择的类型，查找所有此类下面的内容
     *
     * @param artType
     * @param model
     * @return
     */
    @RequestMapping(value = "/findType", params = "artType", method = {RequestMethod.GET})
    public String findTypeById(@RequestParam("artType") String artType, Model model) {
        List<BlogBriefInfo> blogBriefInfo = showInfoService.findOneTypeInfo(artType);
        model.addAttribute("blogBriefInfo", blogBriefInfo);
        return "show_more";
    }
}
