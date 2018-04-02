package mvc.slice.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章的简要信息记录
 *
 * @author
 * @create 2017-12-09 4:40 PM
 **/
public class BlogBriefInfo implements Serializable {
    /**
     *  序号
     */
    private int id;
    /**
     *  文章的标识
     */
    private String artId;
    /**
     *  文章的标题
     */
    private String artTitle;
    /**
     *  文章的简要  取文章的详细信息的前20个字符
     */
    private String artBrief;
    /**
     *  摘要的创建时间
     */
    private Date artTime;

    public BlogBriefInfo() {
    }

    public BlogBriefInfo(int id, String artId, String artTitle, String artBrief, Date artTime) {
        this.id = id;
        this.artId = artId;
        this.artTitle = artTitle;
        this.artBrief = artBrief;
        this.artTime = artTime;
    }

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

    public String getArtBrief() {
        return artBrief;
    }

    public void setArtBrief(String artBrief) {
        this.artBrief = artBrief;
    }

    public Date getArtTime() {
        return artTime;
    }

    public void setArtTime(Date artTime) {
        this.artTime = artTime;
    }
}
