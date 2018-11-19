package cool.modcom.com.employeestracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import helper.SqLiteHelper;

public class AddEmployee extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);

        final EditText name = findViewById(R.id.name);
        final EditText location = findViewById(R.id.location);
        final EditText designation = findViewById(R.id.designation);
        Button save = findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //save to SQLite
                if (name.getText().length() == 0) {
                    name.setError("Empty name");
                } else if (location.getText().length() == 0) {
                    location.setError("Empty location");
                } else if (designation.getText().length() == 0) {
                    designation.setError("Empty designation");
                } else {
                    //use Helper to save
                    SqLiteHelper helper = new SqLiteHelper(AddEmployee.this);
                    helper.insertEmployee(name.getText().toString(),
                            location.getText().toString(),
                            designation.getText().toString());
                    name.setText("");
                    location.setText("");
                    designation.setText("");


                }//end else


            }
        });
    }
}
