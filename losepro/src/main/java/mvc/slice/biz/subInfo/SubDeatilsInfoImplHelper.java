package mvc.slice.biz.subInfo;

import mvc.slice.controller.basedata.form.SubArticleForm;
import mvc.slice.controller.basedata.inputInfo.SubFormInfo;

import java.util.UUID;


public class SubDeatilsInfoImplHelper {
    /**
     *
     * @param subFormInfo
     * @return
     */
    public SubFormInfo setAllInfo(SubFormInfo subFormInfo) {
        String uuidResult = UUID.randomUUID().toString().replace("-", "");

        subFormInfo.setArtId(uuidResult);
        subFormInfo.setId(0);
        return subFormInfo;
    }
}
