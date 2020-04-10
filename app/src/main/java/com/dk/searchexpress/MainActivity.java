package com.dk.searchexpress;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.dk.searchexpress.adapter.ExpResultAdapter;
import com.dk.searchexpress.bean.ExpressQueryBean;
import com.dk.searchexpress.bean.Traces;
import com.dk.searchexpress.callback.IQueryExpressCallBack;
import com.dk.searchexpress.manager.NetManager;
import com.dk.searchexpress.utils.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Context mContext;
    private ListView mResultList;
    private Button mBtnQuery;
    private TextView mTextSelectedCompany;
    private Spinner mSpinner;
    private LinearLayout mCompanySelect;
    private EditText mEditNum;
    private LinearLayout mHeaderContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mContext = this;
        mResultList = (ListView) findViewById(R.id.list);
        mBtnQuery = (Button) findViewById(R.id.btn_query);

        mBtnQuery.setOnClickListener(this);
        mTextSelectedCompany = (TextView) findViewById(R.id.text_selected_company);
        mTextSelectedCompany.setOnClickListener(this);
        mSpinner = (Spinner) findViewById(R.id.spinner);
        //mSpinner.setOnClickListener(this);
        mCompanySelect = (LinearLayout) findViewById(R.id.company_select);
        mCompanySelect.setOnClickListener(this);
        mEditNum = (EditText) findViewById(R.id.edit_num);
        mEditNum.setOnClickListener(this);
        mHeaderContainer = (LinearLayout) findViewById(R.id.header_container);
        mHeaderContainer.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_query:
                doQuery();
                break;
        }
    }

    private void doQuery() {
        NetManager.getInstance().queryExpress("STO", mEditNum.getText().toString().trim(), new IQueryExpressCallBack() {
            @Override
            public void onQuerySuccess(ExpressQueryBean result) {
                if (result != null && mResultList != null) {
                    List<Traces> traces = result.getTraces();
                    ExpResultAdapter resultAdapter = new ExpResultAdapter(mContext, traces);
                    mResultList.setAdapter(resultAdapter);
                }
            }

            @Override
            public void onQueryFail(int code, String msg) {
                LogUtil.loge(String.format("错误：code:%d,Msg:%s", code, msg));
            }
        });
    }
}
