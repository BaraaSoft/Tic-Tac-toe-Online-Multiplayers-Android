package com.baraa.bsoft.tictactoymultiplayer.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baraa.bsoft.tictactoymultiplayer.Model.DataAccessLayer;
import com.baraa.bsoft.tictactoymultiplayer.Model.GameUser;
import com.baraa.bsoft.tictactoymultiplayer.R;
import com.baraa.bsoft.tictactoymultiplayer.Views.PlayersRecyclerViewAdapter;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PlayersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlayersFragment extends Fragment implements DataAccessLayer.OnlineUserListener {
    private static final String TAG = "PlayersFragment";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ArrayList<GameUser> mOnlineUsersList;
    private DataAccessLayer mDataAccessLayer;
    private PlayersRecyclerViewAdapter recyclerViewAdapter;
    FirebaseDatabase database;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public PlayersFragment() {
        // Required empty public constructor
    }

    public static PlayersFragment newInstance(String param1, String param2) {
        PlayersFragment fragment = new PlayersFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        database = FirebaseDatabase.getInstance();
        mOnlineUsersList = new ArrayList<>();
        mDataAccessLayer = new DataAccessLayer();
        mDataAccessLayer.setOnlineUserListener(this);
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_players, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rcPlayers);
        recyclerViewAdapter = new PlayersRecyclerViewAdapter(mOnlineUsersList,getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);

        mDataAccessLayer.getOnlineUsers();
        return view;
    }

    @Override
    public void onUserOnline(GameUser gameUser) {
        mOnlineUsersList.add(gameUser);
        recyclerViewAdapter.notifyDataSetChanged();
    }




}
