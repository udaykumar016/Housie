package uday.housie;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    SessonManager sm;
    ArrayList<GameModel> game_array;
    GameNumberAdapter adapter;
    RecyclerView game_rv;
    StaggeredGridLayoutManager mLayoutManager;
    private static final String TAG = "GameActivity";
    List<Integer> list,list2,list3;
    TextView game_last_no_tv;
    FloatingActionButton fab;
    TextToSpeech ttospeech;
    TextView game_ll_no;
    String all_no_str = "";
    int lastno;
    HorizontalScrollView hscr;
//    public static int lastno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        initData();

         fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(list.size() == 0){
                    Toast.makeText(GameActivity.this, "Game Completed. Press Restart.", Toast.LENGTH_SHORT).show();
                }else {
                    getc();
                }
            fab.setEnabled(false);

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        fab.setEnabled(true);
                    }
                },1*1000);

            }
        });


    }


    public void initData(){

        sm = new SessonManager(GameActivity.this);
        list = new ArrayList<Integer>();
        list2 = new ArrayList<Integer>();
        list3 = new ArrayList<Integer>();
        game_ll_no = (TextView)findViewById(R.id.game_ll_no);

        game_last_no_tv = (TextView)findViewById(R.id.game_last_no_tv);

        ttospeech = new TextToSpeech(GameActivity.this,new  TextToSpeech.OnInitListener(){
            @Override
            public void onInit(int i) {
                if(i != TextToSpeech.ERROR){
                    ttospeech.setLanguage(Locale.UK);

                }
            }
        });
        hscr = (HorizontalScrollView)findViewById(R.id.hscr);





        game_array = new ArrayList<GameModel>();
        mLayoutManager = new StaggeredGridLayoutManager(7, StaggeredGridLayoutManager.VERTICAL);
        game_rv = (RecyclerView)findViewById(R.id.game_rcv);
//
        game_rv.setLayoutManager(mLayoutManager);

        for (int i = 1; i <= 90; i++) {
            list.add(i);
            list2.add(i);
            list3.add(i);
            GameModel gamemodel = new GameModel();
            gamemodel.setNo(list.get(i-1));
            gamemodel.setId(String.valueOf(list.get(i-1)));
        }

        adapter = new GameNumberAdapter(GameActivity.this ,game_array,list,list2,list3,lastno);
        game_rv.setAdapter(adapter);
    }

    public void refreshCurrentActivity(View v){
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }
    public void getc(){

//        Log.e(TAG, "getc Before: "+list.toString() );
        Collections.shuffle(list);
//        Log.e(TAG, "getc After: "+list.toString() );

        try {
            GameModel gamemodel = new GameModel();
            gamemodel.setId(String.valueOf(list.get(0)));
            gamemodel.setNo(list.get(0));

//            Toast.makeText(GameActivity.this, "Number: "+list.get(0), Toast.LENGTH_SHORT).show();

            all_no_str = all_no_str+"   "+String.valueOf(list.get(0));


            game_ll_no.setText(all_no_str);

            hscr.postDelayed(new Runnable() {
                public void run() {
                    hscr.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
                }
            }, 100L);

            lastno = list.get(0);
//            ttospeech.speak(String.valueOf(list.get(0)), TextToSpeech.QUEUE_FLUSH, null);
            Log.e(TAG, "getc: "+sm.getPermStoreData(AppStrings.Session.KEY_SWITCH) );
            if(sm.getPermStoreData(AppStrings.Session.KEY_SWITCH).equals("1")){
                ttospeech.speak(String.valueOf(list.get(0)), TextToSpeech.QUEUE_FLUSH, null);
                Log.e(TAG, "getc: 2222222233333333333344444444444444444444444444" );
            }
            game_last_no_tv.setText(String.valueOf(list.get(0)));
            list2.remove(list.get(0)-1);
            list2.add(list.get(0)-1,0);

            gamemodel.setSelect(true);
            game_array.add(gamemodel);
            adapter.notifyDataSetChanged();
            list.remove(0);


//            ttospeech.speak(String.valueOf(list.get(0)), TextToSpeech.QUEUE_FLUSH, null);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onPause(){
        if(ttospeech !=null){
//            ttospeech.stop();
//            ttospeech.shutdown();
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        try {
//            if(ttospeech !=null){
//                ttospeech.stop();
//                ttospeech.shutdown();
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onResume();
    }

    public void settings(View v){
        Intent in = new Intent(GameActivity.this,SettingsActivity.class);
        startActivityForResult(in,1);
    }


    public int lastngame(){


        return lastno;
    }
}
