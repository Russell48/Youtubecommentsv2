package veve.youtubecommentsv2;

/**
 * Created by Russell on 7/19/2016.
 */
public class Notification {
    private String uniqueNoticeID;
    private String notificationText;

    public Notification(String uniqueNoticeID, String notificationText) {
        this.uniqueNoticeID = uniqueNoticeID;
        this.notificationText = notificationText;
    }

    public String getUniqueNoticeID() {
        return uniqueNoticeID;
    }

    public void setUniqueNoticeID(String uniqueNoticeID) {
        this.uniqueNoticeID = uniqueNoticeID;
    }

    public String getNotificationText() {
        return notificationText;
    }

    public void setNotificationText(String notificationText) {
        this.notificationText = notificationText;
    }
}
