package com.example.gbkim.gubonny.AsyncTask;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.gbkim.gubonny.R;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.net.URLConnection;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityAsyncTask extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.btn_activity_async_task)
    Button button;


    private String fileURL;

    int mSomeMemberVariable = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);

        ButterKnife.bind(this);

        button.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        DownloadFilesTask downloadFilesTask = new DownloadFilesTask(this);
        downloadFilesTask.execute(fileURL);
    }

    public static class DownloadFilesTask extends AsyncTask<String, String, Long> {
        private final WeakReference<ActivityAsyncTask> activityReference;

        private PowerManager.WakeLock mWakeLock;
        private ProgressDialog progressBar;
        private File path;
        private File outputFile;

        DownloadFilesTask(ActivityAsyncTask context) {
            activityReference = new WeakReference<>(context);
        }

        // 가장 먼저 호출 됌.
        // doInBackground 작업 시작 전 필요한 작업들을 여기서 해줌.
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // CPU가 잠들지 않도록 처리하고 ProgressBar 보여줌.
            PowerManager pm = (PowerManager) activityReference.get().getSystemService((Context.POWER_SERVICE));

            assert pm != null;
            mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, getClass().getName());

            mWakeLock.acquire(10 * 60 * 1000L /*10 minutes*/);

            progressBar.show();
        }

        @Override
        protected Long doInBackground(String... string_url) {
            int count;
            long FileSize = -1;
            InputStream input = null;
            OutputStream output = null;
            URLConnection connection;

            try {
                URL url = new URL(string_url[0]);
                connection = url.openConnection();
                connection.connect();


                //파일 크기를 가져옴
                FileSize = connection.getContentLength();

                //URL 주소로부터 파일다운로드하기 위한 input stream
                input = new BufferedInputStream(url.openStream(), 8192);

                path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                outputFile = new File(path, "Alight.avi"); //파일명까지 포함함 경로의 File 객체 생성

                // SD카드에 저장하기 위한 Output stream
                output = new FileOutputStream(outputFile);


                byte data[] = new byte[1024];
                long downloadedSize = 0;
                while ((count = input.read(data)) != -1) {
                    //사용자가 BACK 버튼 누르면 취소가능
                    if (isCancelled()) {
                        input.close();
                        return (long) -1;
                    }

                    downloadedSize += count;

                    if (FileSize > 0) {
                        float per = ((float) downloadedSize / FileSize) * 100;
                        String str = "Downloaded " + downloadedSize + "KB / " + FileSize + "KB (" + (int) per + "%)";
                        publishProgress("" + (int) ((downloadedSize * 100) / FileSize), str);

                    }

                    //파일에 데이터를 기록합니다.
                    output.write(data, 0, count);
                }
                // Flush output
                output.flush();

                // Close streams
                output.close();
                input.close();


            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            } finally {
                try {
                    if (output != null)
                        output.close();
                    if (input != null)
                        input.close();
                } catch (IOException ignored) {
                }

                mWakeLock.release();

            }
            return FileSize;
        }

        @Override
        protected void onProgressUpdate(String... progress) {
            super.onProgressUpdate(progress);

            progressBar.setIndeterminate(false);
            progressBar.setMax(100);
            progressBar.setProgress(Integer.parseInt(progress[0]));
            progressBar.setMessage(progress[1]);
        }

        @Override
        protected void onPostExecute(Long size) {
            super.onPostExecute(size);

            progressBar.dismiss();

            if (size > 0) {
                Toast.makeText(activityReference.get(), "다운로드 완료. 파일 크기 =" + size.toString(), Toast.LENGTH_LONG).show();

                Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                mediaScanIntent.setData(Uri.fromFile(outputFile));
                activityReference.get().sendBroadcast(mediaScanIntent);

//                playVideo(outputFile.getPath());

            } else {
                Toast.makeText(activityReference.get(), "다운로드 실패", Toast.LENGTH_LONG).show();
            }

            // get a reference to the activity if it is still there
            ActivityAsyncTask activity = activityReference.get();
            if (activity == null || activity.isFinishing()) return;

            // access Activity member variables
            activity.mSomeMemberVariable = 321;
        }
    }
}
