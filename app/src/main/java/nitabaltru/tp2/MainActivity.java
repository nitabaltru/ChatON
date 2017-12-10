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

/**
 * A class representing the main activity - the chat
 */
public class MainActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private EditText editText;
    private ImageButton button;
    private MessageAdapter adapter;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //to get the user name and email
        user = UserStorage.getUser(this);

        //if there is no name or/and no email it will change to the name picker activity
        if ("noName".equals(user.name) || "noEmail".equals(user.email) ) {
            this.namePickerActivityLaunch();
        }

        //to find all the wiews used on the main activity
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        editText = findViewById(R.id.inputEditText);
        button = findViewById(R.id.sendButton);
        adapter = new MessageAdapter(new ArrayList<Message>());

        //setting the Message Adapter
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        //setting the position of the recycler view (stack from end)
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);


        // getting the firebase database instance and setting up the listener
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

        //setting up a listener to the button to send the message
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

    /**
     * a method allowing to show the menu
     * @param menu the menu itself
     * @return true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    /**
     * a method giving the option of logout when clicking on the menu item "action_logout"
     * @param item the menuItem being selected
     * @return true
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                UserStorage.saveUserInfo(MainActivity.this, "noName", "noEmail");
                this.namePickerActivityLaunch();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * a private method to launch the name picker activity
     */
    private void namePickerActivityLaunch(){
        Intent intent = new Intent(MainActivity.this, NamePickerActivity.class);
        startActivity(intent);
        finish();
    }
}