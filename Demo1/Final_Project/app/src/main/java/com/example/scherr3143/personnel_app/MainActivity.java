package com.example.scherr3143.personnel_app;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Database and Adapter objects
    DBHelper dbHelper;
    ArrayAdapter<Contact> arrayAdapter;
    List<Contact> contactArrayList = new ArrayList<Contact>();
    Boolean newEntry = true;

    //Contact data entry screen
    Button addContactBTN;
    Button deleteContactBTN;
    Button clearContactBTN;
    ImageView inputPhotoId;
    EditText inputContactName;
    EditText inputContactEmail;
    EditText inputPhoneNumber;
    EditText inputAddress;
    Drawable noImage;
    Uri defaultImage = Uri.parse("android:resource://com.example.scherr3143/drawable.noimage.png");

    //Contact listing screen
    ListView contactListView;
    ImageView listViewPhoto;
    TextView listViewName;

    //int contactIndex;
    int contactPosition;
    Contact curSelectedContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set up the database
        dbHelper = new DBHelper(getApplicationContext());

        //Reference UI components from layout
        addContactBTN = (Button) findViewById(R.id.btnSaveChanges);
        deleteContactBTN = (Button) findViewById(R.id.btnDeleteContact);
        clearContactBTN = (Button) findViewById(R.id.btnClearContact);
        inputContactName = (EditText) findViewById(R.id.editName);
        inputContactEmail = (EditText) findViewById(R.id.editEmail);
        inputAddress = (EditText) findViewById(R.id.editAddress);
        inputPhoneNumber = (EditText) findViewById(R.id.editPhone);
        inputPhotoId = (ImageView) findViewById(R.id.uriContactPhoto);
        noImage = inputPhotoId.getDrawable();
        contactListView = (ListView) findViewById(R.id.listView1);

        inputContactName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                addContactBTN.setEnabled(String.valueOf(inputContactName.getText()).trim().length() > 0);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        inputPhotoId.setOnClickListener(getPhotoFromGallery);
        addContactBTN.setOnClickListener(recordContactInformation);
        clearContactBTN.setOnClickListener(clearContactInformation);
        deleteContactBTN.setOnClickListener(deleteContactInformation);

        //Populate the database
        if (dbHelper.getContactsCount() != 0) {
            contactArrayList.addAll(dbHelper.getAllContacts());
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

    private final View.OnClickListener clearContactInformation = new View.OnClickListener(){
        public void onClick(View v){
            newEntry = true;
            onResume();
        }
    };
    //Add contact record to the database
    private final View.OnClickListener recordContactInformation = new View.OnClickListener(){
        public void onClick(View v){
            Contact contact = new Contact(dbHelper.getContactsCount(), String.valueOf(inputContactName.getText().toString()),
                    String.valueOf(inputContactEmail.getText().toString()),
                    String.valueOf(inputAddress.getText().toString()),
                    String.valueOf(inputPhoneNumber.getText().toString()),
                    defaultImage);
            if(!contactExists(contact)){
                dbHelper.createContact(contact);
                contactArrayList.add(contact);
                arrayAdapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), inputContactName.getText().toString()
                        + " has been added.", Toast.LENGTH_SHORT).show();
                newEntry = true;
                onResume();
                return;
            }
            else{
                //Contact temp = (Contact) v.getTag();
                //Log.d(String.valueOf(temp.getID()),"Contact ID");
                Contact contactU = new Contact(curSelectedContact.getID(), String.valueOf(inputContactName.getText().toString()),
                        String.valueOf(inputContactEmail.getText().toString()),
                        String.valueOf(inputAddress.getText().toString()),
                        String.valueOf(inputPhoneNumber.getText().toString()),
                        defaultImage);
                dbHelper.updateContact(contactU);
                contactArrayList.clear();
                contactArrayList.addAll(dbHelper.getAllContacts());
                arrayAdapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), String.valueOf(inputContactName.getText())
                        + " has now been updated.", Toast.LENGTH_LONG).show();
                newEntry = true;
                onResume();
                return;
            }
        }
    };

    //Delete a contact
    private final View.OnClickListener deleteContactInformation = new View.OnClickListener(){
        public void onClick(View v) {
            dbHelper.deleteContact(curSelectedContact);
            contactArrayList.clear();
            contactArrayList.addAll(dbHelper.getAllContacts());
            arrayAdapter.notifyDataSetChanged();
            newEntry = true;
            onResume();
        }
    };

    private boolean contactExists(Contact member){
        String first = member.getPhone();
        int contactCount = contactArrayList.size();
        for(int i = 0; i < contactCount; i++){
            if(first.compareToIgnoreCase(contactArrayList.get(i).getPhone()) == 0){
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
        contactListView.setAdapter(arrayAdapter);
    }

    private class ContactListAdapter extends ArrayAdapter<Contact>{
        public ContactListAdapter(){
            super(getApplicationContext(), R.layout.fulllist_view, contactArrayList);
        }

        public View getView(int position, View view, ViewGroup parent){
            if(view == null)
                view = getLayoutInflater().inflate(R.layout.fulllist_view, parent, false);

            Contact currentContact = contactArrayList.get(position);

            listViewName = (TextView) view.findViewById(R.id.textViewName);
            listViewPhoto = (ImageView) view.findViewById(R.id.uriContactPhoto);

            listViewName.setText(currentContact.getName());
            listViewPhoto.setImageURI(currentContact.getPhotoUri());

            view.setTag(currentContact);
            //contactPosition = position;

            //attache onClick handler to view
            contactListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    curSelectedContact = (Contact) view.getTag();
                    inputContactName.setText(curSelectedContact.getName());
                    inputAddress.setText(curSelectedContact.getAddress());
                    inputPhoneNumber.setText(curSelectedContact.getPhone());
                    inputContactEmail.setText(curSelectedContact.getEmail());
                    inputPhotoId.setImageURI(curSelectedContact.getPhotoUri());
                }
            });
            return view;
        }
    }
    public void onResume(){
        super.onResume();
        //Clear out contact information if it is a new entry
        if(newEntry){
            inputContactName.setText("");
            inputContactEmail.setText("");
            inputAddress.setText("");
            inputPhoneNumber.setText("");
            inputPhotoId.setImageDrawable(noImage);
        }
    }

}