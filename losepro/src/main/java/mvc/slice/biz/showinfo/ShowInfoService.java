package mvc.slice.biz.showinfo;

import mvc.slice.pojo.BlogArticleInfo;
import mvc.slice.pojo.BlogBriefInfo;
import mvc.slice.pojo.BlogTypeInfo;

import java.util.List;

public interface ShowInfoService {
    /**
     * @param aid
     * @return
     */
    BlogArticleInfo findBlogInfoDetails(String aid);

    /**
     * @return
     */
    List<BlogBriefInfo> findAllInfo();

    /**
     *
     * @return
     */
    List<BlogTypeInfo> findAllArticleType();

    /**
     *
     * @param artType
     * @return
     */
    List<BlogBriefInfo> findOneTypeInfo(String artType);
}
