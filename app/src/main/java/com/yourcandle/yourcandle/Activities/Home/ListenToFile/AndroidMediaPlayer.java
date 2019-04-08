package com.yourcandle.yourcandle.Activities.Home.ListenToFile;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnSeekCompleteListener;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.yourcandle.yourcandle.R;

public class AndroidMediaPlayer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_media_player);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment()).commit();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */

    public static class PlaceholderFragment extends Fragment {

        Button btnStart, btnPause, btnStop, btnSeek;
        TextView textState, textDuration, textPosition;
        SeekBar timeBar;

        MediaPlayer mediaPlayer;

        private int stateMediaPlayer;
        private final int STATE_Idle = 0;
        private final int STATE_Initialized = 1;
        private final int STATE_Preparing = 2;
        private final int STATE_Prepared = 3;
        private final int STATE_Started = 4;
        private final int STATE_Paused = 5;
        private final int STATE_Stopped = 6;
        private final int STATE_PlaybackCompleted = 7;
        private final int STATE_End = 8;
        private final int STATE_Error = 9;

        PlayerTimeTask playerTimeTask;

        public PlaceholderFragment() {
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            // TODO Auto-generated method stub
            super.onCreate(savedInstanceState);
                initMediaPlayer();

            playerTimeTask = new PlayerTimeTask();
            playerTimeTask.execute();
        }

        @Override
        public void onDestroy() {

            playerTimeTask.setRunning(false);

            Toast.makeText(getActivity(), "release mediaPlayer", Toast.LENGTH_LONG).show();
            mediaPlayer.release();
            mediaPlayer = null;
            setPlayerState(STATE_End);

            super.onDestroy();
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container,
                    false);
            btnStart = (Button) rootView.findViewById(R.id.start);
            btnPause = (Button) rootView.findViewById(R.id.pause);
            btnStop = (Button) rootView.findViewById(R.id.stop);
            btnSeek = (Button) rootView.findViewById(R.id.seekto);

            btnStart.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {

                    if(stateMediaPlayer==STATE_Prepared
                            || stateMediaPlayer==STATE_Started
                            || stateMediaPlayer==STATE_Paused
                            || stateMediaPlayer==STATE_PlaybackCompleted){
                        mediaPlayer.start();
                        setPlayerState(STATE_Started);

                        displayDurationPosition();
                    }else{
                        Toast.makeText(getActivity(), "Play at Invalid state!", Toast.LENGTH_LONG).show();
                    }
                }
            });

            btnPause.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {

                    if(stateMediaPlayer==STATE_Started
                            || stateMediaPlayer==STATE_Paused
                            || stateMediaPlayer==STATE_Prepared //simulate a error case
                            || stateMediaPlayer==STATE_PlaybackCompleted){
                        mediaPlayer.pause();
                        setPlayerState(STATE_Paused);

                        displayDurationPosition();
                    }else{
                        Toast.makeText(getActivity(), "Pause at Invalid state!", Toast.LENGTH_LONG).show();
                    }
                }
            });

            btnStop.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {

                    if(stateMediaPlayer==STATE_Prepared
                            || stateMediaPlayer==STATE_Started
                            || stateMediaPlayer==STATE_Stopped
                            || stateMediaPlayer==STATE_Paused
                            || stateMediaPlayer==STATE_PlaybackCompleted){

                        //Stop
                        mediaPlayer.stop();
                        setPlayerState(STATE_Stopped);

                        //then parepare in background thread
                        mediaPlayer.prepareAsync();
                        setPlayerState(STATE_Preparing);

                        displayDurationPosition();
                    }else{
                        Toast.makeText(getActivity(), "Stop at Invalid state!", Toast.LENGTH_LONG).show();
                    }

                }
            });

            btnSeek.setOnClickListener(new OnClickListener(){

                @Override
                public void onClick(View v) {
                    if(stateMediaPlayer==STATE_Prepared
                            || stateMediaPlayer==STATE_Started
                            || stateMediaPlayer==STATE_Paused
                            || stateMediaPlayer==STATE_PlaybackCompleted){
                        mediaPlayer.seekTo(0);

                        displayDurationPosition();
                    }else{
                        Toast.makeText(getActivity(), "SeekTo at Invalid state!", Toast.LENGTH_LONG).show();
                    }
                }});

            timeBar = (SeekBar) rootView.findViewById(R.id.time);
            timeBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

                int targetPos = 0;
                boolean rqsSeek = false;

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                    if(rqsSeek){

                        if(mediaPlayer!=null){
                            if(stateMediaPlayer==STATE_Prepared
                                    || stateMediaPlayer==STATE_Started
                                    || stateMediaPlayer==STATE_Paused
                                    || stateMediaPlayer==STATE_PlaybackCompleted){

                                mediaPlayer.seekTo(targetPos);
                            }
                        }

                        rqsSeek = false;
                    }

                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {}

                @Override
                public void onProgressChanged(SeekBar seekBar, int progress,
                                              boolean fromUser) {

                    if(fromUser){
                        rqsSeek = true;

                        if(mediaPlayer!=null){
                            float seekToPercentage = (float)progress/100.0f;
                            targetPos = (int)(mediaPlayer.getDuration() * seekToPercentage);
                        }
                    }

                }
            });

            textState = (TextView) rootView.findViewById(R.id.state);
            textState.setText(getPlayerState());

            textDuration = (TextView) rootView.findViewById(R.id.duration);
            textPosition = (TextView) rootView.findViewById(R.id.position);
            displayDurationPosition();

            textDuration.setOnClickListener(new OnClickListener(){

                @Override
                public void onClick(View v) {
                    displayDurationPosition();
                }});

            textPosition.setOnClickListener(new OnClickListener(){

                @Override
                public void onClick(View v) {
                    displayDurationPosition();
                }});

            return rootView;
        }

        private void displayDurationPosition(){
            textDuration.setText(
                    "Duration: " + mediaPlayer.getDuration() + " ms");
            textPosition.setText(
                    "Current Position: " + mediaPlayer.getCurrentPosition() + " ms");
        }
        private void initMediaPlayer() {

            Toast.makeText(getActivity(), "initMediaPlayer()", Toast.LENGTH_LONG).show();
            String path = getActivity().getIntent().getStringExtra("PATH");
            mediaPlayer = MediaPlayer.create(getActivity(),Uri.parse(path));


            setPlayerState(STATE_Prepared);

            mediaPlayer.setOnPreparedListener(new OnPreparedListener(){

                @Override
                public void onPrepared(MediaPlayer mp) {
                    setPlayerState(STATE_Prepared);
                    displayDurationPosition();
                }});

            mediaPlayer.setOnCompletionListener(new OnCompletionListener(){

                @Override
                public void onCompletion(MediaPlayer mp) {
                    setPlayerState(STATE_PlaybackCompleted);
                    displayDurationPosition();
                }});

            //Handle Error
            mediaPlayer.setOnErrorListener(new OnErrorListener(){

                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {

                    setPlayerState(STATE_Error);

                    String errorWhat;
                    switch(what){
                        case MediaPlayer.MEDIA_ERROR_UNKNOWN:
                            errorWhat = "MEDIA_ERROR_UNKNOWN";
                            break;
                        case MediaPlayer.MEDIA_ERROR_SERVER_DIED:
                            errorWhat = "MEDIA_ERROR_SERVER_DIED";
                            break;
                        default:
                            errorWhat = "!";
                    }

                    String errorExtra;
                    switch(extra){
                        case MediaPlayer.MEDIA_ERROR_IO:
                            errorExtra = "MEDIA_ERROR_IO";
                            break;
                        case MediaPlayer.MEDIA_ERROR_MALFORMED:
                            errorExtra = "MEDIA_ERROR_MALFORMED";
                            break;
                        case MediaPlayer.MEDIA_ERROR_UNSUPPORTED:
                            errorExtra = "MEDIA_ERROR_UNSUPPORTED";
                            break;
                        case MediaPlayer.MEDIA_ERROR_TIMED_OUT:
                            errorExtra = "MEDIA_ERROR_TIMED_OUT";
                            break;
                        default:
                            errorExtra = "!";
                    }

                    Toast.makeText(getActivity(),
                            "Error" + "\n"
                                    + errorWhat + "\n"
                                    + errorExtra,
                            Toast.LENGTH_LONG).show();

                    //release
                    mp.release();

                        initMediaPlayer();


                    return true;
                }});

            mediaPlayer.setOnSeekCompleteListener(new OnSeekCompleteListener() {

                @Override
                public void onSeekComplete(MediaPlayer mp) {
                    Toast.makeText(getActivity(),
                            "OnSeekComplete: " + mp.getCurrentPosition(),
                            Toast.LENGTH_SHORT).show();

                }
            });
        }

        private void setPlayerState(int st){
            stateMediaPlayer = st;

            String stringState = getPlayerState();
            if(textState!=null){
                textState.setText(stringState);
            }else{
                Toast.makeText(getActivity(),
                        stringState, Toast.LENGTH_LONG).show();
            }

        }

        private String getPlayerState(){
            String strSt;
            switch(stateMediaPlayer){
                case STATE_Idle:
                    strSt = "Idle";
                    break;
                case STATE_Initialized:
                    strSt = "Initialized";
                    break;
                case STATE_Preparing:
                    strSt = "Preparing";
                    break;
                case STATE_Prepared:
                    strSt = "Prepared";
                    break;
                case STATE_Started:
                    strSt = "Started";
                    break;
                case STATE_Paused:
                    strSt = "Paused";
                    break;
                case STATE_Stopped:
                    strSt = "Stopped";
                    break;
                case STATE_PlaybackCompleted:
                    strSt = "PlaybackCompleted";
                    break;
                case STATE_End:
                    strSt = "End";
                    break;
                case STATE_Error:
                    strSt = "Error";
                    break;
                default:
                    strSt = "unknown...";
            }
            return strSt;
        }

        public class PlayerTimeTask extends AsyncTask<Void, Void, Void> {

            boolean running;

            public PlayerTimeTask() {
                running = true;
            }

            public void setRunning(boolean r){
                running = r;
            }

            @Override
            protected Void doInBackground(Void... params) {
                while(running){
                    SystemClock.sleep(250);
                    publishProgress();
                }
                return null;
            }

            @Override
            protected void onProgressUpdate(Void... values) {
                if(timeBar!=null){

                    if(mediaPlayer!=null){

                        if(stateMediaPlayer == STATE_Prepared
                                || stateMediaPlayer == STATE_Started
                                || stateMediaPlayer == STATE_Paused
                                || stateMediaPlayer == STATE_Stopped
                                || stateMediaPlayer == STATE_PlaybackCompleted){
                            int cur = mediaPlayer.getCurrentPosition();
                            int dur = mediaPlayer.getDuration();
                            int timePercentage = 100 * cur/dur;
                            timeBar.setProgress(timePercentage);
                        }

                        textPosition.setText(
                                "Current Position: "
                                        + mediaPlayer.getCurrentPosition() + " ms");
                    }

                }
            }


        }
    }

}
