package com.duoduolicai360.simpleone.demo2;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by swg on 2017/9/20.
 */

public class DownloadTask extends AsyncTask<String, Integer, Integer> {

    private Context mContext;
    private int position;

    public DownloadTask(Context context, int position) {
        this.mContext = context;
        this.position = position;
    }

    @Override
    protected Integer doInBackground(String... strings) {
        URL url = null;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try{
            url = new URL(strings[0]);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.connect();
            int totalLength = con.getContentLength();
            InputStream is = con.getInputStream();
            byte[] buffer = new byte[1024];
            int length = 0;
            int currentLength = 0;
            while((length = is.read(buffer)) != -1){
                out.write(buffer,0,length);
                currentLength += length;
                int progress = (int)((((double)currentLength) / totalLength) * 100);
                publishProgress(progress);
            }
        } catch(Exception e){
            e.printStackTrace();
        } finally{
        }
        return 100;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        //发更新进度的广播
        Intent intent = new Intent();
        intent.putExtra(Activity2.KEY_POSITION, position);
        intent.putExtra(Activity2.KEY_PROGRESS, values[0]);
        intent.setAction(Activity2.ACTION_UPDATE_PROGRESS);
        mContext.sendBroadcast(intent);
    }

    @Override
    protected void onPostExecute(Integer integer) {
    }
}
