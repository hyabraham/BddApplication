package bt.be.bddapplication.Controler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import bt.be.bddapplication.R;
import bt.be.bddapplication.db.DBHelper;
import bt.be.bddapplication.db.UserDAO;
import bt.be.bddapplication.model.User;

public class SubscribtionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribtion);
    }

    public void userCancelRegister(View v){

        Intent cancelIntent=new Intent(SubscribtionActivity.this,MainActivity.class);
        startActivity(cancelIntent);
    }
    public void userRegister(View v){

        EditText fname=(EditText)findViewById(R.id.txt_userFirstName);
        EditText lname=(EditText)findViewById(R.id.txt_userLastName);
        EditText email=(EditText)findViewById(R.id.txt_userMail);
        EditText password1=(EditText)findViewById(R.id.txt_userPassword);
        EditText password2=(EditText)findViewById(R.id.txt_userRePassword);

        String firstName,lastName,mail,mdp1,mdp2;
        int id;

        firstName=fname.getText().toString();
        lastName=lname.getText().toString();
        mail=email.getText().toString();
        mdp1=password1.getText().toString();
        mdp2=password2.getText().toString();

        String[] tabUser = new String[]{fname.getText().toString(), lname.getText().toString(), email.getText().toString(), password1.getText().toString()};
        User u = new User(firstName, lastName, mail, mdp1);

        UserDAO dao = new UserDAO(this);
        dao = dao.openWritable();
        dao.createUser(u);
        Log.i("USER CREATED ", "OK");



    }


}
