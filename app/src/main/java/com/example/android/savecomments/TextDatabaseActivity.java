package com.example.android.savecomments;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.List;
import java.util.Random;

public class TextDatabaseActivity extends ListActivity {
private  CommentsDb db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_database);
        db = new CommentsDb(this);
        db.getdb(); // opens our writable database
        List<Comment> values = db.getAllComments();
        ArrayAdapter<Comment> adapter = new ArrayAdapter<Comment>(
                this,
                R.layout.list_layout,
                values
        );

        setListAdapter(adapter);
    }
    public void onClick(View view)
    {
        @SuppressWarnings("unchecked")
        ArrayAdapter<Comment> adapter = (ArrayAdapter<Comment>) getListAdapter();
        Comment comment = null;
        switch (view.getId()){
        case R.id.add_new_button:
        {
            String cmt[] ={ "Good", "Bad", "Excellent!", "Average"};
            int nextInt = new Random().nextInt(4);
            Log.v(".java ",cmt[nextInt]);
            comment = db.createComment(cmt[nextInt]);
            adapter.add(comment);
            break;}
            case R.id.delete_button:{
                if(getListAdapter().getCount() > 0) {
                    comment = (Comment) getListAdapter().getItem(0);
                    db.deleteComment(comment);
                    adapter.remove(comment);
                }
                break;}
         }
         adapter.notifyDataSetChanged();
    }
    @Override
    protected void onResume(){
        db.getdb();
        super.onResume();
    }
    @Override
    protected void onPause(){
        db.close();
        super.onPause();
    }
}
