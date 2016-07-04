package bt.be.bddapplication.Controler;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import bt.be.bddapplication.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void userRegister(View v){

        Intent registerIntent=new Intent(MainActivity.this,SubscribtionActivity.class);
        startActivity(registerIntent);
    }
}
