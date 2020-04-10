package com.dk.searchexpress.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dk.searchexpress.R;
import com.dk.searchexpress.bean.Traces;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: ExpResultAdapter
 * @Author: dongke
 * @Date: 2020/4/10 20:00
 * @Description:
 */
public class ExpResultAdapter extends BaseAdapter {

    private List<Traces> tracesList;
    private Context mContext;

    public ExpResultAdapter(Context context, List<Traces> tracesList) {
        this.mContext = context;
        this.tracesList = tracesList;
    }

    @Override
    public int getCount() {
        return tracesList == null ? 0 : tracesList.size();
    }

    @Override
    public Traces getItem(int position) {
        return tracesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.query_result_item, null);
            viewHolder = new ViewHolder();
            viewHolder.tvTranceTime = convertView.findViewById(R.id.time);
            viewHolder.tvTranceLocation = convertView.findViewById(R.id.location);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Traces traces = getItem(position);
        viewHolder.tvTranceLocation.setText(traces.getAcceptStation());
        viewHolder.tvTranceTime.setText(formateDate(traces.getAcceptTime()));
        return convertView;
    }

    private String formateDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }

    static class ViewHolder {
        public TextView tvTranceTime;
        public TextView tvTranceLocation;
    }
}
