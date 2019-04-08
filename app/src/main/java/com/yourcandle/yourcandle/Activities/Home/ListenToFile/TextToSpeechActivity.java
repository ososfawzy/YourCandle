package com.yourcandle.yourcandle.Activities.Home.ListenToFile;

import android.content.Intent;
import android.os.AsyncTask;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.yourcandle.yourcandle.R;

import java.io.File;

public class TextToSpeechActivity extends AppCompatActivity {

    TextToSpeechManager ttsManager;
    String utteranceId=this.hashCode() + "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_to_speech);

        final String text = getIntent().getStringExtra("PARSEDTEXT");
        final String pdfName = getIntent().getStringExtra("PDFNAME");
        File root = android.os.Environment.getExternalStorageDirectory();
        final File file = new File(root.getAbsolutePath()+"/AudioFiles/"+pdfName.split("\\.")[0]+".mp3");
        final String path = String.valueOf(file);

        ttsManager = new TextToSpeechManager(this);
        ttsManager.initializeTTS();

        Button read = (Button)findViewById(R.id.button);
        final Button play = (Button)findViewById(R.id.play);

        if(read!=null)
        {
            read.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new speak().execute((Void[])null);
                    ttsManager.speak(text, file, utteranceId);
                    play.setEnabled(true);
                }
            });
        }

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TextToSpeechActivity.this,AndroidMediaPlayer.class);
                intent.putExtra("PATH",path);
                startActivity(intent);
            }
        });

    }

    private class speak extends AsyncTask<Void,Void,Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(getApplicationContext(),"Text To Speech Started, Please wait...",Toast.LENGTH_LONG).show();
            ttsManager.speakMessage("Text To Speech Started, Please wait...",utteranceId);
        }
        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            ttsManager.speakMessage("Text To Speech Done",utteranceId);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == TextToSpeechManager.MY_TTS_CHECK_CODE) {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                // success, create the TTS instance
                ttsManager.initializeTTS();
            } else {
                // missing data, install it
                Intent installIntent = new Intent();
                installIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installIntent);
            }
        }
    }

    @Override
    protected void onDestroy ()
    {
        super.onDestroy();
        ttsManager.onDestroy();
    }

}
