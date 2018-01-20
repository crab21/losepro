package mvc.slice.repository;

import mvc.slice.controller.basedata.inputInfo.SubFormInfo;
import mvc.slice.pojo.paging.PageInfoBean;
import mvc.slice.pojo.BlogArticleInfo;
import mvc.slice.pojo.BlogBriefInfo;
import mvc.slice.pojo.BlogTypeInfo;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 主要是数据库的读取操作
 *
 * @author
 * @create 2017-12-09 2:51 PM
 **/
@Repository
@Transactional
public interface SelectDeatilsInfo {
    /**
     * 通过id来查找文章
     * @param aid
     * @return
     */
    @Transactional
    BlogArticleInfo findBlogInfoByAid(String aid);

    /**
     * 获取文章的摘要信息
     * @return
     */
    @Transactional
    List<BlogBriefInfo> findAllInfoBrief(PageInfoBean pageInfoBean);

    /**
     * 获取所有文章类型  用于展示主页用
     * @return
     */
    List<BlogTypeInfo> findArticleType();

    /**
     * 通过文章的类型，查询属于某一类的所有信息
     * BlogTypeInfo-->artType
     * @param artType
     * @return
     */
    List<BlogBriefInfo> selectBlogBriefByOneType(String artType);

    /**
     * 查找文章id
     * @param subFormInfo
     * @return
     */
    BlogTypeInfo selectBlogTypeId(SubFormInfo subFormInfo);

    /**
     *
     * @return
     */
    int selectBlogBriefPage();
}
