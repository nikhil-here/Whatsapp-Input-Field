package com.application.whatsappinputfield;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.ChangeBounds;
import android.transition.Fade;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements TextWatcher {

    private ConstraintLayout clMessageContainer, clRootContainer;
    private EditText etMessage;
    private ImageButton ibCamera;
    private FloatingActionButton fabMic, fabSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initListeners();
        setTitle("WhatsApp Demo");
    }

    private void initListeners() {
        etMessage.addTextChangedListener(this);
    }

    private void initViews() {
        etMessage = findViewById(R.id.activity_main_et_message);
        fabMic = findViewById(R.id.activity_main_fab_mic);
        fabSend = findViewById(R.id.activity_main_fab_send);
        ibCamera = findViewById(R.id.activity_main_ib_camera);
        clRootContainer = findViewById(R.id.activity_main_cl_root_container);
        clMessageContainer = findViewById(R.id.activity_main_cl_message_container);
    }


    //--------------------EtMessage Text Change Listener starts  --------------------
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (charSequence.length() > 0) {
            if (!charSequence.toString().trim().isEmpty() && ibCamera.getVisibility() == View.VISIBLE) {
                TransitionManager.beginDelayedTransition(clRootContainer, new ChangeBounds());
                ibCamera.setVisibility(View.GONE);
                TransitionManager.beginDelayedTransition(clRootContainer, new Fade());
                fabMic.setVisibility(View.INVISIBLE);
                fabSend.setVisibility(View.VISIBLE);
            }

        } else {
            TransitionManager.beginDelayedTransition(clRootContainer, new ChangeBounds());
            ibCamera.setVisibility(View.VISIBLE);
            TransitionManager.beginDelayedTransition(clRootContainer, new Fade());
            fabMic.setVisibility(View.VISIBLE);
            fabSend.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
    //--------------------EtMessage Text Change Listener ends  --------------------


}