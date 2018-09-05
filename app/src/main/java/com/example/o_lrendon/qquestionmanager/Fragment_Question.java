package com.example.o_lrendon.qquestionmanager;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class Fragment_Question extends Fragment implements View.OnClickListener
{
    private CoordinatorLayout clScreen;
    private RadioGroup rgAnswer;
    private Button btnAction;
    private TextView tvTextQuestion;

    //Datos bundle
    private int order , backgroudReosurce;
    private String textButton, textQuestion;
    private ArrayList<String> answerArrayList;

    //callback instance
    private MainActivity manager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_question, container, false);
        init(view);
        screenBuild();
        return view;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.btnAction:
                if(answerValid())
                {
                    String answer = answerGet();
                    SetValueGroup(answer);
                    manager.NextScreen();
                }
                break;
        }
    }

    public Fragment_Question()
    {
    }

    public Fragment_Question(final Bundle bundle, final MainActivity mainActivity)
    {
        if(bundle != null)
        {
            order = bundle.getInt("Order");
            backgroudReosurce = bundle.getInt("BackgroudReosurce");
            textQuestion = bundle.getString("TextQuestion");
            textButton = bundle.getString("TextButton");
            answerArrayList = bundle.getStringArrayList("AnswerArrayList");
        }
        this.manager = mainActivity;
    }

    private void init(final View view)
    {
        clScreen = view.findViewById(R.id.clScreen);
        rgAnswer = view.findViewById(R.id.rgAnswer);
        tvTextQuestion = view.findViewById(R.id.tvTextQuestion);
        btnAction = view.findViewById(R.id.btnAction);
        btnAction.setOnClickListener(this);
        rgAnswer.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(answerValid())
                {
                    String answer = answerGet();
                    SetValueGroup(answer);
                }
            }
        });
    }

    private void screenBuild()
    {
        buildAnswer();
        tvTextQuestion.setText(textQuestion);
        btnAction.setText(textButton);
        clScreen.setBackgroundResource(backgroudReosurce);
    }

    private void buildAnswer()
    {
        if(answerArrayList != null && answerArrayList.size() > 0) {
            rgAnswer.removeAllViewsInLayout();
            rgAnswer.removeAllViews();
            rgAnswer.setOrientation(LinearLayout.HORIZONTAL);
            rgAnswer.setGravity(Gravity.CENTER);
            for (final String textAnswer : answerArrayList)
            {
                final RadioButton radioButton = new RadioButton(getActivity());
                radioButton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                radioButton.setText(textAnswer);
                radioButton.setChecked(false);
                rgAnswer.addView(radioButton);
            }
        }
    }

    private boolean answerValid()
    {
        final int countChild = rgAnswer.getChildCount();
        int count = 0;
        while(count < countChild)
        {
            final RadioButton radioButton = (RadioButton)rgAnswer.getChildAt(count);
            if(radioButton.isChecked())
            {
                return true;
            }
            count++;
        }

        return false;
    }

    private String answerGet()
    {
        final int countChild = rgAnswer.getChildCount();
        String answer = "";
        int count = 0;
        while(count < countChild)
        {
            final RadioButton radioButton = (RadioButton)rgAnswer.getChildAt(count);
            if(radioButton.isChecked())
            {
                answer = radioButton.getText().toString();
            }
            count++;
        }
        return answer;
    }

    private void SetValueGroup(final String answer)
    {
        final int screenCurrent = manager.getCurrentScreen();
        manager.SetValueQuestion(screenCurrent, answer);
    }
}
