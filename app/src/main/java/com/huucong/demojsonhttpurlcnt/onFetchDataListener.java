package com.huucong.demojsonhttpurlcnt;

import com.huucong.demojsonhttpurlcnt.model.GithubRepository;

import java.util.List;

public interface onFetchDataListener {
    void onFetchDataSuccess(List<GithubRepository> githubRepositories);
}
