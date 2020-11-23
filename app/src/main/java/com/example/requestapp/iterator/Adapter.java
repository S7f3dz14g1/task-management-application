package com.example.requestapp.iterator;

        import android.net.Uri;

        import com.example.requestapp.model.Task;

        import java.util.List;

public interface Adapter {

    void onSuccess();

    void onFailure();

    void setList(String type,List<String> listTasks);

    void setCommpletetTaskList(List< Task > taskList);

    int getSizeList(String type);

    void getKeyImageUser(String key);

    void setUriImageUSer(Uri uri);

    void setSizeCompletedList(int size);
}
