package mvc.slice.biz.showinfo;

import mvc.slice.pojo.paging.PageInfoBean;
import mvc.slice.pojo.BlogArticleInfo;
import mvc.slice.pojo.BlogBriefInfo;
import mvc.slice.pojo.BlogTypeInfo;

import java.util.List;

public interface ShowInfoService {

    BlogArticleInfo findBlogInfoDetails(String aid);


    List<BlogBriefInfo> findAllInfo(PageInfoBean pageInfoBean);


    List<BlogTypeInfo> findAllArticleType();


    List<BlogBriefInfo> findOneTypeInfo(String artType, PageInfoBean pageInfoBean);


    PageInfoBean findPageInfos(PageInfoBean pageInfoBean);


    List<BlogBriefInfo> findBriefInfo(short countNumberMain);
}
