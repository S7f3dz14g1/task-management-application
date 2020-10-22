package com.example.requestapp.database;

import androidx.annotation.NonNull;

import com.example.requestapp.Utils.Config;
import com.example.requestapp.iterator.Adapter;
import com.example.requestapp.model.Task;
import com.example.requestapp.model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Database implements DAO {

    private FirebaseAuth firebaseAuth;
    private final DatabaseReference databaseTasks;
    private Adapter lisner;



    public Database() {
        firebaseAuth = FirebaseAuth.getInstance();
        databaseTasks = FirebaseDatabase.getInstance().getReference();
    }

    public  void setLisner(Adapter adapter){
        lisner=adapter;
    }

    @Override
    public void onLogin(final User user) {
        firebaseAuth.signInWithEmailAndPassword(user.getNickName() + "@app.pl", user.getPassword())
                .addOnSuccessListener(new OnSuccessListener< AuthResult >() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        lisner.onSuccess();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if (e.getMessage().contains("The password is invalid or the user does not have a password.")) {
                    lisner.onFailure();
                } else {
                    signup(user);
                    lisner.onSuccess();
                }
            }
        });
    }

    @Override
    public void signup(User user) {
        firebaseAuth.createUserWithEmailAndPassword(user.getNickName() + Config.END_EMEIL, user.getPassword())
                .addOnSuccessListener(new OnSuccessListener< AuthResult >() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                    }
                });
    }

    @Override
    public void addTask(Task task,int sizeList) {
        databaseTasks.child(task.getNick()).child(Config.TASKS).child(Config.AVAILABLE).child(task.getType())
                .child(sizeList+"").child(Config.DESCRYPTION).setValue(task.getDescryption());
    }


    @Override
    public void getListTask(String nick, final String type) {
        databaseTasks.child(nick).child(Config.TASKS).child(Config.AVAILABLE).child(type).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int iterator=0;
                ArrayList<String> tasksList=new ArrayList<>();
                for(DataSnapshot key:dataSnapshot.getChildren()){
                    String[]value= key.getValue().toString().split("=");
                    tasksList.add(value[1].substring(0,value[1].indexOf("}")));
                }
                lisner.setList(type,tasksList);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

}
