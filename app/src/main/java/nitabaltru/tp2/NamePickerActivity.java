package nitabaltru.tp2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * A class representing the activity that allows the user to pick a name and an email
 * Created by nitabaltru on 04/12/2017.
 */
public class NamePickerActivity extends AppCompatActivity {

    EditText mNameEditText;
    EditText mEmailEditText;
    Button mSubmitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_namepicker);

        //get the view of each field
        mNameEditText = findViewById(R.id.mNameEditText);
        mEmailEditText = findViewById(R.id.mEmailEditText);
        mSubmitButton = findViewById(R.id.mSubmitButton);

        //listener to allow send the information and log in
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(mNameEditText.getText().toString()) && !TextUtils.isEmpty(mEmailEditText.getText().toString())) {
                    if (saveData()) {
                        goToMainActivity();
                    }
                }

            }
        });
    }

    /**
     * Set the data on the UserStorage
     * @return true
     */
    private boolean saveData() {
            UserStorage.saveUserInfo(NamePickerActivity.this, mNameEditText.getText().toString(), mEmailEditText.getText().toString());
            return true;
        }

    /**
     * method to launch the main activity
     */
    private void goToMainActivity() {
            Intent intent = new Intent(NamePickerActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
}
