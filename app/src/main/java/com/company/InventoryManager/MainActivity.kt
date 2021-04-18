package com.company.InventoryManager

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var LOG_TAG: String? = MainActivity::class.qualifiedName;
    private var PREF_KEY: String? = MainActivity::class.java.`package`.toString();
    private var RC_SIGN_IN: Int = 123;
    private var SECRET_KEY: Int = 99;

    var mail: EditText? = null;
    var password: EditText? = null;

    private var preferences: SharedPreferences? = null;
    private var mAuth: FirebaseAuth? = null;
    private var mGoogleSignInClient: GoogleSignInClient? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mail = findViewById(R.id.editTextEmail);
        password = findViewById(R.id.editTextPassword);

        mAuth = Firebase.auth;

        var gso: GoogleSignInOptions =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    fun Login(view: View) {
        var mailStr: String = mail?.text.toString();
        var passwordStr: String = password?.text.toString();

        preferences = getSharedPreferences(PREF_KEY, MODE_PRIVATE);

        Log.i(LOG_TAG, "Logged In: " + mailStr + ", password: " + passwordStr);

        mAuth?.signInWithEmailAndPassword(mailStr, passwordStr)
            ?.addOnCompleteListener(this) { task: Task<AuthResult> ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(LOG_TAG, "signInUserWithEmail:success")
                    /*val user = mAuth.currentUser
                updateUI(user)*/
                    startSession();
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(LOG_TAG, "signInUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext,
                        "Authentication signInUserWithEmail failed: " + task.exception?.message,
                        Toast.LENGTH_SHORT
                    ).show()
                    /*updateUI(null)*/
                }
            }


    }

    fun Register(view: View) {
        var intent: Intent = Intent(this, RegisterActivity::class.java);
        intent.putExtra("SECRET_KEY", SECRET_KEY);
        //TODO
        startActivity(intent);
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.sign_in_button -> LoginWithGoogle(v);
        }
    }

    fun startSession(){
        var intent: Intent = Intent(this, InventoryActivity::class.java);
        //intent.putExtra("SECRET_KEY", SECRET_KEY);
        startActivity(intent);
    }

    fun LoginWithGoogle(view: View) {
        var signInIntent:Intent = mGoogleSignInClient!!.signInIntent;
        startActivityForResult(signInIntent, RC_SIGN_IN);
        Log.w(LOG_TAG, "Login with Google starting..................");
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN){
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data);
            val account: GoogleSignInAccount? = task.result;

            if (account == null){
                Log.w(LOG_TAG, "Google sign in failed!");
            } else{
                Log.w(LOG_TAG, "Google sign in success: " + account.id);
                firebaseAuthWithGoogle(account.idToken!!)
            }
        }
    }

    fun firebaseAuthWithGoogle(idToken: String){
        var credential: AuthCredential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth?.signInWithCredential(credential)?.addOnCompleteListener(this) { task: Task<AuthResult> ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Log.d(LOG_TAG, "signInUserWithGoogle:success")
                /*val user = mAuth.currentUser
            updateUI(user)*/
                startSession();
            } else {
                // If sign in fails, display a message to the user.
                Log.w(LOG_TAG, "signInUserWithGoogle:failure", task.exception)
                Toast.makeText(
                    baseContext,
                    "Authentication signInUserWithGoogle failed: " + task.exception?.message,
                    Toast.LENGTH_SHORT
                ).show()
                /*updateUI(null)*/
            }
        }
    }
}