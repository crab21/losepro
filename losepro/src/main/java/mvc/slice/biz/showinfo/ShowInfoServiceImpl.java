package mvc.slice.biz.showinfo;


/**
 * @author
 * @create 2017-12-02 9:00 PM
 **/

import mvc.slice.pojo.paging.PageInfoBean;
import mvc.slice.pojo.BlogArticleInfo;
import mvc.slice.pojo.BlogBriefInfo;
import mvc.slice.pojo.BlogTypeInfo;
import mvc.slice.repository.SelectDeatilsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowInfoServiceImpl implements ShowInfoService {
    @Autowired
    SelectDeatilsInfo selectDeatilsInfo;

    /**
     * @param aid
     * @return
     */
    public BlogArticleInfo findBlogInfoDetails(String aid) {
        BlogArticleInfo blogInfoByAid = selectDeatilsInfo.findBlogInfoByAid(aid);
        return blogInfoByAid;

    }

    /**
     * @return
     */
    public List<BlogBriefInfo> findAllInfo(PageInfoBean pageInfoBean) {
        return selectDeatilsInfo.findAllInfoBrief(pageInfoBean);
    }

    /**
     * @return
     */
    public List<BlogTypeInfo> findAllArticleType() {
        return selectDeatilsInfo.findArticleType();
    }

    /**
     * @param artType
     * @return
     */
    public List<BlogBriefInfo> findOneTypeInfo(String artType, PageInfoBean pageInfoBean) {
        return selectDeatilsInfo.selectBlogBriefByOneType(artType, pageInfoBean);
    }

    /**
     *
     * @param pageInfoBean
     * @return
     */
    public PageInfoBean findPageInfos(PageInfoBean pageInfoBean) {
        int pageTotal = selectDeatilsInfo.selectBlogBriefPage();

        /*计算总页码*/
        if (pageTotal % pageInfoBean.getPageSize() == 0){
            pageInfoBean.setTotal(pageTotal/pageInfoBean.getPageSize());
        }else{
            pageInfoBean.setTotal(pageTotal/pageInfoBean.getPageSize()+1);
        }

        return pageInfoBean;
    }

    /**
     * 按时间顺序 倒序文章的最后十篇
     * @param countNumberMain
     * @return
     */
    public List<BlogBriefInfo> findBriefInfo(short countNumberMain) {
        return selectDeatilsInfo.selectBlogBriefInfoByTime(countNumberMain);
    }

    /**
     * 按照阅读量 倒叙文章的最后10篇
     * @param countNumberMain
     * @return
     */
    public List<BlogBriefInfo> findBriefInfoByEvalReadNumber(short countNumberMain) {
        return selectDeatilsInfo.selectBlogBriefInfoByEvalNum(countNumberMain);
    }
}
