package mvc.slice.biz.subInfo;

import mvc.slice.controller.basedata.inputInfo.SubFormInfo;
import mvc.slice.pojo.BlogArticleInfo;
import mvc.slice.repository.InsertOrUpdate;
import mvc.slice.repository.SelectDeatilsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author
 * @create 2017-12-09 3:06 PM
 **/
@Service
public class SubDeatilsInfoImpl implements SubDeatilsInfo {
    @Autowired
    InsertOrUpdate insertOrUpdate;
    @Autowired
    SelectDeatilsInfo selectDeatilsInfo;

    /**
     * 添加文章  添加文章分类 添加文章的阅读量等
     * 按照文章的类型进行分类
     *
     * @param subFormInfo
     * @return
     */
    public int addDetailsInfo(SubFormInfo subFormInfo) {
        /*向form中设置某些固定的属性值*/
        subFormInfo = new SubDeatilsInfoImplHelper().setAllInfo(subFormInfo);
        int insertBlogInfo = 0;

        /*先插入文章的类型  对应subFormInfo中的typeId*/
        int insertTypeResult = 0;
        int id = selectDeatilsInfo.selectBlogTypeId(subFormInfo);
        if (!StringUtils.isEmpty(String.valueOf(id))) {
            //当存在此类型时候  直接把id插入到subFormInfo中
            subFormInfo.setTypeId(id);
        } else {
//            artType不存在的情况
            insertTypeResult = insertOrUpdate.addBlogTypeInfo(subFormInfo);
        }

        if (insertTypeResult > 0) {
            /*根据是否插入成功决定进行文章的插入*/
            insertBlogInfo = insertOrUpdate.insertBlogInfo(subFormInfo);
        }
        return insertBlogInfo;
    }
}
