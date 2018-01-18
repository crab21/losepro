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
    public List<BlogBriefInfo> findOneTypeInfo(String artType) {
        return selectDeatilsInfo.selectBlogBriefByOneType(artType);
    }

    public PageInfoBean findPageInfos() {
        int pageTotal = selectDeatilsInfo.selectBlogBriefPage();
        PageInfoBean pageInfoBean = new PageInfoBean();
        if (pageTotal % pageInfoBean.getPageSize() == 0){
            pageInfoBean.setTotal(pageTotal/pageInfoBean.getPageSize());
        }else{
            pageInfoBean.setTotal(pageTotal/pageInfoBean.getPageSize()+1);
        }

        return pageInfoBean;
    }
}
