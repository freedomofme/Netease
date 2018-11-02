package com.hhxplaying.neteasedemo.netease.factory;

import android.content.Context;
import android.util.Log;
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
import com.android.volley.toolbox.StringRequest;
import com.hhxplaying.neteasedemo.netease.config.ErrorCode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by HHX on 15/8/20.
 */
public class RequestSingletonFactory {
    private volatile static RequestSingletonFactory requestFactory;
    private static final HashMap<String, String> defaultPairs_baishuku;
    private static final HashMap<String, String> defaultPairs_baishuku_mobile;

    protected static final String TYPE_UTF8_CHARSET = "charset=UTF-8";

    static {
        defaultPairs_baishuku = new HashMap<>();
        defaultPairs_baishuku.put("User-Agent", "Mozilla/5.0");
        defaultPairs_baishuku.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        defaultPairs_baishuku.put("Accept-Encoding", "");
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
        if (requestFactory == null) {
            synchronized (RequestSingletonFactory.class) {
                if (requestFactory == null)
                    requestFactory = new RequestSingletonFactory();
            }
        }
        return requestFactory;
    }

    public StringRequest getGETStringRequest(Context context, final String url, Response.Listener responseListener) {
        Log.i("RVA", "request add queue. link is :" + url);
      return new StringRequest(Request.Method.GET, url, responseListener, new DefaultErrorListener(context)) {
          @Override
          protected Response<String> parseNetworkResponse(NetworkResponse response) {
              Log.i("RVA", "request successed. link is :" + url);
              addEncodeing2Request(response);
              return super.parseNetworkResponse(response);
          }
          @Override
          public Map<String, String> getHeaders() throws AuthFailureError{
              return defaultPairs_baishuku;
          }
//          https://github.com/freedomofme/HandyVolley
//          Ttl和softTtl说明 Ttl和softTtl用来用户自定义缓存时间，通常softTtl <= Ttl。
//
//          当一个请求的过期时间 > Ttl, 则重新请求服务器。
//
//          当一个请求的过期时间 > softTtl && < Ttl, 则先使用缓存数据做出响应，并同时将该请求发送服务器。（也就是说，响应回调函数会触发两次）
//
//          当一个请求的过期时间 > softTtl，则直接使用本地缓存。
          @Override
          public int getDefaultTtl() {
              return 1 * 30 * 1000; // 哎呀，当时写的时候在接口里返回是缓存的，还是真实的。结果只能临时getDefaultTtl和getDefaultSoftTtl一致了。
          }
          @Override
          public int getDefaultSoftTtl() {
              return 1 * 30 * 1000;
          }
          @Override
          public boolean shouldLocalCacheControl() {
              return true;
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
                addEncodeing2Request(response);
                return super.parseNetworkResponse(response);
            }
            // Volley请求类提供了一个 getHeaders（）的方法，重载这个方法可以自定义HTTP 的头信息。（也可不实现）
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError{
                return defaultPairs_baishuku_mobile;
            }

//            @Override
//            protected String getParamsEncoding() {
//                return "GBK";
//            }
        };
    }

    public static final String CONTENT_TYPE = "Content-Type";

    private void addEncodeing2Request(NetworkResponse response) {
        try {
            String type = response.headers.get(CONTENT_TYPE);
            if (type == null) {
                //Content-Type:
                Log.d("RVA", "content type was null");
                type = TYPE_UTF8_CHARSET;
                response.headers.put(CONTENT_TYPE, type);
            } else if (!type.contains("charset")) {
                //Content-Type: text/plain;
                Log.d("RVA", "charset was null, added encode utf-8");
                type += ";" + TYPE_UTF8_CHARSET;
                response.headers.put(CONTENT_TYPE, type);
            } else {
                //Nice! Content-Type: text/plain; charset=utf-8'
                Log.d("RVA", "charset is " + type);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class DefaultErrorListener implements Response.ErrorListener {
        private Context contextHold;
        public DefaultErrorListener(Context context) {
            contextHold = context;
        }
        @Override
        public void onErrorResponse(VolleyError error) {
            error.printStackTrace();
            Log.d("RVA", "error:" + error);

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
            Toast.makeText(contextHold, ErrorCode.errorCodeMap.get(errorCode), Toast.LENGTH_SHORT).show();
        }
    }




}
