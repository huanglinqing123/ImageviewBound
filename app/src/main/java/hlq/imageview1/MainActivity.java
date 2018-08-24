package hlq.imageview1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import hlq.ImageViewBound;

public class MainActivity extends AppCompatActivity {

    private ImageViewBound imageViewBound;
    private ImageViewBound xiaoxi;
    private ImageViewBound wode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageViewBound = findViewById(R.id.shouye);
        xiaoxi = findViewById(R.id.xiaoxi);
        wode = findViewById(R.id.wode);
        imageViewBound.setMessageNum(1);
        xiaoxi.setMessageNum(10);
        wode.setMessageNum(51);
    }
}
