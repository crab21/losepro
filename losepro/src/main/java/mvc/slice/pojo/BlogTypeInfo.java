package mvc.slice.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 主要是对文章进行分类
 *
 * @author
 * @create 2017-12-22 12:18 AM
 **/
public class BlogTypeInfo implements Serializable {

    /**
     * 编号
     */
    private int id;

    /**
     * 属于何种类型  数据从前台页面传过来
     */
    private String artType;
    /**
     * 创建的时间
     */
    private Date creTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArtType() {
        return artType;
    }

    public void setArtType(String artType) {
        this.artType = artType;
    }

    public Date getCreTime() {
        return creTime;
    }

    public void setCreTime(Date creTime) {
        this.creTime = creTime;
    }
}
