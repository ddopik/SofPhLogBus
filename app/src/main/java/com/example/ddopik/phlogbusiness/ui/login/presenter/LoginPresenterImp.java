package com.example.ddopik.phlogbusiness.ui.login.presenter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import com.androidnetworking.error.ANError;
import com.example.ddopik.phlogbusiness.R;
import com.example.ddopik.phlogbusiness.base.commonmodel.BaseErrorResponse;
import com.example.ddopik.phlogbusiness.base.commonmodel.Device;
import com.example.ddopik.phlogbusiness.base.commonmodel.ErrorMessageResponse;
import com.example.ddopik.phlogbusiness.network.BaseNetworkApi;
import com.example.ddopik.phlogbusiness.ui.login.model.LoginData;
import com.example.ddopik.phlogbusiness.ui.login.view.LoginView;
import com.example.ddopik.phlogbusiness.utiltes.CustomErrorUtil;
import com.example.ddopik.phlogbusiness.utiltes.PrefUtils;
import com.example.ddopik.phlogbusiness.utiltes.Utilities;
import com.google.gson.Gson;
import com.jaychang.sa.AuthCallback;
import com.jaychang.sa.SocialUser;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SuppressLint("CheckResult")
public class LoginPresenterImp implements LoginPresenter {

    private LoginView loginView;
    private Context context;

    public LoginPresenterImp(Context context, LoginView loginView) {
        this.loginView = loginView;
        this.context = context;
    }

    private static final String TAG = LoginPresenterImp.class.getSimpleName();

    @Override
    public void signInNormal(HashMap<String, String> loginData) {
        loginView.showLoginProgress(true);
        loginData.put("firebase_token", PrefUtils.getFirebaseToken(context));
        BaseNetworkApi.LoginUserNormal(loginData)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(loginResponse -> {
                    loginView.showLoginProgress(false);
                    sendFirebaseToken();
                    saveBrand(loginResponse.getData());
                }, throwable -> {
                    loginView.showLoginProgress(false);
                    if (throwable instanceof ANError) {
                        ANError error = (ANError) throwable;
                        if (error.getErrorCode() == BaseNetworkApi.STATUS_BAD_REQUEST) {
                            ErrorMessageResponse errorMessageResponse = new Gson().fromJson(error.getErrorBody(), ErrorMessageResponse.class);
                            for (BaseErrorResponse e : errorMessageResponse.errors) {
                                if (e.code.equals(BaseNetworkApi.ERROR_VERIFICATION)) {
                                    loginView.showResendVerificationRequest();
                                    break;
                                }
                            }
                        }
                    }
                    CustomErrorUtil.Companion.setError(context, TAG, throwable);
                });
    }

    private void sendFirebaseToken() {
        BaseNetworkApi.updateFirebaseToken(new Device(Utilities.getDeviceName(), true, PrefUtils.getFirebaseToken(context)), PrefUtils.getBrandToken(context))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    loginView.showLoginProgress(false);
                    if (response == null)
                        return;
                    PrefUtils.setFirebaseTokenSentToServer(context, true);
                    PrefUtils.setIsFirstLaunch(context, false);


                    if (PrefUtils.isFirstLaunch(context)) {
                        loginView.navigateToPickProfilePhoto();
                    } else {
                        loginView.navigateToHome();
                    }
                }, throwable -> {
                    loginView.showLoginProgress(false);
                    PrefUtils.setFirebaseTokenSentToServer(context, false);
                    if (PrefUtils.isFirstLaunch(context)) {
                        loginView.navigateToPickProfilePhoto();
                    } else {
                        loginView.navigateToHome();
                    }
                    CustomErrorUtil.Companion.setError(context, TAG, throwable.toString());
                });
    }
