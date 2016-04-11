package com.rahul.schoolapp.calendar;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.p_v.flexiblecalendar.FlexibleCalendarView;
import com.p_v.flexiblecalendar.entity.CalendarEvent;
import com.p_v.flexiblecalendar.view.BaseCellView;
import com.p_v.flexiblecalendar.view.SquareCellView;
import com.rahul.schoolapp.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class CalendarActivityFragment extends Fragment implements FlexibleCalendarView.OnMonthChangeListener, FlexibleCalendarView.OnDateClickListener {
    private FlexibleCalendarView calendarView;
    TextView date;
    TableLayout tl1;
    ImageView nextMonthBtn;
    ImageView prevMonthBtn;


    public CalendarActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
        initView(view);
        initListener();
        setCheakBox();
        CalendarView();
        // initAdapter(inflater);

        return view;
    }

    private void initView(View view) {
        tl1 = (TableLayout) view.findViewById(R.id.tl2);
        nextMonthBtn = (ImageView) view.findViewById(R.id.move_to_next_month);
        prevMonthBtn = (ImageView) view.findViewById(R.id.move_to_previous_month);
        date = (TextView) view.findViewById(R.id.date);
        calendarView = (FlexibleCalendarView) view.findViewById(R.id.calendar_view);
    }


    private void initListener() {
        nextMonthBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarView.moveToNextMonth();
            }
        });
        prevMonthBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarView.moveToPreviousMonth();
            }
        });
    }


    private void setCheakBox() {
        int m = 5;
        double n = Math.ceil((double) m / 2);
        Log.d("n++++ = ", "" + n);
        for (int i = 1; i <= n; i++) {
            TableRow row = new TableRow(getActivity());
            row.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            for (int j = 0; j < m / n; j++) {

                CheckBox cbCalendar = new CheckBox(getActivity());
                cbCalendar.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                cbCalendar.setPadding(5, 5, 5, 5);
                int n1 = ((2 * i) - 1) + j;
                cbCalendar.setText("Calendar " + n1);
                cbCalendar.setId(n1);
                row.addView(cbCalendar);

            }
            m--;
            tl1.addView(row);
        }
    }


    private void CalendarView() {
        calendarView.setCalendarView(new FlexibleCalendarView.CalendarView() {
            @Override
            public BaseCellView getCellView(int position, View convertView, ViewGroup parent, @BaseCellView.CellType int cellType) {
                BaseCellView cellView = (BaseCellView) convertView;
                if (cellView == null) {
                    LayoutInflater inflater = LayoutInflater.from(getActivity());
                    cellView = (BaseCellView) inflater.inflate(R.layout.calendar1_date_cell_view, null);
                }
                if (cellType == BaseCellView.OUTSIDE_MONTH) {
                    cellView.setTextColor(getResources().getColor(R.color.date_outside_month_text_color_activity_1));
                }
                return cellView;
            }

            @Override
            public BaseCellView getWeekdayCellView(int position, View convertView, ViewGroup parent) {
                BaseCellView cellView = (BaseCellView) convertView;
                if (cellView == null) {
                    LayoutInflater inflater = LayoutInflater.from(getActivity());
                    cellView = (SquareCellView) inflater.inflate(R.layout.calendar1_week_cell_view, null);
                }
                return cellView;
            }

            @Override
            public String getDayOfWeekDisplayValue(int dayOfWeek, String defaultValue) {
                return String.valueOf(defaultValue.charAt(0));
            }
        });
        calendarView.setOnMonthChangeListener(this);
        calendarView.setOnDateClickListener(this);
        calendarView.setEventDataProvider(new FlexibleCalendarView.EventDataProvider() {
            @Override
            public List<CalendarEvent> getEventsForTheDay(int year, int month, int day) {
                if (year == 2015 && month == 8 && day == 12) {
                    List<CalendarEvent> eventColors = new ArrayList<>(2);
                    eventColors.add(new CalendarEvent(android.R.color.holo_blue_light));
                    eventColors.add(new CalendarEvent(android.R.color.holo_purple));
                    return eventColors;
                }
                if (year == 2015 && month == 8 && day == 7 ||
                        year == 2015 && month == 8 && day == 29 ||
                        year == 2015 && month == 8 && day == 5 ||
                        year == 2015 && month == 8 && day == 9) {
                    List<CalendarEvent> eventColors = new ArrayList<>(1);
                    eventColors.add(new CalendarEvent(android.R.color.holo_blue_light));
                    return eventColors;
                }

                if (year == 2016 && month == 4 && day == 4) {
                    List<CalendarEvent> eventColors = new ArrayList<>(3);
                    eventColors.add(new CalendarEvent(android.R.color.holo_red_dark));
                    eventColors.add(new CalendarEvent(android.R.color.holo_orange_light));
                    eventColors.add(new CalendarEvent(android.R.color.holo_purple));
                    return eventColors;
                }

                return null;
            }
        });
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        updateTitle(calendarView.getSelectedDateItem().getYear(), calendarView.getSelectedDateItem().getMonth());
    }


    private void updateTitle(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, 1);
        date.setText(cal.getDisplayName(Calendar.MONTH, Calendar.LONG, this.getResources().getConfiguration().locale) + " " + year);

    }

    @Override
    public void onMonthChange(int year, int month, int direction) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, 1);
        //updateTitle(year, month);
    }

    @Override
    public void onDateClick(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day);
        Toast.makeText(getActivity(), cal.getTime().toString() + " Clicked", Toast.LENGTH_SHORT).show();
    }
}
