package com.yourcandle.yourcandle.Activities.Home.VoiceRecord;

/**
 * Created by osama on 5/10/2018.
 */

public class Recording {

    String Uri, fileName;
    boolean isPlaying = false;

    public Recording(String uri, String fileName, boolean isPlaying) {
        Uri = uri;
        this.fileName = fileName;
        this.isPlaying = isPlaying;
    }

    public String getUri() {
        return Uri;
    }

    public String getFileName() {
        return fileName;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing){
        this.isPlaying = playing;
    }
}

