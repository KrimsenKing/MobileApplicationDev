package com.example.nathanscherr.todotoday;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Experiment 1: Create the database
        DBHelper database = new DBHelper(this);

        //Add five tasks to the database
        database.addToDoItem(new ToDo_Item(1, "Read Hamlet", 1));
        database.addToDoItem(new ToDo_Item(2, "Study for exam", 1));
        database.addToDoItem(new ToDo_Item(3, "Call Andy and Sam", 0));
        database.addToDoItem(new ToDo_Item(4, "Create newsletter", 1));
        database.addToDoItem(new ToDo_Item(5, "Buy a dog", 0));

        //Display all the tasks in the table
        String taskItemList = "\n";
        ArrayList<ToDo_Item> taskList = database.getAllTaskItems();
        for(int i = 0; i < database.getTaskCount(); i++){
            ToDo_Item task = taskList.get(i);
            taskItemList += "\n" + task.getDescription() + "\t" + task.getIsDone();
        }
        Log.v("DATABASE RECORDS", taskItemList);

        //Experiment 2: Modify a record
        database.editToDoItem(new ToDo_Item(1, "Read newspaper", 1));

        //Experiment 3: Display a specific record
        ToDo_Item anItem = database.getToDo_Task(2);
        Log.v("DATABASE RECORDS", anItem.getDescription());

        //Experiment 4: Delete a record
        database.deleteTaskItem(new ToDo_Item(5, "Buy a dog", 0));

        //Display all the task items in the table
        taskItemList = "\n";
        for(int i = 0; i < database.getTaskCount(); i++){
            ToDo_Item task = taskList.get(i);
            taskItemList += "\n" + task.getDescription() + "\t" + task.getIsDone();
        }

        Log.v("DATABASE RECORDS", taskItemList);
    }
}