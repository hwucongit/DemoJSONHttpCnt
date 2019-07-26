package com.huucong.demojsonhttpurlcnt.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.huucong.demojsonhttpurlcnt.R;
import com.huucong.demojsonhttpurlcnt.model.GithubRepository;
import java.util.List;

public class GitRepositoryAdapter extends RecyclerView.Adapter<GitRepositoryAdapter.ViewHolder> {
    private Context mContext;
    private List<GithubRepository> mGithubRepository;
    public GitRepositoryAdapter(Context context, List<GithubRepository> githubRepositories) {
        this.mContext = context;
        this.mGithubRepository = githubRepositories;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext)
                .inflate(R.layout.item_github_repository,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GithubRepository githubRepository = mGithubRepository.get(position);
        holder.onBind(githubRepository);
    }

    @Override
    public int getItemCount() {
        return mGithubRepository == null ? 0 : mGithubRepository.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView idTextView, noteIdTextView, nameTextView, privateTextView,
            urlTextView, descriptionTextView, createdAtTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            idTextView = itemView.findViewById(R.id.text_id);
            noteIdTextView = itemView.findViewById(R.id.text_note_id);
            nameTextView = itemView.findViewById(R.id.text_name);
            privateTextView = itemView.findViewById(R.id.text_private);
            urlTextView = itemView.findViewById(R.id.text_url);
            descriptionTextView = itemView.findViewById(R.id.text_description);
            createdAtTextView = itemView.findViewById(R.id.text_created_at);
        }
        public void onBind(GithubRepository githubRepository){
            idTextView.setText(githubRepository.getId()+"");
            noteIdTextView.setText(githubRepository.getNodeId()+"");
            nameTextView.setText(githubRepository.getName()+"");
            privateTextView.setText(githubRepository.getPrivate()+"");
            urlTextView.setText(githubRepository.getUrl());
            descriptionTextView.setText(githubRepository.getDescription());
            createdAtTextView.setText(githubRepository.getCreatedAt());
        }
    }
}
