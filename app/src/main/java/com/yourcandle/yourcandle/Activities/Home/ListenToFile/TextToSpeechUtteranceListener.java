package com.yourcandle.yourcandle.Activities.Home.ListenToFile;

import android.content.Context;

import android.speech.tts.UtteranceProgressListener;
import android.util.Log;

/**
 * Created by osama on 5/19/2018.
 */

public class TextToSpeechUtteranceListener extends UtteranceProgressListener {
    Context context;
    public TextToSpeechUtteranceListener(Context ctx)
    {
        context = ctx;
    }

    @Override
    public void onStart(String utteranceId) {
        // TODO Auto-generated method stub
        Log.e("HelloTextToSpeech", "Text To Speech Started ->" + utteranceId);
        //LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(START_PLAYING_BROADCAST));
    }
    @Override
    public void onDone(String utteranceId) {
        // TODO Auto-generated method stub
        Log.e("HelloTextToSpeech", "Text To Speech Done -> " + utteranceId);
    }
    
    @Override
    public void onError(String utteranceId) {
        // TODO Auto-generated method stub
        Log.e("HelloTextToSpeech", "Text To Speech ERROR -> " + utteranceId);
    }



}
