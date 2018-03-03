package mvc.slice.repository;

import mvc.slice.controller.basedata.inputInfo.SubFormInfo;
import mvc.slice.pojo.BlogArticleInfo;
import mvc.slice.pojo.BlogBriefInfo;
import mvc.slice.pojo.BlogTypeInfo;
import mvc.slice.pojo.paging.PageInfoBean;
import org.apache.ibatis.annotations.Param;
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
     *
     * [table: blog_articleinfo]
     * @param aid
     * @return
     */

    BlogArticleInfo findBlogInfoByAid(String aid);

    /**
     * 获取文章的摘要信息
     *
     * [table: blog_brief]
     * @return
     */
    List<BlogBriefInfo> findAllInfoBrief(PageInfoBean pageInfoBean);

    /**
     * 获取所有文章类型  用于展示主页用
     *
     * [table: blog_type]
     * @return
     */
    List<BlogTypeInfo> findArticleType();

    /**
     * 通过文章的类型，查询属于某一类的所有信息
     * BlogTypeInfo-->artType
     *
     * [table: blog_brief
     *         blog_type]
     * @param artType
     * @return
     */
    List<BlogBriefInfo> selectBlogBriefByOneType(@Param("artType")String artType, @Param("pageInfoBean")PageInfoBean pageInfoBean);

    /**
     * 查找文章id
     *
     * [table: blog_type]
     * @param subFormInfo
     * @return
     */
    BlogTypeInfo selectBlogTypeId(SubFormInfo subFormInfo);

    /**
     *  查询所有数据的总个数
     *
     *  [table: blog_brief]
     * @return
     */
    int selectBlogBriefPage();

    /**
     * 通过限制查出来数据的大小
     *
     * [table: blog_brief]
     * @param countNumberMain
     * @return
     */
    List<BlogBriefInfo> selectBlogBriefInfoByTime(short countNumberMain);

    /**
     * 通过阅读量 倒叙查询数据
     *
     * [table: blog_evaluate
     *         blog_brief]
     * @param countNumberMain
     * @return
     */
    List<BlogBriefInfo> selectBlogBriefInfoByEvalNum(short countNumberMain);

    /**
     * 通过类型查询总个数
     *
     * [table: blog_typearticle
     *         blog_type
     *         blog_brief]
     * @param artType
     * @return
     */
    int selectBlogBriefByArtType(String artType);
}
