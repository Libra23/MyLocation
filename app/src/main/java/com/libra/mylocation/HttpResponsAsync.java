package com.libra.mylocation;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by kayalibra on 2017/06/20.
 */

public class HttpResponsAsync extends AsyncTask <JSONObject, Void, Void>{

    private final static String TAG = "HttpResponsAsync";
    private static final String HOST_URL = "https://sodium-carver-170712.appspot.com/api/insert_pole";
    //private static final String HOST_URL = "https://sodium-carver-170712.appspot.com";
    //private static final String HOST_URL = "http://www.yahoo.co.jp/";
    //private static final String HOST_URL = "https://ja.wikipedia.org";
    public HttpResponsAsync(MainActivity mainActivity) {

    }


    @Override
    protected Void doInBackground(JSONObject... params) {
        OkHttpClient client = new OkHttpClient();
        String url = HOST_URL;
        MediaType JSON= MediaType.parse("application/json");
        RequestBody requestBody = RequestBody.create (JSON, params[0].toString());
        Request request = new Request.Builder().url(url).post(requestBody).build();
        try {
            Response response = client.newCall(request).execute();
            Log.d(TAG,"Body   " + response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*
        HttpURLConnection httpURLConnection = null;
        URL url = null;
        String urlString = HOST_URL;
        Log.d(TAG, "doInBackground is called");
        Log.d(TAG, "param[0]:" + params[0].toString());
        try {
            url = new URL(urlString);
            httpURLConnection = (HttpURLConnection) url.openConnection();

            httpURLConnection.setConnectTimeout(100000);
            httpURLConnection.setReadTimeout(100000);

            //httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);

            httpURLConnection.setDoInput(true);

            httpURLConnection.setRequestProperty("Accept-Language", "jp");
            httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=utf-8");

            httpURLConnection.connect();

            InputStream inputStream = httpURLConnection.getInputStream();
            String readSt = readInputStream(inputStream);
            Log.d(TAG, "bodyByte:" + readSt);

            OutputStream outputStream = httpURLConnection.getOutputStream();
            PrintStream printStreams = new PrintStream(httpURLConnection.getOutputStream());

            printStreams.print(params[0]);
            printStreams.close();

            outputStream.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        httpURLConnection.disconnect();
        */

        return null;
    }

    public String readInputStream(InputStream in) throws IOException, UnsupportedEncodingException {
        StringBuffer sb = new StringBuffer();
        String st = "";

        BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
        while((st = br.readLine()) != null)
        {
            sb.append(st);
        }
        try
        {
            in.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return sb.toString();
    }
}
