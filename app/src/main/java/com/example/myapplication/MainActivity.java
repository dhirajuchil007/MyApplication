package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements DemoAdapter.OnItemClicked{
    ArrayList<DemoData> demoDataList=new ArrayList<>();
    RecyclerView dataList;
    public static final String TAG="MainActivity";
    TextView equity,fixedValue,point;
    View view;
    ValueAnimator blueToOrange,orngeToBlue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataList=findViewById(R.id.dataList);
        equity=findViewById(R.id.equity_tv);
        fixedValue=findViewById(R.id.fixedValue_tv);
        point=findViewById(R.id.point_text);
        view=findViewById(R.id.back_view);






        DemoAdapter demoAdapter=new DemoAdapter();
        demoAdapter.setOnClick(this);

        dataList.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        getDataFromServer(demoAdapter);

        int colorFrom = getResources().getColor(R.color.orange);
        int colorTo = getResources().getColor(R.color.blueDark);
        blueToOrange=ValueAnimator.ofObject(new ArgbEvaluator(),colorTo,colorFrom);
        blueToOrange.setDuration(300);
        blueToOrange.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                view.setBackgroundColor((int) animation.getAnimatedValue());
            }
        });

        orngeToBlue=ValueAnimator.ofObject(new ArgbEvaluator(),colorFrom,colorTo);
        orngeToBlue.setDuration(300);
        orngeToBlue.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                view.setBackgroundColor((int) animation.getAnimatedValue());
            }
        });


        




    }

    private void getDataFromServer(final DemoAdapter demoAdapter) {
        Retrofit retrofit=new Retrofit.Builder().baseUrl("http://demo0312305.mockable.io/").addConverterFactory(GsonConverterFactory.create()).build();
        CRApi crApi=retrofit.create(CRApi.class);

        Call<List<DemoData>> call=crApi.getData();
        call.enqueue(new Callback<List<DemoData>>() {
            @Override
            public void onResponse(Call<List<DemoData>> call, Response<List<DemoData>> response) {
                Log.d(TAG, "onResponse: "+response.body().get(0).date);
                for(DemoData d:response.body())
                {
                    demoDataList.add(d);
                    demoAdapter.setData(demoDataList,MainActivity.this);
                    dataList.setAdapter(demoAdapter);

                }

            }

            @Override
            public void onFailure(Call<List<DemoData>> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getMessage());

            }
        });


    }


    @Override
    public void onItemClick(int position) {

        equity.setText(String.valueOf(demoDataList.get(position).equity)+"%");
        fixedValue.setText(String.valueOf(100-demoDataList.get(position).equity)+"%");
        point.setText(demoDataList.get(position).point);
        if(demoDataList.get(position).equity<=50)
        {
            blueToOrange.start();
        }
        else {
            orngeToBlue.start();

        }

    }



}
