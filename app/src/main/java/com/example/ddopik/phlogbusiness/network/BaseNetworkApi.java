package com.example.ddopik.phlogbusiness.network;

import com.androidnetworking.common.Priority;
import com.example.ddopik.phlogbusiness.base.widgets.dialogs.addtoLightbox.model.AddImageToLightBoxResponse;
import com.example.ddopik.phlogbusiness.base.widgets.dialogs.addtoLightbox.model.LightBoxListResponse;
import com.example.ddopik.phlogbusiness.base.widgets.dialogs.addtoLightbox.model.RemoveImageToLightBoxResponse;
import com.example.ddopik.phlogbusiness.ui.album.model.AlbumPreviewImagesResponse;
import com.example.ddopik.phlogbusiness.ui.album.model.AlbumPreviewResponse;
import com.example.ddopik.phlogbusiness.ui.album.model.ImgCommentResponse;
import com.example.ddopik.phlogbusiness.ui.album.model.LikeImageResponse;
import com.example.ddopik.phlogbusiness.ui.brand.model.BrandInnerResponse;
import com.example.ddopik.phlogbusiness.ui.campaigns.addcampaign.model.SubmitCampaignResponse;
import com.example.ddopik.phlogbusiness.ui.campaigns.inner.model.CampaignInnerPhotosResponse;
import com.example.ddopik.phlogbusiness.ui.campaigns.inner.model.CampaignInnerResponse;
import com.example.ddopik.phlogbusiness.ui.campaigns.model.CampaignResponse;
import com.example.ddopik.phlogbusiness.ui.campaigns.model.FollowBrandResponse;
import com.example.ddopik.phlogbusiness.ui.campaigns.model.FollowCampaignResponse;
import com.example.ddopik.phlogbusiness.ui.lightbox.model.AddLighBoxResponse;
import com.example.ddopik.phlogbusiness.ui.lightbox.model.BrandLightBoxResponse;
import com.example.ddopik.phlogbusiness.ui.lightbox.model.DeleteLightBoxResponse;
import com.example.ddopik.phlogbusiness.ui.login.model.LoginResponse;
import com.example.ddopik.phlogbusiness.ui.login.model.SocialLoginResponse;
import com.example.ddopik.phlogbusiness.ui.notification.model.NotificationResponse;
import com.example.ddopik.phlogbusiness.ui.profile.model.BusinessProfileResponse;
import com.example.ddopik.phlogbusiness.ui.search.album.model.AlbumSearchResponse;
import com.example.ddopik.phlogbusiness.ui.search.album.model.SearchFiltersResponse;
import com.example.ddopik.phlogbusiness.ui.search.images.model.ImagesSearchResponse;
import com.example.ddopik.phlogbusiness.ui.search.profile.model.ProfileSearchResponse;
import com.example.ddopik.phlogbusiness.ui.signup.model.AllIndustriesResponse;
import com.example.ddopik.phlogbusiness.ui.signup.model.SignUpResponse;
import com.example.ddopik.phlogbusiness.ui.signup.model.UploadProfileImgResponse;
import com.example.ddopik.phlogbusiness.ui.social.model.SocialResponse;
import com.example.ddopik.phlogbusiness.ui.userprofile.model.FollowUserResponse;
import com.example.ddopik.phlogbusiness.ui.userprofile.model.UserPhotosResponse;
import com.example.ddopik.phlogbusiness.ui.userprofile.model.UserProfileResponse;
import com.example.ddopik.phlogbusiness.ui.welcome.model.WelcomeScreenResponse;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by abdalla-maged on 3/29/18.
 */

public class BaseNetworkApi {



    //Network Status
    public static String STATUS_OK = "200";
    public static String DEFAULT_USER_TYPE = "1";
    public static final int STATUS_BAD_REQUEST = 400;
    public static final int STATUS_401 = 401;
    public static final int STATUS_404 = 404;
    public static final int STATUS_500 = 500;
    public static String STATUS_ERROR = "405";
    public static final String ERROR_STATE_1 = "1";

    //
    private static final String BASE_URL = "http://178.128.162.10/public/api/business";
    private static final String BASE_URL_COMMON = "http://178.128.162.10/public/api/common";

