package com.example.retrofit_review;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.retrofit_review.AirData.AirData;
import com.example.retrofit_review.AirData.Item;
import com.example.retrofit_review.AirData.Response;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;


public class MainActivity extends AppCompatActivity {

    public static final String TAG = "로그";
    private List<Item> mItem;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        Simple 라이브러리가 deprecated 됐으나 다른 대안책이 없음
        JAXB 는 안드로이드에서 사용 불가
        이 프로젝트는 Retrofit 의 복습이기 때문에 Retrofit 을 사용해야만 했음.
        처음 해맸던 점
        AirData 클래스를 만들고 AirData 에 XML 데이터를 넣으면 될 줄 알았는데
        데이터가 들어갈 수 없다는 에러가 계속 떠서 멘붕
        생각해보니 Response 클래스를 넣으면 될 문제였음
        Response 클래스를 넣고 돌려보니
        Element 'item' is already used with @org.simpleframework 라는 에러발생
        구글링으로 @Element(name = "item", required = false, inLine=true) 을 넣으라는 답변을 보고 적용
        완료.
        */
        mRecyclerView = findViewById(R.id.recycler_view_bb);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        new RetrofitInit().openApiService.getData("서울", "recency").enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: 성공");
                    mItem = response.body().getBody().getItems().getItem();
                    Log.d(TAG, "onResponse: " + mItem.toString());
                    MyAdapter myAdapter = new MyAdapter(mItem) ;
                    mRecyclerView.setAdapter(myAdapter);
                } else {
                    Log.d(TAG, "onResponse: 실패");
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Log.d(TAG, "onFailure: 에러" + t.getMessage());

            }
        });

    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MYVH> {
        List<Item> mItem;

        public MyAdapter(List<Item> mItem) {
            this.mItem = mItem;
        }

        @NonNull
        @Override
        public MYVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view, parent, false);
            return new MYVH(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MYVH holder, int position) {
            Item item = this.mItem.get(position);
            holder.city.setText(item.getStationName());
            String grade1 = "";
            switch (item.getPm10Grade()) {
                case "1":
                    grade1 = "좋음";
                    break;
                case "2":
                    grade1 = "보통";
                    break;
                case "3":
                    grade1 = "나쁨";
                    break;
                case "4":
                    grade1 = "매우 나음";
            }
            holder.grade1.setText("미세먼지 " + grade1);
            String grade2 = "";
            switch (item.getPm10Grade()) {
                case "1":
                    grade2 = "좋음";
                    break;
                case "2":
                    grade2 = "보통";
                    break;
                case "3":
                    grade2 = "나쁨";
                    break;
                case "4":
                    grade2 = "매우 나음";
            }
            holder.grade2.setText("초미세먼지 " + grade2);


        }

        @Override
        public int getItemCount() {
            return mItem.size();
        }

        public class MYVH extends RecyclerView.ViewHolder {
            TextView city;
            TextView grade1;
            TextView grade2;

            public MYVH(@NonNull View itemView) {
                super(itemView);
                city = itemView.findViewById(R.id.item_text_city);
                grade1 = itemView.findViewById(R.id.item_text_grade1);
                grade2 = itemView.findViewById(R.id.item_text_grade2);
            }
        }
    }

}