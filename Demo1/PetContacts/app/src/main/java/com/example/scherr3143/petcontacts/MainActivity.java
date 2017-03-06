package com.example.scherr3143.petcontacts;

import android.support.annotation.BoolRes;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ContextMenu;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Database and Adapter objects
    DBHelper dbHelper;
    ArrayAdapter<Pet> arrayAdapter;

    List<Pet> petArrayList = new ArrayList<Pet>();

    //Pet contact data entry screen
    Button addContactBTN;
    ImageView inputPhotoId;
    EditText inputPetName;
    EditText inputPetDetails;
    EditText inputPhoneNumber;
    Drawable noPetImage;
    Uri defaultImage = Uri.parse("android:resource://com.example.scherr3143/drawable.none.png");

    Boolean newEntry = true;

    //Pet listing screen
    ListView petListView;
    ImageView listViewPhoto;
    TextView listViewName;
    TextView listViewDetails;
    TextView listViewPhone;

    TabHost tabHost;
    int contactIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Step 1: set up the database
        dbHelper = new DBHelper(getApplicationContext());

        //Step 2: Reference UI components from layout
        addContactBTN = (Button) findViewById(R.id.addBTN);
        inputPetName = (EditText) findViewById(R.id.memberName);
        inputPetDetails = (EditText) findViewById(R.id.memberDetail);
        inputPhoneNumber = (EditText) findViewById(R.id.memberPhoneNumber);
        inputPhotoId = (ImageView) findViewById(R.id.memberPhoto);
        noPetImage = inputPhotoId.getDrawable();
        petListView = (ListView) findViewById(R.id.listView1);

        //Step 3: Delete pet contact
        registerForContextMenu(petListView);
        petListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                contactIndex = position;
                return false;
            }
        });

        //Step 4: Create action tabs and add pet information
        tabHost = (TabHost) findViewById(R.id.tabHost1);
        tabHost.setup();
        TabHost.TabSpec tabSpec = tabHost.newTabSpec("add pet information");
        tabSpec.setContent(R.id.tabInfo);
        tabSpec.setIndicator("add pet information");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("view all pets");
        tabSpec.setContent(R.id.tabList);
        tabSpec.setIndicator("view all pets");
        tabHost.addTab(tabSpec);

        //Step 5: A pet can be added once user  has entered a name
        inputPetName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                addContactBTN.setEnabled(String.valueOf(inputPetName.getText()).trim().length() > 0);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        //Step 6: Listener events for photo selection and buttons
        inputPhotoId.setOnClickListener(getPhotoFromGallery);
        addContactBTN.setOnClickListener(recordPetInformation);

        //Step 7: Populate the database
        if (dbHelper.getContactsCount() != 0) {
            petArrayList.addAll(dbHelper.getAllContacts());
        }

        populateList();
    }

    //Activate an Intent to choose a photo  from the gallery
    private final View.OnClickListener getPhotoFromGallery = new View.OnClickListener(){
        public void onClick(View v){
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);

            startActivityForResult(Intent.createChooser(intent,"Select Contact Image"), 1);
        }
    };

    //Add pet record to the database
    private final View.OnClickListener recordPetInformation = new View.OnClickListener(){
        public void onClick(View v){
            Pet contact = new Pet(dbHelper.getContactsCount(), String.valueOf(inputPetName.getText().toString()),
                String.valueOf(inputPetDetails.getText().toString()),
                String.valueOf(inputPhoneNumber.getText().toString()),
                defaultImage);
            if(!contactExists(contact)){
                dbHelper.createContact(contact);
                petArrayList.add(contact);
                arrayAdapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), inputPetName.getText().toString()
                        + " has been added.", Toast.LENGTH_SHORT).show();
                newEntry = true;
                onResume();
                return;
            }
            Toast.makeText(getApplicationContext(), String.valueOf(inputPetName.getText())
                    + " has already been added. Use another name.", Toast.LENGTH_LONG).show();
        }
    };

    //Context menu, Delete a Pet
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu,view,menuInfo);

        menu.setHeaderIcon(R.mipmap.ic_launcher);
        menu.setHeaderTitle("Contact ...");
        menu.add(Menu.NONE, 1, Menu.NONE, "Delete Contact");
    }
    public boolean onContextItemSelected(MenuItem item){
        dbHelper.deleteContact(petArrayList.get(contactIndex));
        petArrayList.remove(contactIndex);
        arrayAdapter.notifyDataSetChanged();
        return super.onContextItemSelected(item);
    }
    private boolean contactExists(Pet member){
        String first = member.getPhone();
        int contactCount = petArrayList.size();

        for(int i = 0; i < contactCount; i++){
            if(first.compareToIgnoreCase(petArrayList.get(i).getPhone()) == 0){
                return true;
            }
        }
        return false;
    }

    //Intent returns a photo selected from the photo gallery
    public void onActivityResult(int reqCode, int resCode, Intent data){
        if(resCode == RESULT_OK){
            if(reqCode == 1){
                newEntry = false;
                defaultImage = data.getData();
                inputPhotoId.setImageURI(data.getData());
            }
        }
    }

    private void populateList(){
        arrayAdapter = new ContactListAdapter();
        petListView.setAdapter(arrayAdapter);
    }

    private class ContactListAdapter extends ArrayAdapter<Pet>{
        public ContactListAdapter(){
            super(getApplicationContext(), R.layout.listview_item, petArrayList);
        }

        public View getView(int position, View view, ViewGroup parent){
            if(view == null)
                view = getLayoutInflater().inflate(R.layout.listview_item, parent, false);

            Pet currentContact = petArrayList.get(position);

            listViewName = (TextView) view.findViewById(R.id.textViewName);
            listViewDetails = (TextView) view.findViewById(R.id.textViewDetail);
            listViewPhone = (TextView) view.findViewById(R.id.textViewPhone);
            listViewPhoto = (ImageView) view.findViewById(R.id.memberPhoto);

            listViewName.setText(currentContact.getName());
            listViewDetails.setText(currentContact.getDetail());
            listViewPhone.setText(currentContact.getPhone());
            listViewPhoto.setImageURI(currentContact.getPhotoURI());

            return view;
        }
    }

    public void onResume(){
        super.onResume();
        //Clear out pet information if it is a new entry
        if(newEntry){
            inputPetName.setText("");
            inputPetDetails.setText("");
            inputPhoneNumber.setText("");
            inputPhotoId.setImageDrawable(noPetImage);
        }
    }
}
