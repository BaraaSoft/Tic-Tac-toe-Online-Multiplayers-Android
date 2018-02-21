package com.baraa.bsoft.tictactoymultiplayer.Model;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by baraa on 17/02/2018.
 */

public class DataAccessLayer {
    public static final String DATA_COLLECTIONS_USERS = "Users";
    public static final String SCORE = "score";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String IMG_URL = "ImgUrl";
    public static final String ONLINE = "online";

    private static final String TAG = "DataAccessLayer";
    private DatabaseReference mDataUsersRef;
    FirebaseDatabase database;
    private long mCurrentPoint;
    private FirebaseAuth mAuth;

    private OnlineUserListener mOnlineUserListener;
    public interface OnlineUserListener{
        void onUserOnline(final GameUser gameUser);
    }

    public DataAccessLayer() {
        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        mDataUsersRef = database.getReference();
        mCurrentPoint = 0;
    }

    public void addNewUser(){
        GameUser gameUser = new GameUser(mAuth.getCurrentUser().getDisplayName(),mAuth.getUid());
        gameUser.setImgUrl(mAuth.getCurrentUser().getPhotoUrl().toString());
        mDataUsersRef.child(DATA_COLLECTIONS_USERS).child(gameUser.getId()).setValue(gameUser);
    }

    public void setUserOnline(){
        mDataUsersRef.child(DATA_COLLECTIONS_USERS).child(mAuth.getUid()).child(ONLINE).setValue(true);
    }
    public void setUserOffline(){
        mDataUsersRef.child(DATA_COLLECTIONS_USERS).child(mAuth.getUid()).child(ONLINE).setValue(false);
    }
    public void addToUserScore(final long point){
       Query query = mDataUsersRef.child(DATA_COLLECTIONS_USERS).child(mAuth.getUid());
       query.addListenerForSingleValueEvent(new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {
               mCurrentPoint = dataSnapshot.getValue(GameUser.class).getScore();
               long newScore = mCurrentPoint + point;
               mDataUsersRef.child(DATA_COLLECTIONS_USERS).child(mAuth.getUid()).child(SCORE).setValue(newScore);
           }
           @Override
           public void onCancelled(DatabaseError databaseError) {

           }
       });
    }

    public void getOnlineUsers(){

        Query query = database.getReference().child(DATA_COLLECTIONS_USERS).orderByChild("online").equalTo(true);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                GameUser gameUser = dataSnapshot.getValue(GameUser.class);
                DataAccessLayer.this.mOnlineUserListener.onUserOnline(gameUser);
                // Log.d(TAG, "onChildChanged: GAME_USER ::: "+gameUser.getName());

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    public void setOnlineUserListener(OnlineUserListener onlineUserListener){
        this.mOnlineUserListener = onlineUserListener;
    }
}
