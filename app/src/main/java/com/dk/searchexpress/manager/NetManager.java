package com.dk.searchexpress.manager;

import com.dk.searchexpress.callback.IQueryExpressCallBack;
import com.dk.searchexpress.constants.Constant;
import com.dk.searchexpress.utils.Util;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: NetManager
 * @Author: dongke
 * @Date: 2020/4/10 19:30
 * @Description:
 */
public class NetManager {

    private static NetManager sInstance;

    private NetManager() {

    }

    public static NetManager getInstance() {
        if (sInstance == null) {
            synchronized (NetManager.class) {
                if (sInstance == null) {
                    sInstance = new NetManager();
                }
            }
        }
        return sInstance;
    }


    /**
     *
     * @param expCompany 快递公司
     * @param expNum 快递单号
     * @param expressCallBack 查询结果回调
     */
    public void queryExpress(String expCompany, String expNum, IQueryExpressCallBack expressCallBack) {
        try {
            OkhttpManager.getInstance().execute(Constant.KDNiao.ReqURL, combineParam(expCompany, expNum), expressCallBack);
        } catch (Exception e) {
            e.printStackTrace();
            expressCallBack.onQueryFail(Constant.ERROR_CODE.ERROR_PARAM_CONVERT, "参数转换异常");
        }
    }

    /**
     * 查询参数Map
     * @param expCode 快递公司编号
     * @param expNo 快递单号
     * @return
     * @throws Exception
     */
    private Map<String, String> combineParam(String expCode, String expNo) throws Exception {
        String requestData = "{'OrderCode':'','ShipperCode':'" + expCode + "','LogisticCode':'" + expNo + "'}";

        Map<String, String> params = new HashMap<String, String>();
        params.put("RequestData", Util.urlEncoder(requestData, "UTF-8"));
        params.put("EBusinessID", Constant.KDNiao.EBusinessID);
        params.put("RequestType", "1002");
        String dataSign = Util.encrypt(requestData, Constant.KDNiao.AppKey, "UTF-8");
        params.put("DataSign", Util.urlEncoder(dataSign, "UTF-8"));
        params.put("DataType", "2");

        return params;
    }


}
