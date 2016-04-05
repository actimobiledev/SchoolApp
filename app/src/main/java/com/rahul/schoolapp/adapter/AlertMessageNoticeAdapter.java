package com.rahul.schoolapp.adapter;

import android.app.Activity;
import android.database.DataSetObserver;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.rahul.schoolapp.R;
import com.rahul.schoolapp.fragment.HomeFragment;
import com.rahul.schoolapp.utils.Constants;


public class AlertMessageNoticeAdapter implements ExpandableListAdapter {
    Activity mActivity;
    LayoutInflater layoutInflater;
    View view;


    TextView tvApprove;
    TextView tvDecline;
    CardView cvMessage;
    TextView tvMessage;
    TextView tvMessageTitle;
    TextView tvFrom;
    TextView tvDate;
    TextView tvRecipientText;
    TextView tvRecipientName;
    TextView tvReply;



    public AlertMessageNoticeAdapter(Activity activity, LayoutInflater layoutInflater, View v) {
        mActivity = activity;
        this.layoutInflater = layoutInflater;
        view = v;
        initView (v);
    }


    private void initView(View v){

        tvMessageTitle = (TextView) v.findViewById (R.id.tvMessageTitle);
        tvMessage = (TextView) v.findViewById (R.id.tvMessage);
        cvMessage = (CardView) v.findViewById (R.id.card_view);
        tvFrom = (TextView) v.findViewById (R.id.tvFrom);
        tvDate = (TextView) v.findViewById (R.id.tvDate);
        tvRecipientText = (TextView) v.findViewById (R.id.tvRecipientText);
        tvRecipientName = (TextView) v.findViewById (R.id.tvRecipient);
        tvReply=(TextView)v.findViewById(R.id.tvReply);
    }

    @Override
    public boolean areAllItemsEnabled () {
        return true;
    }

    @Override
    public Object getChild (int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getChildId (int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public View getChildView (final int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View v = View.inflate(mActivity, R.layout.child, null);
        RadioButton radioButton;
        HomeFragment.last_expanded_position = groupPosition;

        if (groupPosition == 0) {
            for (int i = 1; i <= Constants.total_alert; i++) {
                radioButton = new RadioButton(mActivity);
                radioButton.setId (i);
                radioButton.setText ("Alert" + "   " + radioButton.getId ());
                ((RadioGroup) v.findViewById (R.id.radiogroup)).addView (radioButton);
            }
        }
        if (groupPosition == 1) {
            for (int i = (Constants.total_alert + 1); i < (Constants.total_alert + Constants.total_message + 1); i++) {
                radioButton = new RadioButton(mActivity);
                radioButton.setId (i);
                radioButton.setText ("Message" + "   " + radioButton.getId ());
                ((RadioGroup) v.findViewById (R.id.radiogroup)).addView (radioButton);
            }
        }
        if (groupPosition == 2) {
            for (int i = (Constants.total_alert + Constants.total_message + 1); i < (Constants.total_alert + Constants.total_message + Constants.total_notice + 1); i++) {
                radioButton = new RadioButton(mActivity);
                radioButton.setId (i);
                radioButton.setText ("Notice" + "   " + radioButton.getId ());
                ((RadioGroup) v.findViewById (R.id.radiogroup)).addView (radioButton);
            }
        }
        //get selected radio button from radioGroup
        RadioGroup radioGroup = (RadioGroup) v.findViewById (R.id.radiogroup);
        final View finalV = v;
        radioGroup.setOnCheckedChangeListener (new RadioGroup.OnCheckedChangeListener () {
            @Override
            public void onCheckedChanged (RadioGroup group, int checkedId) {
                cvMessage.setVisibility (View.VISIBLE);
                RadioButton osButton = (RadioButton) finalV.findViewById (checkedId);
                switch (groupPosition){
                    case 0:

                        tvRecipientName.setVisibility (View.VISIBLE);
                        tvRecipientText.setVisibility (View.VISIBLE);
                        tvRecipientText.setVisibility(View.VISIBLE);
                        tvReply.setVisibility(View.GONE);

                        //                 rlAlert.setVisibility (View.VISIBLE);
                        break;
                    case 1:

                        tvRecipientName.setVisibility (View.VISIBLE);
                        tvRecipientText.setVisibility (View.VISIBLE);
                        tvReply.setVisibility(View.VISIBLE);

                        //                  rlAlert.setVisibility (View.VISIBLE);
                        break;
                    case 2:

                        tvRecipientName.setVisibility (View.GONE);
                        tvRecipientText.setVisibility (View.GONE);
                        tvReply.setVisibility(View.GONE);
                        //                   rlAlert.setVisibility (View.VISIBLE);
                        break;
                }
                tvMessageTitle.setText (osButton.getText ());
                tvMessage.setText ("The following Radio button is selected...\n" + osButton.getText ());
            }
        });
        v.invalidate ();
        return v;
    }

    @Override
    public int getChildrenCount (int groupPosition) {
        return 1;
    }

    @Override
    public long getCombinedChildId (long groupId, long childId) {
        return 0;
    }

    @Override
    public long getCombinedGroupId (long groupId) {
        return 0;
    }

    @Override
    public Object getGroup (int groupPosition) {
        return null;
    }

    @Override
    public int getGroupCount () {
        return 3;
    }

    @Override
    public long getGroupId (int groupPosition) {
        return 0;
    }

    @Override
    public View getGroupView (int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View v = convertView.inflate (mActivity, R.layout.expandable_group_layout, null);
        TextView txtView = (TextView) v.findViewById (R.id.txt1);
        TextView tvNumber = (TextView) v.findViewById (R.id.tvNumber);

        if (groupPosition == 0) {
            txtView.setText ("Alert");
            tvNumber.setText ("" + Constants.total_alert);
            txtView.setCompoundDrawablesWithIntrinsicBounds (R.drawable.alert, 0, 0, 0);
        }
        if (groupPosition == 1) {
            txtView.setText ("Message");
            tvNumber.setText ("" + Constants.total_message);
            txtView.setCompoundDrawablesWithIntrinsicBounds (R.drawable.message, 0, 0, 0);
        }
        if (groupPosition == 2) {
            txtView.setText ("Notice");
            tvNumber.setText ("" + Constants.total_notice);
            txtView.setCompoundDrawablesWithIntrinsicBounds (R.drawable.notice, 0, 0, 0);
        }
        v.invalidate ();
        return v;
    }

    @Override
    public boolean hasStableIds () {
        return false;
    }

    @Override
    public boolean isChildSelectable (int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public boolean isEmpty () {
        return false;
    }

    @Override
    public void onGroupCollapsed (int groupPosition) {
    }

    @Override
    public void onGroupExpanded (int groupPosition) {
    }

    @Override
    public void registerDataSetObserver (DataSetObserver observer) {
    }

    @Override
    public void unregisterDataSetObserver (DataSetObserver observer) {
    }
}