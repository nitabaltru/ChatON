package nitabaltru.tp2;

/**
 * Created by nitabaltru on 27/11/2017.
 */

public class Message {
    private String content;
    private String userName;
    private String userEmail;
    private Long timestamp;

    public Message() {
    }

    public Message(String content, String userName, String userEmail, Long timestamp)
    {
        this.content = content;
        this.userName = userName;
        this.timestamp = timestamp;
        this.userEmail = userEmail;
    }

    public String getContent() {
        return this.content;
    }

    public String getUserName() {
        return this.userName;
    }

    public Long getTimestamp() {
        return this.timestamp;
    }

    public String getUserEmail() {
        return this.userEmail;
    }
}
