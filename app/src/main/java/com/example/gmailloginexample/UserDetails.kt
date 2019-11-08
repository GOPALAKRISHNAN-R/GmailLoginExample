package com.example.gmailloginexample

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView

class UserDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details_user)

        val signOut = findViewById<Button>(R.id.sign_out)

        val profileName = findViewById<TextView>(R.id.profile_text)
        profileName.setText(intent.extras.getString("display_name"))

        val profileEmail = findViewById<TextView>(R.id.profile_email)
        profileEmail.setText(intent.extras.getString("email"))

        //for getting image from gmail
        val profileImage = findViewById<ImageView>(R.id.profile_image)
        Glide.with(getApplicationContext()).load(intent.extras.getString("pic"))
            .thumbnail(0.5f)
            .crossFade()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(profileImage);
        profileImage.setImageURI(intent.extras.getParcelable("pic"))

        val givenName = findViewById<TextView>(R.id.profile_givenName)
        givenName.setText(intent.extras.getString("given_name"))

        val familyName = findViewById<TextView>(R.id.profile_familyName)
        familyName.setText(intent.extras.getString("family_name"))

        val profileId = findViewById<TextView>(R.id.profile_id)
        profileId.setText(intent.extras.getString("id"))

        signOut.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }

    }

}

