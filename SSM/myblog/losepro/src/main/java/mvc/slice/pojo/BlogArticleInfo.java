package mvc.slice.pojo;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wpy
 *
 *
 */
@Component
public class BlogArticleInfo implements Serializable {
    /**
     *  序号
     */
    private int id;
    /**
     *  文章的编号
     */
    private String artId;
    /**
     *  文章的标题
     */
    private String artTitle;
    /**
     *  文章的详细信息
     */
    private String artDetaInfo;
    /**
     *  文章的添加时间
     */
    private Date artTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArtId() {
        return artId;
    }

    public void setArtId(String artId) {
        this.artId = artId;
    }

    public String getArtTitle() {
        return artTitle;
    }

    public void setArtTitle(String artTitle) {
        this.artTitle = artTitle;
    }

    public String getArtDetaInfo() {
        return artDetaInfo;
    }

    public void setArtDetaInfo(String artDetaInfo) {
        this.artDetaInfo = artDetaInfo;
    }

    public Date getArtTime() {
        return artTime;
    }

    public void setArtTime(Date artTime) {
        this.artTime = artTime;
    }
}
