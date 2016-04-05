package com.rahul.schoolapp.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.rahul.schoolapp.R;
import com.rahul.schoolapp.activity.AddMessageActivity;
import com.rahul.schoolapp.adapter.AlertMessageNoticeAdapter;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    private ExpandableListView exListView;
    private TextView tvMessageTitle;
    private TextView tvMessage;
    private TextView tvApprove;
    private TextView tvDecline;
    private CardView cvMessage;
    private ImageView ivAddAlert;
    private ImageView ivAddMessage;
    private ImageView ivAddNotice;
    private TextView tvAddAlert;
    private TextView tvAddMessage;
    private TextView tvAddNotice;
    private TextView tvReply;
    RelativeLayout rlAlert;
    public static int last_expanded_position = - 1;
    AlertMessageNoticeAdapter adapter;
    Spinner spinner;

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate (R.layout.fragment_home, null);
        initView (v);
//        new GetAlertMessageNotice (getActivity ()).execute ();
        initListener ();
        initAdapter (inflater, v);
        return v;
    }

    private void initView (View v) {
        exListView = (ExpandableListView) v.findViewById (R.id.list);

        tvMessageTitle = (TextView) v.findViewById (R.id.tvMessageTitle);
        tvMessage = (TextView) v.findViewById (R.id.tvMessage);
        cvMessage = (CardView) v.findViewById (R.id.card_view);
        rlAlert = (RelativeLayout) v.findViewById (R.id.rlAlert);
        tvReply=(TextView)v.findViewById(R.id.tvReply);
        spinner=(Spinner)v.findViewById(R.id.spinnerSelectSchool);
    }

    private void initListener () {


        exListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                //               cvMessage.setVisibility (View.GONE);
            }
        });
        exListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                cvMessage.setVisibility(View.GONE);
                if (last_expanded_position != -1
                        && groupPosition != last_expanded_position) {
                    exListView.collapseGroup(last_expanded_position);
                }
                last_expanded_position = groupPosition;
            }
        });

        tvReply.setOnTouchListener (new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    tvReply.setTextColor(getResources().getColor(R.color.colorPrimary));
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    tvReply.setTextColor(getResources().getColor(R.color.colorHint));
                    Intent intent = new Intent(getActivity(), AddMessageActivity.class);
                    startActivity(intent);
                }
                return true;
            }
        });


    }

    private void initAdapter (LayoutInflater inflater, View v) {
        adapter = new AlertMessageNoticeAdapter(getActivity (), inflater, v);
        exListView.setAdapter(adapter);


        List<String> list = new ArrayList<String>();
        list.add("My School");
        list.add("Sant Xiaveer");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                String subject = spinner.getSelectedItem().toString();

            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}