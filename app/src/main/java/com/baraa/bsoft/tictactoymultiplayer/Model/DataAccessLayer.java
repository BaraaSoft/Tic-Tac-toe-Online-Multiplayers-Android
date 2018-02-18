package com.baraa.bsoft.tictactoymultiplayer.Model;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by baraa on 17/02/2018.
 */

public class DataAccessLayer {
    public static final String DATA_COLLECTIONS_USERS = "Users";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String IMG_URL = "ImgUrl";
    public static final String ONLINE = "online";

    private static final String TAG = "DataAccessLayer";
    private DatabaseReference mDataUsersRef;
    FirebaseDatabase database;
    private FirebaseAuth mAuth;
    public DataAccessLayer() {
        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        mDataUsersRef = database.getReference();
    }

    public void addNewUser(){
        GameUser gameUser = new GameUser(mAuth.getCurrentUser().getDisplayName(),mAuth.getUid());
        gameUser.setImgUrl(mAuth.getCurrentUser().getPhotoUrl().toString());
        Log.d(TAG, "addNewUser: User Photo \n"+gameUser.getImgUrl());
        mDataUsersRef.child(DATA_COLLECTIONS_USERS).child(gameUser.getId()).setValue(gameUser);
    }

    public void setUserOnline(){
        mDataUsersRef.child(DATA_COLLECTIONS_USERS).child(mAuth.getUid()).child(ONLINE).setValue(true);
    }
    public void setUserOffline(){
        mDataUsersRef.child(DATA_COLLECTIONS_USERS).child(mAuth.getUid()).child(ONLINE).setValue(false);
    }


}