    private static final String WELCOME_SLIDES_IMAGES = BASE_URL + "/photographer/init_slider";
    private static final String USER_SEARCH_FILTERS = BASE_URL_COMMON + "/filters/list";
    private static final String SEARCH_ALBUM = BASE_URL + "/album/search";
    private static final String SEARCH_IMAGES = BASE_URL + "/photo/search";
    private static final String ALL_INDUSTRY = BASE_URL_COMMON + "/industries/list";
    private static final String SIGN_UP_USER = BASE_URL + "/auth/signup";
    private static final String NORMAL_LOGIN = BASE_URL + "/auth/login";
    private static final String FACEBOOK_LOGIN_URL = BASE_URL + "/signup_facebook";
    private static final String INNER_BRAND_URL = BASE_URL + "/search_in_one_brands";
    private static final String BRAND_FOLLOW_URL = BASE_URL + "/join_photographer_brand";
    private static final String SOCIAL_DATA_URL = BASE_URL + "/social";
    private static final String GET_ALL_NOTIFICATION = BASE_URL + "/notification";
    private static final String ALL_COMPLETED_CAMPAIGN_URL = BASE_URL + "/campaign/old";
    private static final String ALL_RUING_CAMPAIGN_URL = BASE_URL + "/campaign/draft";
    private static final String ALL_DRAFT_CAMPAIGN_URL = BASE_URL + "/campaign/draft";
    private static final String CAMPAIGN_PHOTOS_URL = BASE_URL + "/get_photos_campaign";
    private static final String CAMPAIGN_DETAILS_URL = BASE_URL + "/detail_one_campaign";
    private static final String USER_PROFILE_URL = BASE_URL + "/get_info_photographer";
    private static final String USER_PROFILE_PHOTOS = BASE_URL + "/image_photographer";
    private static final String PHOTOGRAPHER_FOLLOW_USER_URL = BASE_URL + "/follow";
    private static final String PHOTOGRAPHER_SEARCH_URL = BASE_URL + "/photographer/list";
    private static final String GET_ALBUM_PREVIEW = BASE_URL +"/album/details";
    private static final String GET_ALBUM_IMAGES_PREVIEW = BASE_URL +"/album/photos";
    private static final String GET_IMAGE_COMMENT = BASE_URL + "/image/comments";
    private static final String SUBMIT_IMAGE_COMMENT = BASE_URL + "/image/comment/submit";
    private static final String LIKE_IMAGE = BASE_URL + "/photo/like";
    private static final String FOLLOW_CAMPAIGN_URL = BASE_URL + "/join_photographer_campaign";
    private static final String UPLOAD_PROFILE_IMG = BASE_URL + "/profile/upload";
    private static final String SUBMIT_CAMPAIGN_URL = BASE_URL + "/campaign/create";
    private static final String BRAND_PROFILE_URL = BASE_URL + "/profile";
    private static final String BRAND_LIGHT_BOX_URL = BASE_URL +"/lightBox/all";
    private static final String DELETE_LIGHT_BOX_URL = BASE_URL +"/lightBox/delete";
    private static final String ADD_LIGHT_BOX_URL = BASE_URL +"/lightBox/add";
    private static final String GET_LIGHT_BOX_URL = BASE_URL +"/lightBox/all";
    private static final String ADD_IMG_TO_LIGHT_BOX_URL = BASE_URL +"/lightBox/photo/save";
    private static final String REMOVE_IMG_TO_LIGHT_BOX_URL = BASE_URL +"/lightBox/photo/delete";
    //Path Parameters
    private static final String PAGER_PATH_PARAMETER = "page";


    //Body Parameters
    private static final String TOKEN_BODY_PARAMETER = "token";

    public static io.reactivex.Observable<SignUpResponse> signUpUser(HashMap<String, String> signUpData) {
        return Rx2AndroidNetworking.post(SIGN_UP_USER)
                .addBodyParameter(signUpData)
                .setPriority(Priority.HIGH)
                .build()
                .getObjectObservable(SignUpResponse.class);
    }

