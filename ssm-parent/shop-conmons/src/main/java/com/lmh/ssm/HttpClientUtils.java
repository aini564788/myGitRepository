package com.lmh.ssm;


import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

public class HttpClientUtils {

    public static String doGet(String url, Map<String, String> parms) {
        String respseTex = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse = null;
        HttpGet httpGet = new HttpGet(url);
        try {
            URIBuilder uriBuilder = new URIBuilder();
            if (parms != null && !parms.isEmpty()) {
                List<NameValuePair> list = new ArrayList<>();
                Set<Map.Entry<String, String>> set = parms.entrySet();
                Iterator<Map.Entry<String, String>> iterator = set.iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, String> entry = iterator.next();
                    String name = entry.getKey();
                    String password = entry.getValue();
                    list.add(new BasicNameValuePair(name, password));
                }
                uriBuilder.addParameters(list);
            }
            httpGet.setURI(uriBuilder.build());
            httpResponse = httpClient.execute(httpGet);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                respseTex = EntityUtils.toString(httpResponse.getEntity());
            }
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        } finally {
            close(httpClient, httpResponse);
        }

        return respseTex;
    }

    public static String doGet(String url) {
        CloseableHttpResponse httpResponse = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String responseTex = null;

        HttpGet httpGet = new HttpGet(url);
        try {
            httpResponse = httpClient.execute(httpGet);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                responseTex = EntityUtils.toString(httpResponse.getEntity());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(httpClient, httpResponse);
            return responseTex;
        }

    }

    public static String doPost(String url, Map<String, String> parms) {
        String respseTex = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse = null;
        List<NameValuePair> list=null;
        HttpPost httpPost = new HttpPost(url);
        if (parms != null && !parms.isEmpty()) {
            list = new ArrayList<>();
            Set<Map.Entry<String, String>> set = parms.entrySet();
            Iterator<Map.Entry<String, String>> iterator = set.iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                String name = entry.getKey();
                String password = entry.getValue();
                list.add(new BasicNameValuePair(name, password));
            }
        }
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(list,"utf-8"));
            httpResponse = httpClient.execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                respseTex = EntityUtils.toString(httpResponse.getEntity());
            }
        } catch ( IOException e) {
            e.printStackTrace();
        } finally {
            close(httpClient, httpResponse);
        }
        return respseTex;
    }


    /**
     *
     * @param url==>Http://ip:port/api/user/1
     * @return
     */
    public  static String doDelete(String url){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse=null;
        String resText=null;
        try {
            HttpDelete httpDelete=new HttpDelete(url);
            httpResponse = httpClient.execute(httpDelete);
            if(httpResponse.getStatusLine().getStatusCode()==200){
                resText=EntityUtils.toString(httpResponse.getEntity());
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            close(httpClient,httpResponse);

            return resText;
        }

    }

    /**
     * 1.批量删除的时候  用post请求
     * 2._method="delete"    ===>post 请求
     */
    public static String  delete(String url,Map<String,String>params){
        params.put("_method","DELETE");
        String s = doPost(url, params);
        return s;
    }


    public  String  doPut(String url,Map<String,String> params){

        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse=null;
        String resText=null;
        List<NameValuePair> list=new ArrayList<>();
        if(params!=null&&!params.isEmpty()){
            //遍历map  ==>3种遍历
            Set<Map.Entry<String, String>> entrySet = params.entrySet();

            Iterator<Map.Entry<String, String>> iterator = entrySet.iterator();
            while(iterator.hasNext()){
                Map.Entry<String, String> entry = iterator.next();
                String name=entry.getKey();
                String value=entry.getValue();
                list.add(new BasicNameValuePair(name,value));

            }

        }

        try {
            HttpPut httpPut=new HttpPut(url);
            httpPut.setEntity(new UrlEncodedFormEntity(list,"utf-8"));
            httpResponse = httpClient.execute(httpPut);
            if(httpResponse.getStatusLine().getStatusCode()==200){
                resText=EntityUtils.toString(httpResponse.getEntity());
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            close(httpClient,httpResponse);

            return resText;
        }
    }


    /**
     *
     * @param url
     * @param requestJson
     * @return
     */
    public static String doPostJson(String url,String requestJson){

        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse=null;
        String resText=null;

        try {
            HttpPost httpPost=new HttpPost(url);
            //application/json
            httpPost.setHeader("Content-Type","application/json;charset=utf-8");
            httpPost.setEntity(new StringEntity(requestJson,"utf-8"));
            httpResponse = httpClient.execute(httpPost);
            if(httpResponse.getStatusLine().getStatusCode()==200){
                resText=EntityUtils.toString(httpResponse.getEntity());
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            close(httpClient,httpResponse);

            return resText;
        }

    }



    /**
     * @param httpClient
     * @param httpResponse
     */
    public static void close(CloseableHttpClient httpClient, CloseableHttpResponse httpResponse) {
        //释放资源
        if (httpResponse != null) {
            try {
                httpResponse.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        try {
            httpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
