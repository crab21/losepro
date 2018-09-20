package fileloadlearn;

import org.apache.commons.fileupload.ProgressListener;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

/**
 * Created by k on 2018/9/20.
 */
@Component
public class FileUploadProcessListener implements ProgressListener {

    private HttpSession httpSession;

    public void setSession(HttpSession session) {
        this.httpSession = session;
        Progress progress = new Progress();
        session.setAttribute("status", progress);
    }

    @Override
    public void update(long bytesRead, long contentLength, int items) {
        System.out.println(bytesRead);

        System.out.println(contentLength);

        Progress status = (Progress) httpSession.getAttribute("status");
        status.setBytesRead(bytesRead);
        status.setContentLength(contentLength);
        status.setItems(items);
    }
}
