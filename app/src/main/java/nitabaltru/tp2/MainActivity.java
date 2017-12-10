package nitabaltru.tp2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DatabaseReference databaseReference;
    EditText editText;
    ImageButton button;
    MessageAdapter adapter;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        user = UserStorage.getUser(this);

        if (user.name.equals("noName") || user.email.equals("noEmail") ) {
            this.namePickerActivityLaunch();
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        editText = findViewById(R.id.inputEditText);
        button = findViewById(R.id.sendButton);
        adapter = new MessageAdapter(new ArrayList<Message>());

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("chat/messages");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Message> items = new ArrayList<>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    items.add(postSnapshot.getValue(Message.class));
                }
                adapter.setData(items);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(editText.getText().toString()))
                {
                    DatabaseReference newDbRef = databaseReference.push();
                    newDbRef.setValue(
                        new Message(editText.getText().toString(), user.name , user.email , System.currentTimeMillis()));
                    editText.setText("");
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                UserStorage.saveUserInfo(MainActivity.this, "noName", "noEmail");
                this.namePickerActivityLaunch();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void namePickerActivityLaunch(){
        Intent intent = new Intent(MainActivity.this, NamePickerActivity.class);
        startActivity(intent);
        finish();
    }
}