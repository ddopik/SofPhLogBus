package com.example.ddopik.phlogbusiness.ui.userprofile.view;

import com.example.ddopik.phlogbusiness.base.commonmodel.ImageObj;

import java.util.List;

public interface UserProfileActivityView {

    void viewUserProfileLevel(String userLevel); //
    void viewUserProfileRating(int userRating); //
    void viewUserProfileProfileImg(String userImg); //
    void viewUserProfileFullName(String fullName); //
    void viewUserProfileUserName(String userName); //
    void viewUserProfilePhotosCount(String photosCount);//
    void viewUserProfileFollowersCount(String followersCount); //
    void viewUserProfileFollowingCount(String followingCount); //
    void viewUserPhotos(List<ImageObj> userPhotoList);
    void viewUserPhotosProgress(boolean state);
    void showMessage(String msg);
}