package com.example.o_lrendon.qquestionmanager;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Questionary> questionaryList = new ArrayList<>();
    private ViewPager viewPager;
    private SparseArray sparseArrayQuestionBlueTelecom = new SparseArray();

    public MainActivity(){}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        loadQuestionary();
        SetupViewPager(viewPager, getSupportFragmentManager());
    }

    private void init()
    {
        viewPager = findViewById(R.id.viewpager);
    }

    private void loadQuestionary()
    {
        final ArrayList<String> answerList= new ArrayList<>();
        answerList.add("SI");
        answerList.add("NO");

        questionaryList.add(new Questionary(1,"¿Tu motocicleta necesita mantenimiento?", answerList, R.drawable.bg_asesorbtel_q1, "1"));
        questionaryList.add(new Questionary(2,"¿Tu motocicleta necesita balanceo?", answerList, R.drawable.bg_asesorbtel_q2, "2"));
        questionaryList.add(new Questionary(2,"¿Tu motocicleta necesita balanceo?", answerList, R.drawable.bg_asesorbtel_q2, "3"));
        questionaryList.add(new Questionary(2,"¿Tu motocicleta necesita balanceo?", answerList, R.drawable.bg_asesorbtel_q2, "4"));
        questionaryList.add(new Questionary(2,"¿Tu motocicleta necesita balanceo?", answerList, R.drawable.bg_asesorbtel_q2, "5"));
        questionaryList.add(new Questionary(2,"¿Tu motocicleta necesita balanceo?", answerList, R.drawable.bg_asesorbtel_q2, "6"));
        questionaryList.add(new Questionary(2,"¿Tu motocicleta necesita balanceo?", answerList, R.drawable.bg_asesorbtel_q2, "7"));
        questionaryList.add(new Questionary(2,"¿Tu motocicleta necesita balanceo?", answerList, R.drawable.bg_asesorbtel_q2, "8"));
        questionaryList.add(new Questionary(2,"¿Tu motocicleta necesita balanceo?", answerList, R.drawable.bg_asesorbtel_q2, "9"));
        questionaryList.add(new Questionary(2,"¿Tu motocicleta necesita balanceo?", answerList, R.drawable.bg_asesorbtel_q2, "10"));
        questionaryList.add(new Questionary(3,"¿Tu motocicleta necesita un enceramiento?", answerList, R.drawable.bg_asesorbtel_q3, "Finalizo"));

    }

    public void SetupViewPager(final ViewPager viewPager, FragmentManager fragmentManager)
    {
        final ViewPagerAdapter adapter = new ViewPagerAdapter(fragmentManager);

        for(final Questionary questionaryItem : questionaryList)
        {
            final Bundle bundleItem = new Bundle();
            bundleItem.putInt("Order", questionaryItem.getOrder());
            bundleItem.putInt("BackgroudReosurce", questionaryItem.getBackgroudReosurce());
            bundleItem.putString("TextButton", questionaryItem.getTextButton());
            bundleItem.putString("TextQuestion", questionaryItem.getQuestion());
            bundleItem.putStringArrayList("AnswerArrayList", questionaryItem.getAnswerList());
            adapter.addFragment(new Fragment_Question(bundleItem, this));
        }
        viewPager.setOffscreenPageLimit(questionaryList.size());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffPixels)
            {
            }
            @Override
            public void onPageSelected(int position)
            {
                if(!validQuestion(viewPager.getCurrentItem() - 1))
                {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
                }
            }
            @Override
            public void onPageScrollStateChanged(int state)
            {
            }
        });
    }

    public void NextScreen()
    {
        final int currentScreen = viewPager.getCurrentItem() + 1;
        if(currentScreen < viewPager.getOffscreenPageLimit())
        {
            viewPager.setCurrentItem(currentScreen);
        }
    }

    public int getCurrentScreen()
    {
       return viewPager.getCurrentItem();
    }

    public void SetValueQuestion(final int question, final String value)
    {
        if(validQuestion(question))
        {
            deleteQuestion(question);
        }
        sparseArrayQuestionBlueTelecom.put(question, value);
    }

    private boolean validQuestion(final int question)
    {
        return sparseArrayQuestionBlueTelecom != null && sparseArrayQuestionBlueTelecom.get(question) != null;
    }

    public void deleteQuestion(final int question)
    {
        sparseArrayQuestionBlueTelecom.delete(question);
    }
}



