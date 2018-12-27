package com.example.ddopik.phlogbusiness.ui.userprofile.view;
/**
 * Created by Abdalla_maged on 9/30/2018.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.*;
import com.example.ddopik.phlogbusiness.R;
import com.example.ddopik.phlogbusiness.Utiltes.GlideApp;
import com.example.ddopik.phlogbusiness.base.BaseActivity;
import com.example.ddopik.phlogbusiness.base.commonmodel.ImageObj;
import com.example.ddopik.phlogbusiness.base.widgets.CustomRecyclerView;
import com.example.ddopik.phlogbusiness.base.widgets.PagingController;
import com.example.ddopik.phlogbusiness.ui.userprofile.presenter.UserProfilePresenter;
import com.example.ddopik.phlogbusiness.ui.userprofile.presenter.UserProfilePresenterImpl;


import java.util.ArrayList;
import java.util.List;


public class UserProfileActivity extends BaseActivity implements UserProfileActivityView {


    public static String USER_ID="user_id";
    private String userID;
    private TextView userProfileLevel, userProfileUserName, userProfileFullName, userProfilePhotosCount, userProfileFolloweresCount, userProfileFollowingCount;
    private RatingBar userProfileRating;
    private ImageView userProfileImg;
    private CustomRecyclerView userProfilePhotosRv;
    private UserProfilePhotosAdapter userProfilePhotosAdapter;
    private UserProfilePresenter userProfilePresenter;
    private List<ImageObj> userPhotoList = new ArrayList<ImageObj>();
    private ProgressBar userProfilePhotosProgressBar;
    private Button followUser;
    private PagingController pagingController;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        initPresenter();
        initView();
        initListener();
    }


    @Override
    public void initPresenter() {
        userProfilePresenter = new UserProfilePresenterImpl(getBaseContext(), this);
    }


    @Override
    public void initView() {


        if(getIntent().getStringExtra(USER_ID) != null)
            this.userID=getIntent().getStringExtra(USER_ID);
            userProfileLevel = findViewById(R.id.user_profile_level);
            userProfileRating = findViewById(R.id.profile_rating);
            userProfileImg = findViewById(R.id.user_profile_img);
            userProfileFullName = findViewById(R.id.user_profile_full_name);
            userProfileUserName = findViewById(R.id.user_profile_username);
            userProfilePhotosCount = findViewById(R.id.photos_val);
            userProfileFolloweresCount = findViewById(R.id.followers_val);
            userProfileFollowingCount = findViewById(R.id.following_val);
            userProfilePhotosRv = findViewById(R.id.user_profile_photos);
            userProfilePhotosProgressBar = findViewById(R.id.user_profile_photos_progress_bar);
            followUser=findViewById(R.id.follow_user);
            userProfilePhotosAdapter = new UserProfilePhotosAdapter(this, userPhotoList);
            userProfilePhotosRv.setAdapter(userProfilePhotosAdapter);


            userProfilePresenter.getUserProfileData(userID); //todo static call here
            userProfilePresenter.getUserPhotos(userID,0);



    }


    private void initListener(){
        pagingController=new PagingController(userProfilePhotosRv) {
            @Override
            public void getPagingControllerCallBack(int page) {
                userProfilePresenter.getUserPhotos(userID,page+1);
            }
        };
        followUser.setOnClickListener(v -> {
            if(userID !=null)
            userProfilePresenter.followUser(userID);
        });
    }

    @Override
    public void showToast(String msg) {
        super.showToast(msg);
    }

    @Override
    public void viewUserProfileLevel(String userLevel) {
        userProfileLevel.setText(userLevel);
    }

    @Override
    public void viewUserProfileRating(int userRating) {
        userProfileRating.setRating(userRating);

    }

    @Override
    public void viewUserProfileProfileImg(String userImg) {
        GlideApp.with(this)
                .load(userImg)
                .placeholder(R.drawable.default_user_pic)
                .centerCrop()
                .into(userProfileImg);
    }

    @Override
    public void viewUserProfileFullName(String fullName) {
        userProfileFullName.setText(fullName);
    }

    @Override
    public void viewUserProfileUserName(String userName) {
        userProfileUserName.setText(userName);
    }

    @Override
    public void viewUserProfilePhotosCount(String photosCount) {
        userProfilePhotosCount.setText(photosCount);
    }

    @Override
    public void viewUserProfileFollowersCount(String followersCount) {
        userProfileFolloweresCount.setText(followersCount);
    }

    @Override
    public void viewUserProfileFollowingCount(String followingCount) {
        userProfileFollowingCount.setText(followingCount);
    }

    @Override
    public void viewUserPhotos(List<ImageObj> userPhotoList) {
        this.userPhotoList.addAll(userPhotoList);
        this.userPhotoList.addAll(userPhotoList);
        userProfilePhotosAdapter.notifyDataSetChanged();
    }

    @Override
    public void viewUserPhotosProgress(boolean state) {
        if (state) {
            userProfilePhotosProgressBar.setVisibility(View.VISIBLE);
        } else {
            userProfilePhotosProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showMessage(String msg) {
        super.showToast(msg);
    }
}