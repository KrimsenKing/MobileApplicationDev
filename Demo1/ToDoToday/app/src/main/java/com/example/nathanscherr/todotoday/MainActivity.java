package com.example.nathanscherr.todotoday;

import android.support.v7.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    protected DBHelper mDBHelper;
    private List<ToDo_Item> list;
    private MyAdapter adapt;
    private EditText myTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Step 1: Launch layout for main activity
        setContentView(R.layout.activity_main);

        //Step 2: Establish refrences to ui elements located in layout
        myTask = (EditText) findViewById(R.id.editText1);

        //Step 3: Create the database
        DBHelper database = new DBHelper(this);

        /*//Add five tasks to the database
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

        Log.v("DATABASE RECORDS", taskItemList);*/
    }

    protected void onResume(){
        super.onResume();
        list = mDBHelper.getAllTasks();
        adapt = new MyAdapter(this, R.layout.todo_item, list);
        ListView listTask = (ListView) findViewById(R.id.listView1);
        listTask.setAdapter(adapt);
    }

    //Button click event for adding a new task
    public void addTaskNow(View view){
        String s = myTask.getText().toString();
        if(s.isEmpty()){
            Toast.makeToast(getApplictaionContext(), "A TODO task must be entered.", Toast.LENGTH_SHORT).show();
        }
        else{
            //Build a new task item and add to task list
            ToDo_Item task = new ToDo_Item(s,0);
            mDBHelper.addToDoItem(task);

            //Clear the task edit view
            myTask.setText("");

            //Add task and set notification of changes
            adapt.add(task);
            adapr.notifyDataSetChanged();
        }
    }

    //Button click event for deleting all tasks
    public void clearTasks(View view){
        mDBHelper.clearAll(list);
        adapt.notifyDataSetChanged();
    }

    //Adapter
    private class MyAdapter extends ArrayAdapter<ToDo_Item>{
        Context context;
        List<ToDo_Item> taskList = new ArraayAdapter<ToDo_Item>();

        public myAdapter(Context c, int rId, List<ToDo_Item> objects){
            super(c,rId,objects);
            taskList = objects;
            context = c;
        }

        // ToDo Task Item view
        // This method defines the todo item that will be placed inside the view.
        // The checkbox state is the is_done status of the todo task and the checkbox text is the description of the todo task

        public View getView(int position, View convertView, ViewGroup parent){

            CheckBox isDoneChkBx = null;
            if(convertView == null){
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.todo_item, parent, false);

                isDoneChkBx = (CheckBox) convertView.findViewById(R.id.chkStatus);
                convertView.setTag(isDoneChkBx);

                isDoneChkBx.setClickListener(new View.onClickListener(){
                    public void onClick(View view){
                        CheckBox cb = (CheckBox) view;
                        ToDo_Item changeTask = (ToDo_Item) cb.getTag();
                        changeTask.setIsDone(cb.isChecked() == true ? 1 : 0);
                        mDBHelper.updateTask(changeTask);
                    }
                });
            }
            else{
                isDoneChkBx = (CheckBox) convertView.getTag();
            }
            ToDo_Item current = taskList.get(position);
            isDoneChkBx.setText(current.getDescription());
            isDoneChkBx.setChecked(current.getIsDone() == 1 ? true : false);
            isDoneChkBx.setTag(current);
            return convertView;
        }
    }
}