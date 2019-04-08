package com.yourcandle.yourcandle.Activities.Home.CapturePhoto;

import android.content.Context;
import android.speech.tts.TextToSpeech;

import java.util.Locale;


public class Speech {
    private static TextToSpeech tts;
    private static String S_str;

    public void Talk(Context context, String str) {
        S_str = str;
        tts = new TextToSpeech(ResultsActivity.getContext(), new TextToSpeech.OnInitListener() {

            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    tts.setLanguage(Locale.getDefault());
                    tts.setPitch(1.3f);
                    tts.setSpeechRate(1f);
                    //   tts.speak(SC_str, TextToSpeech.QUEUE_FLUSH, null,null);
                    tts.speak(S_str, TextToSpeech.QUEUE_FLUSH, null);
                }
            }
        });
    }
}

