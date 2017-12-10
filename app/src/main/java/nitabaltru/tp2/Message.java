package nitabaltru.tp2;

/**
 * A classe representing a message and all its information.
 * Created by nitabaltru on 27/11/2017.
 */

class Message {
    /**
     * the message itself
     */
    private String content;

    /**
     * the name of the user
     */
    private String userName;

    /**
     * the email of the user
     */
    private String userEmail;

    /**
     * The timestamp (when the message has been sent)
     */
    private Long timestamp;

    /**
     * the constructor of a message
     * @param content the message
     * @param userName the user's name
     * @param userEmail the user's email
     * @param timestamp the timestamp
     */
    Message(String content, String userName, String userEmail, Long timestamp)
    {
        this.content = content;
        this.userName = userName;
        this.timestamp = timestamp;
        this.userEmail = userEmail;
    }

    /**
     * get the content of the message
     * @return String
     */
    String getContent() {
        return this.content;
    }

    /**
     * get the user name
     * @return String
     */
    String getUserName() {
        return this.userName;
    }

    /**
     * get the timestamp
     * @return Long
     */
    Long getTimestamp() {
        return this.timestamp;
    }

    /**
     * get the user email
     * @return String
     */
    String getUserEmail() {
        return this.userEmail;
    }
}
