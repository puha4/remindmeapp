package com.qoobico.remindme.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.qoobico.remindme.R;
import com.qoobico.remindme.dto.RemindDTO;
import com.qoobico.remindme.fragment.AbstractTabFragment;
import com.qoobico.remindme.fragment.BirthdaysFragment;
import com.qoobico.remindme.fragment.HistoryFragment;
import com.qoobico.remindme.fragment.IdeasFragment;
import com.qoobico.remindme.fragment.TodoFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TabsFragmentAdapter extends FragmentPagerAdapter {

    private Map<Integer, AbstractTabFragment> tabs;
    private Context context;

    private HistoryFragment historyFragment;

    private List<RemindDTO> data;

    public TabsFragmentAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
        this.data = new ArrayList<>();
        initTabMap(context);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs.get(position).getTitle();
    }

    @Override
    public Fragment getItem(int position) {
        return tabs.get(position);
    }

    @Override
    public int getCount() {
        return tabs.size();
    }

    private void initTabMap(Context context) {
        historyFragment = HistoryFragment.getInstance(context, data);

        tabs = new HashMap<>();
        tabs.put(0, historyFragment);
        tabs.put(1, IdeasFragment.getInstance(context));
        tabs.put(2, TodoFragment.getInstance(context));
        tabs.put(3, BirthdaysFragment.getInstance(context));
    }

    public void setData(List<RemindDTO> data) {
        this.data = data;
        historyFragment.refreshData(data);
    }
}
