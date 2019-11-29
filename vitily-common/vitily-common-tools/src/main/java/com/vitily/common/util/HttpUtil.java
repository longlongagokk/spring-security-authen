package com.vitily.common.util;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
public class HttpUtil {
    static final int CONN_TIMEOUT = 20;
    static final int READ_TIMEOUT = 20;
    static final int WRITE_TIMEOUT = 20;
    static final OkHttpClient client = createOkHttpClient();
    // create OkHttpClient:
    private static HttpUtil instance = new HttpUtil();
    public static HttpUtil getInstance() {
        return instance;
    }
    static OkHttpClient createOkHttpClient() {

        OkHttpClient httpClient = null;
        try {
            httpClient = new OkHttpClient.Builder().connectTimeout(CONN_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS).writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                    .build();
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return httpClient;
    }
    public String okHttpGet(String url, Map<String,String> headers){
        return okHttpGet(url,null,headers);
    }
    public String okHttpGet(String url,Map<String,String> params, Map<String,String> headers){
        try {
            String param = paramToQuery(params);
            if(url.indexOf("?") == -1){
                url += "?";
            }
            url +=param;
            Request.Builder builder = new Request.Builder().url(url).get();
            if(CommonUtil.isNotNull(headers)){
                for(Map.Entry<String,String> e:headers.entrySet()){
                    builder.addHeader(e.getKey(),e.getValue());
                }
            }
            Request request = builder.build();
            Response response = client.newCall(request).execute();
            String s = response.body().string();
            return s;
        } catch (IOException e) {
            log.error(e.getMessage(),e);
            return null;
        }
    }
    public String okHttpPostOfJson(String url, Map<String, String> param, Map<String,String> headers) {
        try {
            RequestBody body = RequestBody.create(MediaType.parse("application/json"), JSONUtil.toJSONString(param));
            Request.Builder builder = new Request.Builder().url(url).post(body);
            if(CommonUtil.isNotNull(headers)){
                for(Map.Entry<String,String> e:headers.entrySet()){
                    builder.addHeader(e.getKey(),e.getValue());
                }
            }
            Request request = builder.build();
            Response response = client.newCall(request).execute();
            String s = response.body().string();
            return s;
        } catch (IOException e) {
            log.error(e.getMessage(),e);
            return null;
        }
    }
    public String okHttpPostOfForm(String url,Map<String,String> param,Map<String,String> headers){
        try {
            FormBody.Builder bodyBuilder = new FormBody.Builder();
            if(CommonUtil.isNotNull(param)){
                for(Map.Entry<String,String> e:param.entrySet()){
                    bodyBuilder.add(e.getKey(),e.getValue());
                }
            }
            Request.Builder builder = new Request.Builder().url(url).post(bodyBuilder.build());
            if(CommonUtil.isNotNull(headers)){
                for(Map.Entry<String,String> e:headers.entrySet()){
                    builder.addHeader(e.getKey(),e.getValue());
                }
            }
            Request request = builder.build();
            Response response = client.newCall(request).execute();
            String s = response.body().string();
            return s;
        } catch (IOException e) {
            log.error(e.getMessage(),e);
            return null;
        }
    }
    public String simplePost(String url,String jsonData,Map<String,String> headers){
        String result = null;
        PrintWriter out = null;
        InputStream in = null;
        try {
            URL _url = new URL(url);
            HttpURLConnection urlcon = (HttpURLConnection) _url.openConnection();
            urlcon.setDoInput(true);
            urlcon.setDoOutput(true);
            urlcon.setUseCaches(false);
            urlcon.setRequestMethod("POST");
            if(CommonUtil.isNotNull(headers)){
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    urlcon.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }
            urlcon.connect();
            if(CommonUtil.isNotNull(jsonData)) {
                out = new PrintWriter(urlcon.getOutputStream());
                out.print(jsonData);
                out.flush();
            }
            in = urlcon.getInputStream();
            BufferedReader buffer = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            StringBuffer bs = new StringBuffer();
            String line = null;
            while ((line = buffer.readLine()) != null) {
                bs.append(line);
            }
            result = bs.toString();
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        } finally {
            try {
                if (null != in)
                    in.close();
                if (null != out)
                    out.close();
            } catch (Exception e2) {
                log.error(e2.getMessage(),e2);
            }
        }
        return result;
    }
    public static String paramToQuery(Map<String,String> params){
        if(CommonUtil.isNotNull(params)) {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, String> e : params.entrySet()) {
                sb.append("&").append(e.getKey()).append("=").append(e.getValue());
            }
            if (sb.length() > 0) {
                return sb.substring(1);
            }
        }
        return "";
    }
}
