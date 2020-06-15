package com.udacity.urlvideodownloader;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.downloader.Error;
import com.downloader.OnCancelListener;
import com.downloader.OnDownloadListener;
import com.downloader.OnPauseListener;
import com.downloader.OnProgressListener;
import com.downloader.OnStartOrResumeListener;
import com.downloader.PRDownloader;
import com.downloader.PRDownloaderConfig;
import com.downloader.Progress;

public class MainActivity extends AppCompatActivity {

    private VideoView videoView;
    private Button btn_download;

    String url = "http://images-assets.nasa.gov/video/42_RethinkingAnAlienWorld/42_RethinkingAnAlienWorld~mobile.mp4";
    String dirPath = "MyVideo/";
    String fileName = "video123.mp4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Enabling database for resume support even after the application is killed:
        PRDownloaderConfig config = PRDownloaderConfig.newBuilder()
                .setDatabaseEnabled(true)
                .setReadTimeout(30_000)
                .setConnectTimeout(30_000)
                .build();
        PRDownloader.initialize(getApplicationContext(), config);


        btn_download = (Button) findViewById(R.id.download_btn);
        videoView = (VideoView) findViewById(R.id.videoView);
        Uri vidurl = Uri.parse("");
        videoView.setVideoURI(vidurl);
        videoView.start();



    }


    int downloadId = PRDownloader.download(url, dirPath, fileName)
            .build()
            .setOnStartOrResumeListener(new OnStartOrResumeListener() {
                @Override
                public void onStartOrResume() {

                }
            })
            .setOnPauseListener(new OnPauseListener() {
                @Override
                public void onPause() {

                }
            })
            .setOnCancelListener(new OnCancelListener() {
                @Override
                public void onCancel() {

                }
            })
            .setOnProgressListener(new OnProgressListener() {
                @Override
                public void onProgress(Progress progress) {

                }
            })
            .start(new OnDownloadListener() {
                @Override
                public void onDownloadComplete() {
                    Toast.makeText(MainActivity.this,
                            "Download Complete",Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onError(Error error) {
                    Toast.makeText(MainActivity.this,
                            "Error Downloading video",Toast.LENGTH_SHORT).show();
                }

            });
}
