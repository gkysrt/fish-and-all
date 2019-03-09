package com.example.android.fishall;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import java.util.Calendar;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class AboutActivitiy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Element adsElement = new Element();
        adsElement.setTitle("Advertise here");

        View aboutPage = new AboutPage(this)
                .isRTL(false)
                .setImage(R.drawable.fish_and_all_logo_light_blue)
                .setDescription("This is demo version")
                .addItem(new Element().setTitle("Version 1.0"))
                .addItem(adsElement)
                .addGroup("Connect with me")
                .addEmail("gkysrt@gmail.com")
                .addFacebook("canberk.kandemir1")
                .addInstagram("janberkkandemir")
                .addGitHub("gkysrt")
                .addItem(createCopyright())
                .create();

        setContentView(aboutPage);
    }


    private Element createCopyright() {

        Element copyright = new Element();
        final String copyrightString = String.format("Copyright %d by Gökay Sert - Can Berk Kandemir", Calendar.getInstance().get(Calendar.YEAR));

        copyright.setTitle(copyrightString);
        copyright.setIconDrawable(R.mipmap.ic_launcher);
        copyright.setGravity(Gravity.CENTER);


        copyright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AboutActivitiy.this,copyrightString,Toast.LENGTH_LONG).show();
            }
        });

        return copyright;
    }


}
