import org.jsoup.nodes.Document;

public class Page {

    private byte[] content;
    private String html;
    private Document doc;
    private String charset;
    private String url;
    private String contentType;

    public Page(byte[] content, String html, Document doc, String charset, String url, String contentType) {
        this.content = content;
        this.html = html;
        this.doc = doc;
        this.charset = charset;
        this.url = url;
        this.contentType = contentType;
    }

    public Page() {

    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public Document getDoc() {
        return doc;
    }

    public void setDoc(Document doc) {
        this.doc = doc;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
