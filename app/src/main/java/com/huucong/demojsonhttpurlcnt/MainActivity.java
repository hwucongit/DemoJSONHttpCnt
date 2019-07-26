package com.huucong.demojsonhttpurlcnt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.huucong.demojsonhttpurlcnt.adapter.GitRepositoryAdapter;
import com.huucong.demojsonhttpurlcnt.handler.FetchDataFromUrl;
import com.huucong.demojsonhttpurlcnt.model.GithubRepository;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements onFetchDataListener {
    private List<GithubRepository> mGithubRepositories;
    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_repository);

        new FetchDataFromUrl(this)
                .execute("https://api.github.com/users/google/repos");
    }

    @Override
    public void onFetchDataSuccess(List<GithubRepository> githubRepositories) {
        displayData(githubRepositories);
    }
    private void displayData(List<GithubRepository> githubRepositories){
        mGithubRepositories = githubRepositories;
        GitRepositoryAdapter adapter = new GitRepositoryAdapter(this,mGithubRepositories);
        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayout);
        mRecyclerView.setAdapter(adapter);
    }
}
