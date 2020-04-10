package com.dk.searchexpress.callback;

import com.dk.searchexpress.bean.ExpressQueryBean;

/**
 * @ClassName: IQueryExpressCallBack
 * @Author: dongke
 * @Date: 2020/4/10 19:50
 * @Description:
 */
public interface IQueryExpressCallBack {
    void onQuerySuccess(ExpressQueryBean result);

    void onQueryFail(int code, String msg);
}
