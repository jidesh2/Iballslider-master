package com.onroute.iballslider;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

public class settings_new extends AppCompatActivity {
ImageButton landscape,portrait;

TinyDB tb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_new);
tb=new TinyDB(this);
tb.putString("orientation","null");
landscape= findViewById(R.id.landscape);
landscape.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        tb.putString("orientation","landscape");
        Intent mainIntent = new Intent(settings_new.this,MainActivity.class );
        //   mainIntent.putExtra("hi", a);mainIntent.putExtra("news","null");
        // mainIntent.putExtra("title", x1.getText().toString());
        startActivity(mainIntent);
        finish();

    }
});
        portrait= findViewById(R.id.portrait);
        portrait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tb.putString("orientation","portrait");
                Intent mainIntent = new Intent(settings_new.this,MainActivity.class );
                //   mainIntent.putExtra("hi", a);mainIntent.putExtra("news","null");
                // mainIntent.putExtra("title", x1.getText().toString());
                startActivity(mainIntent);

finish();
            }
        });

    }

}
