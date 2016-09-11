package uday.housie;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lakki on 09-Jul-16.
 */
public class GameNumberAdapter extends RecyclerView.Adapter<GameNumberAdapter.ViewHolder>{

    private static final String TAG = "GameNumberAdapter";
    ViewHolder viewHolder;
      ArrayList<GameModel> itemsData;
    Activity act;
    List<Integer> list1;
    List<Integer> list2;
    List<Integer> list3;
    int lastno;


    public GameNumberAdapter(Activity act, ArrayList<GameModel> itemsData,List list1,List list2,List list3,int lastno2){

        this.act = act;
        this.itemsData = itemsData;
        this.list1 = list1;
        this.list2 = list2;
        this.list3 = list3;
        this.lastno = lastno2;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemLayoutView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.row_game, parent, false);
        viewHolder = new ViewHolder(itemLayoutView);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GameNumberAdapter.ViewHolder holder, int position) {
//        GameModel gamemodel = itemsData.get(position);

        holder.game_no.setText(String.valueOf(list3.get(position)));
        holder.row_main_ll.setBackgroundColor(act.getResources().getColor(R.color.colorPrimary));

//        holder.game_no.setText(gamemodel.getNo());
        try {
//            Log.e(TAG, "onBindViewHolder: "+list1.get(position)+"---"+list2.get(position) );
//            Log.e(TAG, "onBindViewHolder: "+list2.toString() );

            for (int i = 0; i < list2.size(); i++) {

                if(list2.get(position) == 0 ){
                    holder.row_main_ll.setBackgroundColor(act.getResources().getColor(R.color.colorAccent));
                    zoomIneffetct(holder.game_no);
                    break;
                }
            }





        } catch (Exception e) {
            e.printStackTrace();
        }


//        Log.e(TAG, "onBindViewHolder: "+gamemodel.getNo()+"------"+gamemodel.getId() );

//        if(gamemodel.isSelect()){
//            holder.game_no.setBackgroundColor(act.getResources().getColor(R.color.colorAccent));
//        }else {
//            holder.game_no.setBackgroundColor(act.getResources().getColor(R.color.colorPrimary));
//        }
    }


    @Override
    public int getItemCount() {
        return list2.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView game_no;
        LinearLayout row_main_ll;
        public ViewHolder(View itemView) {
            super(itemView);
            game_no = (TextView) itemView.findViewById(R.id.row_game_tv);
            row_main_ll = (LinearLayout)itemView.findViewById(R.id.row_main_ll);

        }
    }

    public  void zoomIneffetct(TextView image)
    {
        ScaleAnimation scale = new ScaleAnimation(0, 1, 0, 1, ScaleAnimation.RELATIVE_TO_SELF, .5f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
        scale.setDuration(500);
        scale.setInterpolator(new OvershootInterpolator());
        image.startAnimation(scale);
    }


}
