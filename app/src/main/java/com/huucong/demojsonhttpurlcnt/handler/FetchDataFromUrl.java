package com.huucong.demojsonhttpurlcnt.handler;

import android.os.AsyncTask;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huucong.demojsonhttpurlcnt.model.GithubRepository;
import com.huucong.demojsonhttpurlcnt.onFetchDataListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class FetchDataFromUrl extends AsyncTask<String,Void, List<GithubRepository>> {

    private onFetchDataListener mOnFetchDataListener;
    public FetchDataFromUrl(onFetchDataListener mOnFetchDataListener){
        this.mOnFetchDataListener = mOnFetchDataListener;
    }

    @Override
    protected List<GithubRepository> doInBackground(String... strings) {
        try {
            String JSON = getJSONStringFromUrl(strings[0]);

            Type type = new TypeToken<List<GithubRepository>>(){}.getType();

            List<GithubRepository> githubRepositories;

            githubRepositories = new Gson().fromJson(JSON, type);

            return githubRepositories;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<GithubRepository> githubRepositories) {
        super.onPostExecute(githubRepositories);
        mOnFetchDataListener.onFetchDataSuccess(githubRepositories);
    }

    private String getJSONStringFromUrl(String urlString) throws IOException {
        HttpURLConnection httpURLConnection = null;

        URL url = new URL(urlString);

        httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setConnectTimeout(10000);
        httpURLConnection.setReadTimeout(10000);

        StringBuffer sb = new StringBuffer();
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(httpURLConnection.getInputStream()));

        String line;
        while ((line = bufferedReader.readLine()) != null){
            sb.append(line + "\n");
        }

        bufferedReader.close();
        httpURLConnection.disconnect();

        String JSONString;
        JSONString = sb.toString();
        return  JSONString;
    }
}
