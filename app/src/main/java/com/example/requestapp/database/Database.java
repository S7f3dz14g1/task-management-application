package com.example.requestapp.database;

import android.net.Uri;

import androidx.annotation.NonNull;

import com.example.requestapp.utils.Config;
import com.example.requestapp.iterator.Adapter;
import com.example.requestapp.model.StringHelper;
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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

public class Database implements DAO {

    private FirebaseAuth firebaseAuth;
    private final DatabaseReference databaseTasks;
    private Adapter lisner;
    private StorageReference storageReference;

    public Database() {
        firebaseAuth = FirebaseAuth.getInstance();
        databaseTasks = FirebaseDatabase.getInstance().getReference();
        storageReference= FirebaseStorage.getInstance().getReference();
    }

    public void setLisner(Adapter adapter) {
        lisner = adapter;
    }

    @Override
    public void onLogin(final User user) {
        firebaseAuth.signInWithEmailAndPassword(user.getNickName().toLowerCase() + "@app.pl", user.getPassword())
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
    public void addAvailableTask(Task task, int sizeList) {
        databaseTasks.child(getUserNick()).child(Config.TASKS).child(Config.AVAILABLE).child(task.getType())
                .child(sizeList + "").child(Config.DESCRYPTION).setValue(task.getDescryption());
    }
    @Override
    public void addCompletedTask(Task task, int sizeList) {
        databaseTasks.child(task.getNick()).child(Config.TASKS).child(Config.COMPLETED).child(sizeList + "")
                .setValue(task);
    }

    @Override
        public void getListAvilableTask(String nick, final String type) {
        databaseTasks.child(nick).child(Config.TASKS).child(Config.AVAILABLE).child(type).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList< String > tasksList = new ArrayList<>();

                for (DataSnapshot key : dataSnapshot.getChildren()) {
                    String[] value = key.getValue().toString().split("=");
                    tasksList.add(value[1].substring(0, value[1].indexOf("}")));
                }
                lisner.setList(type, tasksList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    @Override
    public void getListCompletedTask() {
        databaseTasks.child(getUserNick()).child(Config.TASKS).child(Config.COMPLETED).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List< Task > tasksList = new ArrayList<>();
                for (DataSnapshot key : dataSnapshot.getChildren()) {
                    String[] value = key.getValue().toString().split("=");
                    tasksList.add(new Task(value[2].substring(0,value[2].indexOf(',')),value[3].substring(0,value[3].indexOf('}'))));
                }
                lisner.setCommpletetTaskList(tasksList);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    private void updateListWhenItemIsDeleted( final String type, final String description) {

        databaseTasks.child(getUserNick()).child(Config.TASKS).child(Config.AVAILABLE).child(type).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList< String > tasksList = new ArrayList<>();//lista po usunięciu
                int i = 0;//ilość iteracji w liście
                for (DataSnapshot key : dataSnapshot.getChildren()) {
                    String n = (key.getValue().toString().split("=")[1]);///pobranie opius zdj
                    String opis = n.substring(0, n.length() - 1);
                    if (!opis.equals(description)) {//jeśli nie jest ten który chcemy usunąć to zapisuje do listy
                        String[] value = key.getValue().toString().split("=");
                        tasksList.add(value[1].substring(0, value[1].indexOf("}")));
                    }
                    i++;
                }
                i = 0;
                for (String s : tasksList)//aktualizowanie listy w firebase
                    addAvailableTask(new Task(getUserNick(), type, s), i++);
                deleteAvelableTask(type, i + "");//usunięcie ostatniego elementu
                lisner.setList(type, tasksList);//ustawienie listy
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    private void deleteAvelableTask(String type,String index){
        databaseTasks.child(getUserNick()).child(Config.TASKS).child(Config.AVAILABLE).child(type).child(index).removeValue();
    }
    @Override
    public FirebaseUser getUser() {
        return firebaseAuth.getCurrentUser();
    }

    @Override
    public String getUserNick() {
        return StringHelper.getNickName(firebaseAuth.getCurrentUser().getEmail());
    }

    @Override
    public void signOut() {
        firebaseAuth.signOut();
    }

    @Override
    public void uploadImage(Uri uri, final String key) {
       storageReference.child("images/"+key).putFile(uri)
               .addOnSuccessListener(new OnSuccessListener< UploadTask.TaskSnapshot >() {
                   @Override
                   public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                       setKeyImage(getUserNick(),key);
                   }
               });
    }

    @Override
    public void getKeyUserImage(String nickName) {
        databaseTasks.child(nickName).child(Config.IMAGE).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                lisner.getKeyImageUser(dataSnapshot.getValue()+"");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    @Override
    public void getUserImage(String key) {
        storageReference.child(Config.IMAGES).child(key).getDownloadUrl().addOnSuccessListener(new OnSuccessListener< Uri >() {
            @Override
            public void onSuccess(Uri uri) {
                lisner.setUriImageUSer(uri);
            }
        });
    }

    @Override
    public void moveAvailableTaskToCompletedTask(Task task, String intex) {
        updateListWhenItemIsDeleted(task.getType(),task.getDescryption());
        addCompletedTask(task,Integer.parseInt(intex));
    }

    @Override
    public void getSizeCompletedList() {
        databaseTasks.child(getUserNick()).child(Config.TASKS).child(Config.COMPLETED).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int size=0;
                for (DataSnapshot key : dataSnapshot.getChildren())
                    size++;
               lisner.setSizeCompletedList(size);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }


    private void setKeyImage(String nick,String key){
        databaseTasks.child(nick).child(Config.IMAGE).setValue(key);
    }

}
