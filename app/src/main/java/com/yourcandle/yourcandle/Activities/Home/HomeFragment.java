package com.yourcandle.yourcandle.Activities.Home;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yourcandle.yourcandle.Activities.Home.CapturePhoto.Main3Activity;
import com.yourcandle.yourcandle.Activities.Home.ListenToFile.Main2Activity;
import com.yourcandle.yourcandle.Activities.Home.ListenToFile.TextToSpeechManager;
import com.yourcandle.yourcandle.Activities.Home.SocialNetwork.Main1Activity;
import com.yourcandle.yourcandle.Activities.Home.VoiceRecord.Main4Activity;
import com.yourcandle.yourcandle.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
@BindView(R.id.socialView)
    ImageView socialView;
    @BindView(R.id.camView)
    ImageView camView;
    @BindView(R.id.recordView)
    ImageView recordView;
    @BindView(R.id.uploadView)
    ImageView uploadView;

    TextToSpeechManager ttsManager;
    String utteranceId=this.hashCode() + "";

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ttsManager = new TextToSpeechManager(getActivity());
        ttsManager.initializeTTS();

      View view=inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this,view);
        uploadView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ttsManager.speakMessage("listen to file",utteranceId);
                Intent intent = new Intent(getActivity(),Main2Activity.class);
                startActivity(intent);
            }
        });
        recordView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ttsManager.speakMessage("voice record",utteranceId);
                Intent intent = new Intent(getActivity(),Main4Activity.class);
                startActivity(intent);

            }
        });
        camView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ttsManager.speakMessage("capture photo",utteranceId);
                Intent intent = new Intent(getActivity(),Main3Activity.class);
                startActivity(intent);
            }
        });
        socialView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ttsManager.speakMessage("social network",utteranceId);
                Intent intent = new Intent(getActivity(),Main5Activity.class);
                startActivity(intent);
            }
        });

        return view;
    }

}
