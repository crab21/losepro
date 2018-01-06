package mvc.slice.pojo;

import java.util.Date;

/**
 * 主要是类别和id的关联
 *
 * @author
 * @create 2017-12-22 12:22 AM
 **/
public class BlogTypeArticleInfo {

    /**
     * 编号
     */
    private int id;

    /**
     * 文章的类型标识
     */
    private String typeId;
    /**
     * 文章标识字段
     */
    private String artId;
    /**
     * 此条记录的创建时间
     */
    private Date creTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getArtId() {
        return artId;
    }

    public void setArtId(String artId) {
        this.artId = artId;
    }

    public Date getCreTime() {
        return creTime;
    }

    public void setCreTime(Date creTime) {
        this.creTime = creTime;
    }
}