//
//    @Override
//    public void signInWithGoogle() {
////        GoogleSignInOptions.SCOPE_EMAIL
//        List<String> scopes = new ArrayList<String>();
//        scopes.add("email");
//        scopes.add("profile");
//        scopes.add("openid");
//
//        com.jaychang.sa.google.SimpleAuth.connectGoogle(scopes, new AuthCallback() {
//            @Override
//            public void onSuccess(SocialUser socialUser) {
//
//                Log.e(TAG, "userId:" + socialUser.toString());
////                Log.d(TAG, "email:" + socialUser.email);
//            }
//
//            @Override
//            public void onError(Throwable error) {
//                Log.e(TAG, "signInWithGoogle()--->" + error.getMessage());
//            }
//
//            @Override
//            public void onCancel() {
//                Log.e(TAG, "Canceled");
//            }
//        });
//
//    }
//
//    @Override
//    public void signInWithFaceBook() {
//        List<String> scopes = new ArrayList<>();
//        com.jaychang.sa.facebook.SimpleAuth.connectFacebook(scopes, new AuthCallback() {
//            @SuppressLint("CheckResult")
//            @Override
//            public void onSuccess(SocialUser socialUser) {
//
//                HashMap<String, String> parameter = new HashMap<String, String>();
//                parameter.put("userId", socialUser.userId);
//
//                parameter.put("accessToken", socialUser.accessToken);
//                parameter.put("username", socialUser.username);
//                parameter.put("pageLink", socialUser.pageLink);
//
//                parameter.put("fullName", socialUser.fullName);
//                parameter.put("facebook_id", socialUser.userId);
//                parameter.put("mobile_os", "Android");
//                parameter.put("mobile_model", Utilities.getDeviceName());
//                parameter.put("email", socialUser.email);
//                parameter.put("image_profile", socialUser.profilePictureUrl);
//
//                parameter.put("firebase_token", PrefUtils.getFirebaseToken(context));
//                processFaceBookUser(parameter);
//
//            }
//
//            @Override
//            public void onError(Throwable error) {
//                CustomErrorUtil.Companion.setError(context, TAG, error);
//            }
//
//            @Override
//            public void onCancel() {
//                Log.e(TAG, "Canceled");
//            }
//        });
//    }


//    @SuppressLint("CheckResult")
//    private void processFaceBookUser(HashMap<String, String> data) {
//        BaseNetworkApi.socialLoginFacebook(data)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(socialLoginResponse -> {
//                    if (socialLoginResponse.state.equals(BaseNetworkApi.STATUS_OK)) {
//                        PrefUtils.setLoginState(context, true);
//                        PrefUtils.setBrandToken(context, socialLoginResponse.token.get(0));
//                        loginView.navigateToHome();
//                    } else {
//                        loginView.showMessage(context.getResources().getString(R.string.error_login));
////                        Log.e(TAG, "processFaceBookUser() Error--->" + socialLoginResponse.state +"  "+ socialLoginResponse.msg) ;
//                    }
//                }, throwable -> {
//                    CustomErrorUtil.Companion.setError(context, TAG, throwable);
//                });
//    }


    private void saveBrand(LoginData loginData) {
        PrefUtils.setLoginState(context, true);
        PrefUtils.setBrandID(context, loginData.id);
        PrefUtils.setBrandToken(context, loginData.token);
        PrefUtils.setBrandHash(context, loginData.hash);
        PrefUtils.setIsBrand(context, loginData.isBrand);
        PrefUtils.setIsPhoneVerified(context, loginData.isPhoneVerified);
        PrefUtils.setBrandStatus(context, loginData.brandStatus);
        PrefUtils.setBrandSetUp(context, loginData.isSetupBrand);
        PrefUtils.setIsMailVerified(context, loginData.isEmailVerified);
        PrefUtils.setBrandText(context, loginData.isBrandText);


    }

    @Override
    public Observable<Boolean> forgotPassword(Context context, String email) {
        return BaseNetworkApi.forgotPassword(email)
                .map(response -> response != null);
    }

    @SuppressLint("CheckResult")
    @Override
    public void sendVerificationRequest(String email) {
        BaseNetworkApi.requestVerification(email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(success -> {
                    loginView.showMessage(context.getString(R.string.veification_request_sent));
                }, throwable -> {
                    if (throwable instanceof ANError) {
                        ANError error = (ANError) throwable;
                        if (error.getErrorCode() == BaseNetworkApi.STATUS_BAD_REQUEST) {
                            ErrorMessageResponse errorMessageResponse = new Gson().fromJson(error.getErrorBody(), ErrorMessageResponse.class);
                            for (BaseErrorResponse e : errorMessageResponse.errors) {
                                switch (e.code) {
                                    case BaseNetworkApi.ERROR_VALIDATION:
                                    case BaseNetworkApi.ERROR_VERIFICATION:
                                        loginView.showMessage(e.message);
                                        break;
                                }
                            }
                        }
                    }
                    CustomErrorUtil.Companion.setError(context, TAG, throwable);
                });
    }
}
