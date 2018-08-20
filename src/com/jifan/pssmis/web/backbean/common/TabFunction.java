package com.jifan.pssmis.web.backbean.common;

public class TabFunction implements java.io.Serializable {

private static final long serialVersionUID = -1106669148515861952L;
private String title;
private String url;
private String functionCode;
private boolean active;
public String getTitle() {
    return title;
}
public void setTitle(String title) {
    this.title = title;
}
public String getUrl() {
    return url;
}
public void setUrl(String url) {
    this.url = url;
}
public String getFunctionCode() {
    return functionCode;
}
public void setFunctionCode(String functionCode) {
    this.functionCode = functionCode;
}
public boolean isActive() {
    return active;
}
public void setActive(boolean active) {
    this.active = active;
}

}
