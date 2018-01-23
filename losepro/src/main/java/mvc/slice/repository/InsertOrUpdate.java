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
    @Transactional(transactionManager = "transactionManager")
    int insertBlogInfo(SubFormInfo blogInfo);

    @Transactional(transactionManager = "transactionManager")
    int addBlogTypeInfo(SubFormInfo subFormInfo);
}
