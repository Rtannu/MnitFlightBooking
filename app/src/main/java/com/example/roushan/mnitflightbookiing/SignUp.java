package com.example.roushan.mnitflightbookiing;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {
    TextView privacy;
    TextView test;
    EditText name;
    EditText email;
    EditText password;
    EditText con_password;
    Button button;
    DatebaseHelper datebaseHelper;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setTitle("Create an Account");
        //for back button on title
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        privacy =(TextView)findViewById(R.id.textView13);
        privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp.this,Privacy.class));
            }
        });


        datebaseHelper=new DatebaseHelper(this);
        //get information about user
        name=(EditText)findViewById(R.id.editText);
        test=(TextView)findViewById(R.id.textView7);
        email=(EditText)findViewById(R.id.editText5);
        password=(EditText)findViewById(R.id.editText2);
        con_password=(EditText)findViewById(R.id.editText6) ;
        button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((name.getText().toString()).equals("")||email.getText().toString().equals("")||password.getText().toString().equals("")||
                con_password.getText().toString().equals(""))
                {
                    if(!CheckUsername(name.getText().toString()))
                    {
                        Toast.makeText(SignUp.this,"Name can't be null",Toast.LENGTH_SHORT).show();
                    }

                    if(TextUtils.isEmpty(email.getText().toString()))
                    {
                        Toast.makeText(SignUp.this,"Email can't be null",Toast.LENGTH_SHORT).show();
                    }
                      if(TextUtils.isEmpty(password.getText().toString()))
                    {
                        Toast.makeText(SignUp.this,"password can't be null",Toast.LENGTH_SHORT).show();
                    }

                    if(TextUtils.isEmpty(con_password.getText().toString()))
                    {
                        Toast.makeText(SignUp.this,"confirm password can't be null",Toast.LENGTH_SHORT).show();
                    }


                }
                else {
                    if(!CheckUsername(name.getText().toString())) {
                        Toast.makeText(SignUp.this,"Numeric & special character not allowed",Toast.LENGTH_SHORT).show();

                       /* boolean result = datebaseHelper.signUp(name.getText().toString(), email.getText().toString(), password.getText().toString());
                        if (result)
                            Toast.makeText(SignUp.this, "data is inserted", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(SignUp.this, "data is not inserted", Toast.LENGTH_SHORT).show();*/
                    }
                    else  if(!CheckEmail(email.getText().toString()))
                    {
                        Toast.makeText(SignUp.this,"Email is not valid",Toast.LENGTH_SHORT).show();
                    }
                    else  if(password.getText().toString().length()<8)
                          {
                              Toast.makeText(SignUp.this,"password is too short",Toast.LENGTH_SHORT).show();
                          }

                    else  if(con_password.getText().toString().length()<8)
                    {
                        Toast.makeText(SignUp.this,"confirm password is too short",Toast.LENGTH_SHORT).show();
                    }
                    else if(!(password.getText().toString()).equals(con_password.getText().toString()))
                      {
                          Toast.makeText(SignUp.this,"password not matched",Toast.LENGTH_SHORT).show();
                      }
                    else if(datebaseHelper.CheckSignup(email.getText().toString()))
                      {
                          Toast.makeText(SignUp.this,"Email is already exits",Toast.LENGTH_SHORT).show();
                      }
                    else
                    {
                         boolean result = datebaseHelper.signUp(name.getText().toString(), email.getText().toString(), password.getText().toString());
                        if (result)
                        {
                            sharedPreferences=getSharedPreferences("login",MODE_PRIVATE);
                            SharedPreferences.Editor editor=sharedPreferences.edit();
                            editor.putString("email",email.getText().toString());
                            editor.putString("password",password.getText().toString());
                            editor.commit();
                            Toast.makeText(SignUp.this, "Sign successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SignUp.this,Profile.class));
                            finish();
                        }
                        else
                            Toast.makeText(SignUp.this, "Sign not successfully", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
    public void logIn(View view)
           {
              // login=(TextView)findViewById(R.id.textView8);
               startActivity(new Intent(SignUp.this,LogIn.class));
           }
    public void terms(View view)
           {
               startActivity(new Intent(SignUp.this,TermsAndCondition.class));
           }
     //for back button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private boolean CheckUsername(String Name) {

        Pattern pattern = Pattern.compile("[a-zA-Z]{1,40}");
        Matcher matcher = pattern.matcher(Name);
        return matcher.matches();
    }
    private boolean CheckPassword(String Password) {

        Pattern pattern = Pattern.compile("[0-9a-zA-Z!~#$%^&*?/,;:'`]{8,10}");
        Matcher matcher = pattern.matcher(Password);
        return matcher.matches();
    }

    private boolean CheckEmail(String email) {

        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
