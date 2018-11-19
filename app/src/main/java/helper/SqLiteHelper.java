package helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


// this helper will Add/search/update/delete employees
//CRUD OPERATIONS
//sqlite- BUILD IN DATABASE IN ANDROID

public class SqLiteHelper extends SQLiteOpenHelper {

    private static String DB_NAME = "EmpDb";             // name of the database
    private static int DB_VERSION = 1;                   // database version
    private static String EMPLOYEE_TABLE = "employee";   // employee table name

    //---------column names for employee table
    private static String NAME_COLUMN = "name";
    private static String LOCATION_COLUMN = "location";
    private static String DESIGNATION_COLUMN = "designation";

    //creation of User Table
    private static String USER_TABLE = "users";
    private static String FIRST_NAME = "first_name";
    private static String LAST_NAME = "last_name";
    private static String EMAIL = "email";
    private static String PASSWORD = "password";


    // Constructor for creating the Database EmpDb
    Context context;

    public SqLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }


    // the 2 methods below must be implemented (onCreate and onUpgrade)
    @Override
    public void onCreate(SQLiteDatabase db) {
        // creates table
        db.execSQL("CREATE TABLE IF NOT EXISTS " + EMPLOYEE_TABLE
                + "(" + NAME_COLUMN + " TEXT(50)," + LOCATION_COLUMN + " TEXT(50)," + DESIGNATION_COLUMN + " TEXT(50));");

        db.execSQL("CREATE TABLE IF NOT EXISTS " + USER_TABLE
                + "(" + FIRST_NAME + " TEXT(50)," + LAST_NAME + " TEXT(50)," + EMAIL + " TEXT(50)," + PASSWORD + " TEXT(50));");

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { // used when there has been change in Db structure
        // drop table on upgrade
        db.execSQL("DROP TABLE " + EMPLOYEE_TABLE);
        onCreate(db);// recreate the dropped table again
        db.execSQL("DROP TABLE " + USER_TABLE);
        onCreate(db);


    }

    //---------------**** Create/ add a record****------------------------------
    public void insertEmployee(String name, String location, String designation) {

        // Add an employee
        SQLiteDatabase database = this.getWritableDatabase();  // create an object of the current Db and Make it Writable so data can be added

        //========================== using NoSQL====================================

        //step 1: set the values
        ContentValues values = new ContentValues();
        values.put(NAME_COLUMN, name);
        values.put(LOCATION_COLUMN, location);
        values.put(DESIGNATION_COLUMN, designation);

        // step 2: Insert the set values into a table
        long response = database.insert(EMPLOYEE_TABLE, null, values); // insert the values and return a long to be used to check if values were saved

        // step 3: check if record has been saved using the response returned by the insert method
        if (response == -1) {
            //failed to save
            Toast.makeText(context, "Failed to save", Toast.LENGTH_SHORT).show();
        } else {
            // saved message
            Toast.makeText(context, "Successfully Saved" + response, Toast.LENGTH_SHORT).show();
        }

        //========================== using SQL========================================
        //database.execSQL("INSERT INTO "+EMPLOYEE_TABLE+"("+NAME_COLUMN+","+LOCATION_COLUMN+","+DESIGNATION_COLUMN+") VALUES('Joseph Mwangi,197 Lenana Place, IT Officer');");


    }// end of method

    //-------------------*****Read Employee****------------------------------------------
    public void readEmployee() {
        SQLiteDatabase database = this.getReadableDatabase(); // create an object of the current db and make it readable

    }

    public void insertUser(String first_name, String last_name, String email, String password) {
        SQLiteDatabase database = this.getWritableDatabase();
        //TODO check if user exists before registering

        ContentValues values = new ContentValues();
        values.put(FIRST_NAME, first_name);
        values.put(LAST_NAME, last_name);
        values.put(EMAIL, email);
        values.put(PASSWORD, password);


        long response = database.insert(USER_TABLE, null, values);

        if (response == -1) {

            Toast.makeText(context, "Failed to save", Toast.LENGTH_SHORT).show();
        } else {

            Toast mytoast = Toast.makeText(context, "Saved successfully", Toast.LENGTH_SHORT);
            mytoast.show();
        }


    }

    public void readUser() {

        SQLiteDatabase database = this.getReadableDatabase();
    }


}// end of class