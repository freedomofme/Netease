package com.hhxplaying.neteasedemo.netease.factory;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.hhxplaying.neteasedemo.netease.config.ErrorCode;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by HHX on 15/8/20.
 */
public class RequestSingletonFactory {
    private volatile static RequestSingletonFactory requestFactory;
    private static final HashMap<String, String> defaultPairs_baishuku;
    private static final HashMap<String, String> defaultPairs_baishuku_mobile;
    static {
        defaultPairs_baishuku = new HashMap<>();
        defaultPairs_baishuku.put("User-Agent", "Mozilla/5.0");
        defaultPairs_baishuku.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        defaultPairs_baishuku.put("Accept-Encoding", ""); //这句话加上去怎么死的都不知道
        defaultPairs_baishuku.put("Accept-Language", "zh-CN,zh;q=0.8");
        defaultPairs_baishuku.put("Host", "c.m.163.com");
        defaultPairs_baishuku.put("Upgrade-Insecure-Requests", "1");

        defaultPairs_baishuku_mobile = new HashMap<>();
//        defaultPairs_baishuku_mobile.put("Host", "m.baishuku.com");
//        defaultPairs_baishuku_mobile.put("Referer", "http://m.baishuku.com/modules/article/waps.php");
        defaultPairs_baishuku_mobile.put("Cookie", "");
        defaultPairs_baishuku_mobile.put("Accept-Encoding", "");

    }



    public static RequestSingletonFactory getInstance() {
        RequestSingletonFactory hold = requestFactory;
        if (hold == null) {
            synchronized (RequestSingletonFactory.class) {
                hold = requestFactory;
                if (hold == null)
                    requestFactory = hold = new RequestSingletonFactory();
            }
        }
        return hold;
    }

    public StringRequest getGETStringRequest(Context context, String url, Response.Listener responseListener) {
      return new StringRequest(Request.Method.GET, url, responseListener, new DefaultErrorListener(context)) {
          @Override
          protected Response<String> parseNetworkResponse(NetworkResponse response) {
              String str = null;
              try {
                  str = new String(response.data, "utf-8");//这里写死不好
              } catch (UnsupportedEncodingException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
              }
              return Response.success(str, HttpHeaderParser.parseCacheHeaders(response));
          }
          @Override
          public Map<String, String> getHeaders() throws AuthFailureError{
              return defaultPairs_baishuku;
          }
      };
    }

    public StringRequest getPOSTStringRequest(Context context, String url, final Map<String, String> paramsMap, Response.Listener responseListener) {
        return new StringRequest(Request.Method.POST, url, responseListener, new DefaultErrorListener(context)) {
          @Override
          protected Map<String, String> getParams() throws AuthFailureError {
              return paramsMap;
          }
            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String str = null;
                try {
                    str = new String(response.data, "utf-8");//这里写死不好
                } catch (UnsupportedEncodingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return Response.success(str, HttpHeaderParser.parseCacheHeaders(response));
            }
            // Volley请求类提供了一个 getHeaders（）的方法，重载这个方法可以自定义HTTP 的头信息。（也可不实现）
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError{
                return defaultPairs_baishuku_mobile;
            }
            @Override
            protected String getParamsEncoding() {
                return "GBK";
            }
        };


    }

    class DefaultErrorListener implements Response.ErrorListener {
        private Context contextHold;
        public DefaultErrorListener(Context context) {
            contextHold = context;
        }
        @Override
        public void onErrorResponse(VolleyError error) {

            error.printStackTrace();
            System.out.println("error:" + error);
            int errorCode = 0;
            if (error instanceof TimeoutError) {
                errorCode = -7;
            } else if (error instanceof NoConnectionError) {
                errorCode = -1;
            } else if (error instanceof AuthFailureError) {
                errorCode = -6;
            } else if (error instanceof ServerError) {
                errorCode = 0;
            } else if (error instanceof NetworkError) {
                errorCode = -1;
            } else if (error instanceof ParseError) {
                errorCode = -8;
            }
            Toast.makeText(contextHold, ErrorCode.errorCodeMap.get(-1), Toast.LENGTH_SHORT).show();
        }
    }




}
