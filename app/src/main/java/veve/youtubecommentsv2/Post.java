package veve.youtubecommentsv2;

/**
 * Created by Russell on 7/18/2016.
 */
public class Post {
    private static String uniquePostIdentifier;
    private static String posterID;
    private String youtubeVideoLink;
    private String videoDescription;
    private boolean NSFW;
    private String date;
    private int votes;

    public Post(String youtubeVideoLink, String videoDescription, boolean NSFW, String date, int votes) {
        this.youtubeVideoLink = youtubeVideoLink;
        this.videoDescription = videoDescription;
        this.NSFW = NSFW;
        this.date = date;
        this.votes = votes;
    }

    public static String getUniquePostIdentifier() {
        return uniquePostIdentifier;
    }

    public static void setUniquePostIdentifier(String uniquePostIdentifier) {
        Post.uniquePostIdentifier = uniquePostIdentifier;
    }

    public static String getPosterID() {
        return posterID;
    }

    public static void setPosterID(String posterID) {
        Post.posterID = posterID;
    }

    public String getYoutubeVideoLink() {
        return youtubeVideoLink;
    }

    public void setYoutubeVideoLink(String youtubeVideoLink) {
        this.youtubeVideoLink = youtubeVideoLink;
    }

    public String getVideoDescription() {
        return videoDescription;
    }

    public void setVideoDescription(String videoDescription) {
        this.videoDescription = videoDescription;
    }

    public boolean isNSFW() {
        return NSFW;
    }

    public void setNSFW(boolean NSFW) {
        this.NSFW = NSFW;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }
}
