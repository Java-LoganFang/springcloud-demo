package cn.com.itoken.common.constants;

/**
 * http状态码
 * 枚举类
 */
public enum HttpStatusConstants {
    BAD_GATWAY(502, "从上游服务器接收到无效响应");

    private int status;
    private String content;

    private HttpStatusConstants(int status, String content) {
        this.status = status;
        this.content = content;
    }

    public int getStatus() {
        return status;
    }

    public String getContent() {
        return content;
    }
}
