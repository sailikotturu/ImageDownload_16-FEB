package com.example.sailik.imagedownload_16_feb;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;


public class MainActivity extends AppCompatActivity implements View.OnClickListener,LoadImageTask.Listener{

    private Button mButtonAsync;
    private Button mButtonPiccasso;
    private Button mButtonGlide;
    private ImageView mImage;
    String image_url_async;
    String image_url_pic;
    String image_url_glide;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Imagedownload_16_feb.setContext(this);

        mButtonAsync = (Button) findViewById(R.id.button_async);
        mButtonPiccasso = (Button) findViewById(R.id.button_picasso);
        mButtonGlide = (Button) findViewById(R.id.button_glide);
        mImage = (ImageView) findViewById(R.id.imageView);


        mButtonAsync.setOnClickListener(this);
        mButtonPiccasso.setOnClickListener(this);
        mButtonGlide.setOnClickListener(this);

        image_url_async = this.getResources().getString(R.string.async_url);
        image_url_pic = this.getResources().getString(R.string.picasso_url);
        image_url_glide = this.getResources().getString(R.string.glider_url);
    }


    @Override
    public void onImageLoaded(Bitmap bitmap) {
        Bitmap bitmap_scaled = resize(bitmap,300,300);
        mImage.setImageBitmap(bitmap_scaled);
    }

    @Override
    public void onError() {
        Toast.makeText(this, "Error Loading Image !", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_async:
                new LoadImageTask(this).execute(image_url_async);
                break;
            case R.id.button_picasso:
                Picasso.with(this)
                        .load(image_url_pic)
                        .resize(300,300)
                        .into(mImage);
                break;
            case R.id.button_glide:
                Glide.with(this).load(image_url_glide).into(mImage);
                break;
        }
    }

    private Bitmap resize(Bitmap bm, int w, int h)
    {
        int width = bm.getWidth();
        int height = bm.getHeight();
        int newWidth = w;
        int newHeight = h;
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);

        return resizedBitmap;
    }
}
