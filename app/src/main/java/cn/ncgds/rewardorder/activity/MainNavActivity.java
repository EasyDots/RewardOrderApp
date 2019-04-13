package cn.ncgds.rewardorder.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import cn.ncgds.rewardorder.R;

public class MainNavActivity extends AppCompatActivity {

    private ImageView mTextMessage;
    private FrameLayout flContent;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setVisibility(View.VISIBLE);
                    flContent.setVisibility(View.GONE);

                    Glide.with(MainNavActivity.this).load(R.drawable.img_index).into(mTextMessage);
                    // mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setVisibility(View.VISIBLE);
                    flContent.setVisibility(View.GONE);

                    // mTextMessage.setText(R.string.title_dashboard);
                    Glide.with(MainNavActivity.this).load(R.drawable.img_home).into(mTextMessage);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setVisibility(View.VISIBLE);
                    flContent.setVisibility(View.GONE);

                    Glide.with(MainNavActivity.this).load(R.drawable.img_msg).into(mTextMessage);

                    //  mTextMessage.setText(R.string.title_notifications);
                    return true;
                case R.id.navigation_my:
                    //Glide.with(MainNavActivity.this).load(R.drawable.img_my).into(mTextMessage);
                    View v = View.inflate(MainNavActivity.this,R.layout.layout_my,null);
                    flContent.addView(v);
                    mTextMessage.setVisibility(View.GONE);
                    flContent.setVisibility(View.VISIBLE);

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
        Toast.makeText(this, "version new", Toast.LENGTH_SHORT).show();
        mTextMessage = (ImageView) findViewById(R.id.ivContent);
        flContent = findViewById(R.id.fl_content);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
