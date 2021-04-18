package com.company.InventoryManager

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {
    private var LOG_TAG: String? = RegisterActivity::class.qualifiedName;
    private var PREF_KEY: String? = MainActivity::class.java.`package`.toString();
    private var SECRET_KEY: Int = 99;

    var usernameEditText: EditText? = null;
    var userEmailEditText: EditText? = null;
    var passwordEditText: EditText? = null;
    var passwordAgainEditText: EditText? = null;
    var radioGroup: RadioGroup? = null;

    private var preferences: SharedPreferences? = null;
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        //setSupportActionBar(findViewById(R.id.toolbar))

        usernameEditText = findViewById(R.id.usernameEditText);
        userEmailEditText = findViewById(R.id.userEmailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        passwordAgainEditText = findViewById(R.id.passwordAgainEditText);
        radioGroup = findViewById(R.id.accountTypeGroup);
        radioGroup?.check(R.id.customerRadioButton);

        var secret_key:Int = intent.getIntExtra("SECRET_KEY", 0);

        if (secret_key != 99){
            finish();
        }

        preferences = getSharedPreferences(PREF_KEY, MODE_PRIVATE);
        var username: String? = preferences?.getString("username", "");
        var password: String? = preferences?.getString("password", "");

        usernameEditText?.setText(username);
        passwordEditText?.setText(password);
        passwordAgainEditText?.setText(password);

        mAuth = Firebase.auth;

        /*findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }*/
    }

    fun register(view: View) {
        var usernameStr:String = usernameEditText?.text.toString();
        var emailStr:String = userEmailEditText?.text.toString();
        var passwordStr:String = passwordEditText?.text.toString();
        var passwordAgainStr:String = passwordAgainEditText?.text.toString();

        if (!passwordStr.equals(passwordAgainStr)){
            Log.e(LOG_TAG, "Passwords aren't matching!");
            return;
        }

        Log.i(LOG_TAG, "Logged In: " + usernameStr + ", email: " + emailStr);

        var checkedId: Int? = radioGroup?.checkedRadioButtonId;
        var radioButton: RadioButton? = checkedId?.toInt()?.let { radioGroup?.findViewById(it) };
        var accounType:String = radioButton?.text.toString();

        mAuth.createUserWithEmailAndPassword(emailStr, passwordStr).addOnCompleteListener(this) { task: Task<AuthResult> ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Log.d(LOG_TAG, "createUserWithEmail:success")
                /*val user = mAuth.currentUser
                updateUI(user)*/
                startSession();
            } else {
                // If sign in fails, display a message to the user.
                Log.w(LOG_TAG, "createUserWithEmail:failure", task.exception)
                Toast.makeText(
                        baseContext, "Authentication createUserWithEmail failed: " + task.exception?.message,
                        Toast.LENGTH_SHORT
                ).show()
                /*updateUI(null)*/
            }

        }
    }
    fun cancel(view: View) {
        finish();
    }

    fun startSession(){
        var intent: Intent = Intent(this, InventoryActivity::class.java);
        //intent.putExtra("SECRET_KEY", SECRET_KEY);
        startActivity(intent);
    }
}