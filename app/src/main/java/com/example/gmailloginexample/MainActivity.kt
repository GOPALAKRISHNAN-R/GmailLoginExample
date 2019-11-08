package com.example.gmailloginexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.google.android.gms.common.api.ApiException

/** Simple Kotlin application for Google Sign-in integration
 * @author R.GOPALAKRISHNAN
 * @author www.rgopalakrishnanmca.simplesite.com
 **/
const val RC_SIGN_IN = 123

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        // Build a GoogleSignInClient with the options specified by gso.
        val mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        sign_in_button.visibility = View.VISIBLE
        tv_name.visibility = View.GONE
        sign_in_button.setSize(SignInButton.SIZE_STANDARD)

        sign_in_button.setOnClickListener {
            val signInIntent = mGoogleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }

    }


    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            val intent = Intent(applicationContext, UserDetails::class.java)
            intent.putExtra("display_name", account!!.displayName.toString())
            intent.putExtra("given_name", account!!.givenName.toString())
            intent.putExtra("family_name", account!!.familyName.toString())
            intent.putExtra("pic", account!!.photoUrl.toString())
            intent.putExtra("email", account!!.email.toString())
            intent.putExtra("id", account!!.id.toString())
            startActivity(intent)
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            e.printStackTrace()

        }
    }

}

/*
*   REFERENCE:
*   ==========
* 1)GOOGLE SIGNIN INTEGRATION
* ---------------------------
* https://codelabs.developers.google.com/codelabs/sign-in/index.html?index=..%2F..index#2
* https://codelabs.developers.google.com/codelabs/sign-in/index.html?index=..%2F..index#2
* https://developer.android.com/studio/publish/app-signing
* 2)PROFILE PICTURE
* ------------------
* http://www.androidhive.info/2014/02/android-login-with-google-plus-account-1/
*
* */