    public static io.reactivex.Observable<LoginResponse> LoginUserNormal(HashMap<String, String> loginData) {
        return Rx2AndroidNetworking.post(NORMAL_LOGIN)
                .addBodyParameter("email", loginData.get("email"))
                .addBodyParameter("password", loginData.get("password"))
                .setPriority(Priority.HIGH)
                .build()
                .getObjectObservable(LoginResponse.class);
    }

    public static io.reactivex.Observable<SocialLoginResponse> socialLoginFacebook(HashMap<String, String> loginData) {
        return Rx2AndroidNetworking.post(FACEBOOK_LOGIN_URL)
                .addBodyParameter("fullName", loginData.get("fullName"))
                .addBodyParameter("facebook_id", loginData.get("facebook_id"))
                .addBodyParameter("mobile_os", loginData.get("mobile_os"))
                .addBodyParameter("mobile_model", loginData.get("mobile_model"))
                .addBodyParameter("email", loginData.get("email"))
                .addBodyParameter("image_profile", loginData.get("image_profile"))
                .setPriority(Priority.HIGH)
                .build()
                .getObjectObservable(SocialLoginResponse.class);
    }

    public static io.reactivex.Observable<AllIndustriesResponse> getAllIndustries() {
        return Rx2AndroidNetworking.get(ALL_INDUSTRY)
                .setPriority(Priority.HIGH)
                .build()
                .getObjectObservable(AllIndustriesResponse.class);
    }


    public static io.reactivex.Observable<WelcomeScreenResponse> getWelcomeSlidesImages() {
        return Rx2AndroidNetworking.get(WELCOME_SLIDES_IMAGES)
                .setPriority(Priority.HIGH)
                .build()
                .getObjectObservable(WelcomeScreenResponse.class);
    }

    public static io.reactivex.Observable<SearchFiltersResponse> getFilters() {
        return Rx2AndroidNetworking.get(USER_SEARCH_FILTERS)

                .setPriority(Priority.HIGH)
                .build()
                .getObjectObservable(SearchFiltersResponse.class);
    }



    public static io.reactivex.Observable<AlbumSearchResponse> getSearchAlbum(String key, String page) {
        return Rx2AndroidNetworking.post(SEARCH_ALBUM)
                .addQueryParameter(PAGER_PATH_PARAMETER, page)
                .addQueryParameter("keyword", key)
                .setPriority(Priority.HIGH)
                .build()
                .getObjectObservable(AlbumSearchResponse.class);
    }


    public static io.reactivex.Observable<ImagesSearchResponse> getSearchImages(String key, String page) {
        return Rx2AndroidNetworking.post(SEARCH_IMAGES)
                .addQueryParameter(PAGER_PATH_PARAMETER, page)
                .addQueryParameter("keyword", key)
                .setPriority(Priority.HIGH)
                .build()
                .getObjectObservable(ImagesSearchResponse.class);
    }


    public static io.reactivex.Observable<FollowBrandResponse> followBrand(String token, String brandId){
        return Rx2AndroidNetworking.post(BRAND_FOLLOW_URL)
                .addBodyParameter(TOKEN_BODY_PARAMETER,token)
                .addBodyParameter("brand_id",brandId)
                .setPriority(Priority.HIGH)
                .build()
                .getObjectObservable(FollowBrandResponse.class);
    }

    public static io.reactivex.Observable<SocialResponse> getSocialData(String token){
        return Rx2AndroidNetworking.post(SOCIAL_DATA_URL)
                .addBodyParameter(TOKEN_BODY_PARAMETER, token)
                .setPriority(Priority.HIGH)
                .build()
                .getObjectObservable(SocialResponse.class);
    }



    public static io.reactivex.Observable<ProfileSearchResponse> getProfileSearch(String key, int page) {
        return Rx2AndroidNetworking.post(PHOTOGRAPHER_SEARCH_URL)
                .addBodyParameter("keyword", key)
                .addQueryParameter(PAGER_PATH_PARAMETER, String.valueOf(page))
                .getResponseOnlyFromNetwork()
                .setPriority(Priority.HIGH)
                .build()
                .getObjectObservable(ProfileSearchResponse.class);
    }

