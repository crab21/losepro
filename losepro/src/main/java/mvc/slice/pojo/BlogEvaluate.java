package mvc.slice.pojo;

import java.util.Date;

/**
 * 文章的阅读数、赞赏数等
 *
 * @author
 * @create 2017-12-22 12:19 AM
 **/
public class BlogEvaluate {
    /**
     * 序号
     */
    private int id;
    /**
     * 此类的标识符
     */
    private String artId;
    /**
     * 文章的赞赏标识
     */
    private int evalThumbsUp;
    /**
     * 文章的阅读数量
     */
    private int evalReadNumber;
    /**
     * 文章的分享数量
     */
    private int evalShareNumber;
    /**
     * 此条数据的插入时间
     */
    private Date evalTime;

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

    public int getEvalThumbsUp() {
        return evalThumbsUp;
    }

    public void setEvalThumbsUp(int evalThumbsUp) {
        this.evalThumbsUp = evalThumbsUp;
    }

    public int getEvalReadNumber() {
        return evalReadNumber;
    }

    public void setEvalReadNumber(int evalReadNumber) {
        this.evalReadNumber = evalReadNumber;
    }

    public int getEvalShareNumber() {
        return evalShareNumber;
    }

    public void setEvalShareNumber(int evalShareNumber) {
        this.evalShareNumber = evalShareNumber;
    }

    public Date getEvalTime() {
        return evalTime;
    }

    public void setEvalTime(Date evalTime) {
        this.evalTime = evalTime;
    }
}
