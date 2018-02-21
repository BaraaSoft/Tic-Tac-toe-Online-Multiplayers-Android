package com.baraa.bsoft.tictactoymultiplayer.Views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baraa.bsoft.tictactoymultiplayer.Model.GameUser;
import com.baraa.bsoft.tictactoymultiplayer.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by baraa on 18/02/2018.
 */

public class PlayersRecyclerViewAdapter extends RecyclerView.Adapter<PlayersRecyclerViewAdapter.UserViewHolder> {

    private ArrayList<GameUser> mGamersOnlineList;
    private Context mContext;

    public PlayersRecyclerViewAdapter(ArrayList<GameUser> lst, Context context) {
        this.mGamersOnlineList = lst;
        this.mContext = context;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_item,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        GameUser  gameUser =  mGamersOnlineList.get(position);
        Picasso.with(mContext)
                .load(gameUser.getImgUrl())
                .into(holder.getImgProfile());
        if(gameUser.isOnline()){
            holder.getImgOnline().setImageDrawable(mContext.getDrawable(R.drawable.ic_person_online_24dp));
        }else {
            holder.getImgOnline().setImageDrawable(mContext.getDrawable(R.drawable.ic_person_offline_black_24dp));
        }
        holder.getTvName().setText(gameUser.getName());
        holder.getTvScore().setText(mContext.getString(R.string.score_label,gameUser.getScore()));

    }

    @Override
    public int getItemCount() {
        return mGamersOnlineList.size();
    }

    public void updateDataSet(ArrayList<GameUser> lst){
        this.mGamersOnlineList = lst;
        notifyDataSetChanged();
    }

    class UserViewHolder extends RecyclerView.ViewHolder{
        ImageView imgProfile;
        ImageView imgOnline;
        TextView tvName;
        TextView tvScore;

        public UserViewHolder(View itemView) {
            super(itemView);
            this.imgProfile = itemView.findViewById(R.id.imgProfile);
            this.imgOnline = itemView.findViewById(R.id.imgOnline);
            this.tvName = itemView.findViewById(R.id.tvName);
            this.tvScore = itemView.findViewById(R.id.tvScore);
        }

        public ImageView getImgProfile() {
            return imgProfile;
        }

        public ImageView getImgOnline() {
            return imgOnline;
        }

        public TextView getTvName() {
            return tvName;
        }

        public TextView getTvScore() {
            return tvScore;
        }
    }
}