    public static io.reactivex.Observable<NotificationResponse> getNotification(String token, String page){
        return Rx2AndroidNetworking.post(GET_ALL_NOTIFICATION)
                .addBodyParameter(TOKEN_BODY_PARAMETER, token)
                .addQueryParameter(PAGER_PATH_PARAMETER,page)
                .setPriority(Priority.HIGH)
                .build()
                .getObjectObservable(NotificationResponse.class);
    }


    public static io.reactivex.Observable<CampaignResponse> getAllCompleteCampaign(  int page) {
        return Rx2AndroidNetworking.post(ALL_COMPLETED_CAMPAIGN_URL)
                .addQueryParameter(PAGER_PATH_PARAMETER, String.valueOf(page))
                .setPriority(Priority.HIGH)
                .build()
                .getObjectObservable(CampaignResponse.class);
    }
    public static io.reactivex.Observable<CampaignResponse> getAllDraftCampaign( int page) {
        return Rx2AndroidNetworking.post(ALL_DRAFT_CAMPAIGN_URL)
                .addQueryParameter(PAGER_PATH_PARAMETER, String.valueOf(page))
                .setPriority(Priority.HIGH)
                .build()
                .getObjectObservable(CampaignResponse.class);
    }
    public static io.reactivex.Observable<CampaignResponse> getAllRunningCampaign(  int page) {
        return Rx2AndroidNetworking.post(ALL_RUING_CAMPAIGN_URL)
                .addQueryParameter(PAGER_PATH_PARAMETER, String.valueOf(page))
                .setPriority(Priority.HIGH)
                .build()
                .getObjectObservable(CampaignResponse.class);
    }

    public static io.reactivex.Observable<CampaignInnerPhotosResponse> getCampaignInnerPhotos(String token, String campaignID, int page) {
        return Rx2AndroidNetworking.post(CAMPAIGN_PHOTOS_URL)
                .addBodyParameter(TOKEN_BODY_PARAMETER, token)
                .addBodyParameter("join_campaign_id", campaignID)
                .addQueryParameter(PAGER_PATH_PARAMETER, String.valueOf(page))
                .setPriority(Priority.HIGH)
                .build()
                .getObjectObservable(CampaignInnerPhotosResponse.class);
    }

    public static io.reactivex.Observable<CampaignInnerResponse> getCampaignDetails(String token, String campaignID) {
        return Rx2AndroidNetworking.post(CAMPAIGN_DETAILS_URL)
                .addBodyParameter(TOKEN_BODY_PARAMETER, token)
                .addBodyParameter("campaign_id", campaignID)
                .setPriority(Priority.HIGH)
                .build()
                .getObjectObservable(CampaignInnerResponse.class);
    }


    public static io.reactivex.Observable<UserProfileResponse> getUserProfile(String token, String userID) {
        return Rx2AndroidNetworking.post(USER_PROFILE_URL)
                .addBodyParameter(TOKEN_BODY_PARAMETER, token)
                .addBodyParameter("photographer_id", userID)
                .setPriority(Priority.HIGH)
                .build()
                .getObjectObservable(UserProfileResponse.class);
    }

    public static io.reactivex.Observable<UserPhotosResponse> getUserProfilePhotos(String token, String userID, int page) {
        return Rx2AndroidNetworking.post(USER_PROFILE_PHOTOS)
                .addBodyParameter(TOKEN_BODY_PARAMETER, token)
                .addBodyParameter("photographer_id", userID)
                .addQueryParameter(PAGER_PATH_PARAMETER, String.valueOf(page))
                .setPriority(Priority.HIGH)
                .build()
                .getObjectObservable(UserPhotosResponse.class);
    }



    public static io.reactivex.Observable<FollowUserResponse> followUser(String token, String userID){
        return Rx2AndroidNetworking.post(PHOTOGRAPHER_FOLLOW_USER_URL)
                .addBodyParameter(TOKEN_BODY_PARAMETER,token)
                .addBodyParameter("user_names_id_to",userID)
                .setPriority(Priority.HIGH)
                .build()
                .getObjectObservable(FollowUserResponse.class);
    }

