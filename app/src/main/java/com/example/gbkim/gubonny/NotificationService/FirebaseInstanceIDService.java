package com.example.gbkim.gubonny.NotificationService;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by gbkim on 18. 3. 13.
 */

public class FirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "MyFirebaseIDService";

    /*
    * Called if InstanceID token is updated. This may occur if the security of
    * the previous token had been compromised. Note that this is called when the Instance
    * is initially generated so this is where you wiykd retrieve the token
    * InstanceID 토큰이 업데이트 된 경우 호출됩니다.
    * 이전 토큰의 보안이 손상된 경우에 발생할 수 있습니다.
    * 이것은 InstanceID 토큰이 처음 생성 될 때 호출되어 토큰을 검색 할 곳입니다.*/

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);

        /*
        * If you want to send messages to this application instance or
        * manage this apps subscriptions on the server side,
        * send the Instance ID token to your app server.*/
        sendRegistrationToServer(refreshedToken);

    } // END refresh_token

    /*
    * Persist token to third-party servers.
    *
    * Modify this method to associate the user's FCM InstanceID token with any server-side account
    * maintained by your application
    *
    * @param token The new token. */
    private void sendRegistrationToServer(String refreshedToken) {
        // TODO : Implement this method to send token to your app server.
    }
}
