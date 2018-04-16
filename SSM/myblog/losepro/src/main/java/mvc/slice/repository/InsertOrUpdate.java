package mvc.slice.repository;

import mvc.slice.controller.basedata.inputInfo.SubFormInfo;
import mvc.slice.pojo.BlogArticleInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 主要是数据库的写操作
 *
 * @author
 * @create 2017-12-09 2:55 PM
 **/
@Repository
public interface InsertOrUpdate {

    /**
     * 插入文章信息和相对应的类型信息
     *
     * [table: blog_articleinfo
     *         blog_typearticle]
     * @param blogInfo
     * @return
     */
    @Transactional(transactionManager = "transactionManager")
    int insertBlogInfo(SubFormInfo blogInfo);

    /**
     * 插入文章所对应的类型信息
     *
     * [table: blog_type]
     *
     * @param subFormInfo
     * @return
     */
    @Transactional(transactionManager = "transactionManager")
    int addBlogTypeInfo(SubFormInfo subFormInfo);
}
