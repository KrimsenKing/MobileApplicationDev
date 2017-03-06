package com.example.nathanscherr.todotoday;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

/**
 * Created by nathanscherr on 2017-03-05.
 */

public class DBHelper extends SQLiteOpenHelper{

    //Define the database and table
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ToDo_Today";
    private static final String DATABASE_TABLE = "ToDo_Items";

    //Define the column names for the ToDo_Items table
    private static final String KEY_TASK_ID = "_id";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_IS_DONE = "is_done";

    //Count the number of tasks in the list
    private int taskCount;

    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase database){
        String sqlStatement = "CREATE TABLE " + DATABASE_TABLE + "(" + KEY_TASK_ID + " INTEGER PRIMARY KEY, "
                + KEY_DESCRIPTION + " TEXT, " + KEY_IS_DONE + " INTEGER)";
        database.execSQL(sqlStatement);
        taskCount = 0;
    }

    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){
        database.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(database);
    }

    //Add, Edit, and Delete database operations

    //Add a task to the database
    public void addToDoItem(ToDo_Item task){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        taskCount++;
        //Add KEY_VALUE pair information for the task count
        values.put(KEY_TASK_ID, taskCount);

        //Add KEY_VALUE pair information for the task description
        values.put(KEY_DESCRIPTION, task.getDescription());

        //Add KEY_VALUE pair information for is_done
        //0 = Not Done, 1 = Done
        values.put(KEY_IS_DONE, task.getIsDone());

        //Insert database row into the table
        db.insert(DATABASE_TABLE, null, values);

        //Close the database connection
        db.close();
    }

    //Edit a task in the database
    public void editToDoItem(ToDo_Item task){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_DESCRIPTION, task.getDescription());
        values.put(KEY_IS_DONE, task.getIsDone());

        db.update(DATABASE_TABLE, values, KEY_TASK_ID + " = ?", new String[]{String.valueOf(task.getId())});

        db.close();
    }

    //Return a specific task from the database
    public ToDo_Item getToDo_Task(int id){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DATABASE_TABLE, new String[]{KEY_TASK_ID, KEY_DESCRIPTION, KEY_IS_DONE},
                KEY_TASK_ID + " = ?", new String[]{String.valueOf(id)}, null, null, null, null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        ToDo_Item task = new ToDo_Item(cursor.getInt(0), cursor.getString(1), cursor.getInt(2));
        db.close();
        return task;
    }

    //Delete a specific task from the database
    public void deleteTaskItem(ToDo_Item task){

        SQLiteDatabase db = this.getReadableDatabase();

        //Delete the table row
        db.delete(DATABASE_TABLE, KEY_TASK_ID + " = ?", new String[]{String.valueOf(task.getId())});
        db.close();
    }

    public int getTaskCount(){
        return taskCount;
    }

    //Return array list from database
    public ArrayList<ToDo_Item> getAllTaskItems(){

        ArrayList<ToDo_Item> taskList = new ArrayList<ToDo_Item>();
        String queryList = "SELECT * FROM " + DATABASE_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryList, null);

        //Collect each row in the database table
        if(cursor.moveToFirst()){
            do{
                ToDo_Item task = new ToDo_Item();
                task.setId(cursor.getInt(0));
                task.setDescription(cursor.getString(1));
                task.setIsDone(cursor.getInt(2));

                taskList.add(task);
            } while (cursor.moveToNext());
        }
        return taskList;
    }
}
