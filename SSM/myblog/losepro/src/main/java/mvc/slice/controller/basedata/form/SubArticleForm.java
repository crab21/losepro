package mvc.slice.controller.basedata.form;

import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 提交文章的页面传来的数据对象的封装
 *
 * @author
 * @create 2017-12-23 8:13 PM
 **/
@Component
public class SubArticleForm {
    /**
     * 文章的编号
     */
    @NotNull
    private String artId;
    /**
     * 文章的标题
     */

    private String artTitle;
    /**
     * 文章的详细信息
     */
    private String artDetaInfo;
    /**
     * 文章的类型
     */
    private String artType;

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

    public String getArtType() {
        return artType;
    }

    public void setArtType(String artType) {
        this.artType = artType;
    }
}