    public static io.reactivex.Observable<AlbumPreviewResponse> getAlbumDetails(String albumId, String page) {
        return Rx2AndroidNetworking.post(GET_ALBUM_PREVIEW)
                .addQueryParameter(PAGER_PATH_PARAMETER,page)
                .addBodyParameter("album_id",albumId)
                .setPriority(Priority.HIGH)
                .build()
                .getObjectObservable(AlbumPreviewResponse.class);
    }

    public static io.reactivex.Observable<AlbumPreviewImagesResponse> getAlbumImagesPreview(String albumId, String page) {
        return Rx2AndroidNetworking.post(GET_ALBUM_IMAGES_PREVIEW)
                .addQueryParameter(PAGER_PATH_PARAMETER,page)
                .addBodyParameter("album_id",albumId)
                .setPriority(Priority.HIGH)
                .build()
                .getObjectObservable(AlbumPreviewImagesResponse.class);
    }


    public static io.reactivex.Observable<ImgCommentResponse> getImageComments(String image_id, String page) {
        return Rx2AndroidNetworking.post(GET_IMAGE_COMMENT)
                .addQueryParameter("image_id", image_id)
                .addQueryParameter(PAGER_PATH_PARAMETER, page)
                .getResponseOnlyFromNetwork()
                .setPriority(Priority.HIGH)
                .build()
                .getObjectObservable(ImgCommentResponse.class);
    }

    public static io.reactivex.Observable<ImgCommentResponse> submitImageComment(String image_id, String imageComment) {
        return Rx2AndroidNetworking.post(SUBMIT_IMAGE_COMMENT)
                .addQueryParameter("image_id", image_id)
                .addQueryParameter("comment", imageComment)
                .setPriority(Priority.HIGH)
                .build()
                .getObjectObservable(ImgCommentResponse.class);
    }



    public static io.reactivex.Observable<LikeImageResponse> likeImage(String imageId) {
        return Rx2AndroidNetworking.post(LIKE_IMAGE)
                .addBodyParameter("image_id", imageId)
                .getResponseOnlyFromNetwork()
                .setPriority(Priority.HIGH)
                .build()
                .getObjectObservable(LikeImageResponse.class);
    }
    public static io.reactivex.Observable<BrandInnerResponse> getBrandInnerData(String token, String brandId) {
        return Rx2AndroidNetworking.post(INNER_BRAND_URL)
                .addBodyParameter(TOKEN_BODY_PARAMETER, token)
                .addBodyParameter("id", brandId)
                .setPriority(Priority.HIGH)
                .build()
                .getObjectObservable(BrandInnerResponse.class);
    }
    public static io.reactivex.Observable<FollowCampaignResponse> followCampaign(String campaignID) {
        return Rx2AndroidNetworking.post(FOLLOW_CAMPAIGN_URL)
                .addBodyParameter("campaign_id", campaignID)
                .setPriority(Priority.HIGH)
                .build()
                .getObjectObservable(FollowCampaignResponse.class);
    }


     public static io.reactivex.Observable<BusinessProfileResponse> getBrandProfileData() {
        return Rx2AndroidNetworking.post(BRAND_PROFILE_URL)
                .setPriority(Priority.HIGH)
                .build()
                .getObjectObservable(BusinessProfileResponse.class);
    }



     public static io.reactivex.Observable<BrandLightBoxResponse> getBrandLightBoxes(String page) {
        return Rx2AndroidNetworking.post(BRAND_LIGHT_BOX_URL)
                .setPriority(Priority.HIGH)
                .addQueryParameter(PAGER_PATH_PARAMETER,page)
                .build()
                .getObjectObservable(BrandLightBoxResponse.class);
    }

    public static io.reactivex.Observable<DeleteLightBoxResponse> deleteLightBox(String id) {
        return Rx2AndroidNetworking.post(DELETE_LIGHT_BOX_URL)
                .setPriority(Priority.HIGH)
                .addBodyParameter("lightbox_id",id)
                .build()
                .getObjectObservable(DeleteLightBoxResponse.class);
    }

