package com.example.sailik.imagedownload_16_feb;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by saili.k on 16-02-2017.
 */

public class LoadImageTask extends AsyncTask<String, Void, Bitmap> {
    public LoadImageTask(Listener listener) {

        mListener = listener;
    }

    public interface Listener{

        void onImageLoaded(Bitmap bitmap);
        void onError();
    }

    private Listener mListener;
    private ProgressDialog mProgressDialog;
    Context c = Imagedownload_16_feb.getContext();

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        mProgressDialog = new ProgressDialog(c);

        //mProgressDialog.setTitle("Download");

        mProgressDialog.setMessage("Loading...");
        mProgressDialog.setIndeterminate(false);

        mProgressDialog.show();
    }

    @Override
    protected Bitmap doInBackground(String... args) {

        try {

            return BitmapFactory.decodeStream((InputStream)new URL(args[0]).getContent());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {

        if (bitmap != null) {
            mProgressDialog.dismiss();
            mListener.onImageLoaded(bitmap);

        } else {
            mListener.onError();
        }
    }




}
