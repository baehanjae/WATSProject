package com.example.mjkim.watsproject.Review;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mjkim.watsproject.CertainReviewScreenActivity;
import com.example.mjkim.watsproject.LocationDetailScreenActivity;
import com.example.mjkim.watsproject.R;

import java.util.ArrayList;

public class ReviewAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private Activity m_activity;
    private ArrayList<ReviewList> arr;
    public static int select = 0; //출력되는 리뷰 개수 선택 1이면 3개 2이면 최대 20개


    public ReviewAdapter(Activity act, ArrayList<ReviewList> arr_item) {
        this.m_activity = act;
        arr = arr_item;
        mInflater = (LayoutInflater) m_activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        if (select == 1) {
            if (LocationDetailScreenActivity.length < 3) return arr.size();
            else return 3;
        } else return arr.size();
    }

    @Override
    public Object getItem(int position) {
        return arr.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {

            int res = 0;
            res = R.layout.review_list_box;
            convertView = mInflater.inflate(res, parent, false);

        }

        ImageView profilePic = (ImageView) convertView.findViewById(R.id.profile_pic);
        TextView reviewerName = (TextView) convertView.findViewById(R.id.vi_name);
        TextView description = (TextView) convertView.findViewById(R.id.vi_description);
        TextView postDate = (TextView) convertView.findViewById(R.id.vi_date);
        LinearLayout layout_view = (LinearLayout) convertView.findViewById(R.id.vi_view);


        reviewerName.setText(arr.get(position).getUserName());
        description.setText(arr.get(position).getReview_description());
        postDate.setText(arr.get(position).getDate());


        layout_view.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){

                GoIntent(position);

            }

        });

        return convertView;


    }

    public void GoIntent(int a){

        Intent intent = new Intent(m_activity, CertainReviewScreenActivity.class);


        intent.putExtra("Email", arr.get(a).getUserEmail());
        intent.putExtra("Name", arr.get(a).getUserName());
        intent.putExtra("Review", arr.get(a).getReview_description());
        intent.putExtra("Date",arr.get(a).getDate());
        intent.putExtra("Tag1",arr.get(a).getTag1());
        intent.putExtra("Tag2",arr.get(a).getTag2());
        intent.putExtra("Tag3",arr.get(a).getTag3());
        intent.putExtra("Tag4",arr.get(a).getTag4());
        intent.putExtra("Tag5",arr.get(a).getTag5());
        intent.putExtra("Tag6",arr.get(a).getTag6());


        m_activity.startActivity(intent);
    }
}