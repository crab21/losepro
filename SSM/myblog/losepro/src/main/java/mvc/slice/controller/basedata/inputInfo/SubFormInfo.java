package mvc.slice.controller.basedata.inputInfo;

/**
 * 表单提交时候   包含封装在内的数据
 *
 * @author
 * @create 2017-12-23 8:37 PM
 **/
public class SubFormInfo {
    /**
     * 序号
     */
    private int id;
    /**
     * 类型ID
     */
    private int typeId;
    /**
     * 文章的编号
     */
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
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

    public String getArtType() {
        return artType;
    }

    public void setArtType(String artType) {
        this.artType = artType;
    }
}