    public static io.reactivex.Observable<AddLighBoxResponse> addLightBox(HashMap<String,String> data) {
        return Rx2AndroidNetworking.post(ADD_LIGHT_BOX_URL)
                .setPriority(Priority.HIGH)
                .addBodyParameter(data)
                .build()
                .getObjectObservable(AddLighBoxResponse.class);
    }

    public static io.reactivex.Observable<LightBoxListResponse> getLightBoxes() {
        return Rx2AndroidNetworking.post(GET_LIGHT_BOX_URL)
                .setPriority(Priority.HIGH)
                .build()
                .getObjectObservable(LightBoxListResponse.class);
    }


    public static io.reactivex.Observable<AddImageToLightBoxResponse> addImageToLightBox(Map<String,String> data) {
        return Rx2AndroidNetworking.post(ADD_IMG_TO_LIGHT_BOX_URL)
                .setPriority(Priority.HIGH)
                .addBodyParameter(data)
                .build()
                .getObjectObservable(AddImageToLightBoxResponse.class);
    }

    public static io.reactivex.Observable<RemoveImageToLightBoxResponse> removeLightBoxImage(Map<String,String> data) {
        return Rx2AndroidNetworking.post(REMOVE_IMG_TO_LIGHT_BOX_URL)
                .setPriority(Priority.HIGH)
                .addBodyParameter(data)
                .build()
                .getObjectObservable(RemoveImageToLightBoxResponse.class);
    }



    public static io.reactivex.Observable<SubmitCampaignResponse> submitCampaign(String token, HashMap<String, String> data, File campaignImg) {
        return Rx2AndroidNetworking.upload(SUBMIT_CAMPAIGN_URL)
                .addHeaders("x-auth-token", token)
                .addHeaders("x-user-type", DEFAULT_USER_TYPE)
                .addHeaders("x-lang-code", "en-us")
                .addMultipartFile("image_cover", campaignImg)
                .addMultipartParameter(data)
                .setPriority(Priority.HIGH)
                .build()
                .getObjectObservable(SubmitCampaignResponse.class);
    }

    public static io.reactivex.Observable<UploadProfileImgResponse> uploadUserProfilePhoto(String token,File image) {
        return Rx2AndroidNetworking.upload(UPLOAD_PROFILE_IMG)
                .addHeaders("x-auth-token", token)
                .addHeaders("x-user-type", DEFAULT_USER_TYPE)
                .addHeaders("x-lang-code", "en-us")
                .addMultipartFile("thumbnail", image)
                .setPriority(Priority.HIGH)
                .build()
                .getObjectObservable(UploadProfileImgResponse.class);
    }

//    public static io.reactivex.Observable<GeoCodeAutoCompleteResponse> getGeoGodeAutoCompleteResponse(String key){
//        return Rx2AndroidNetworking.get()
//
//    }
//

//    public static io.reactivex.Observable<BaseResponse> makeGetRequest(String lang, String key) {
//        return Rx2AndroidNetworking.get(REQUEST_URL)
//                .addPathParameter("lang", lang)
//                .addQueryParameter("key", String.valueOf(key))
//                .getResponseOnlyFromNetwork()
//                .build()
//                .getObjectObservable(BaseResponse.class);
//    }

//    /**
//     * --Upload Attachment
//     *
//     * @param file -->File src
//     * @message -->Request parameter you can add multible parameter to request  body along with uploaded attachment
//     */
//    public static void complaint(String message, File file, final RequestCallBack requestCallBack) {
//        AndroidNetworking.upload(REQUEST_URL)
//                .addHeaders("Content-Type", "multipart/form-data")
//                .addMultipartParameter("message", message)
//                .addMultipartFile("files[]", file) //todo "files[]" is A key According to back End
//                .setPriority(Priority.HIGH)
//                .build()
//                .getAsJSONObject(new JSONObjectRequestListener() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//                            boolean success = response.getBoolean("success");
//                            if (success) {
//                                requestCallBack.OnSuccsess();
//                            } else {
//                                requestCallBack.onFailer();
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                            requestCallBack.onFailer();
//                        }
//                    }
//
//                    @Override
//                    public void onError(ANError anError) {
//                        requestCallBack.onFailer();
//                    }
//                });
//    }
//

    }
