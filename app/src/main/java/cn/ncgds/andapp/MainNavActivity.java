package cn.ncgds.andapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MainNavActivity extends AppCompatActivity {

    private ImageView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Glide.with(MainNavActivity.this).load(R.drawable.img_index).into(mTextMessage);
                    // mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    // mTextMessage.setText(R.string.title_dashboard);
                    Glide.with(MainNavActivity.this).load(R.drawable.img_home).into(mTextMessage);
                    return true;
                case R.id.navigation_notifications:
                    Glide.with(MainNavActivity.this).load(R.drawable.img_msg).into(mTextMessage);

                    //  mTextMessage.setText(R.string.title_notifications);
                    return true;
                case R.id.navigation_my:
                    Glide.with(MainNavActivity.this).load(R.drawable.img_my).into(mTextMessage);

                    // mTextMessage.setText(R.string.title_my);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_nav);

        mTextMessage = (ImageView) findViewById(R.id.ivContent);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
