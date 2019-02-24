package com.example.ddopik.phlogbusiness.ui.commentimage.presenter;

import android.annotation.SuppressLint;
import android.content.Context;
import com.example.ddopik.phlogbusiness.base.commonmodel.BaseImage;
import com.example.ddopik.phlogbusiness.network.BaseNetworkApi;
import com.example.ddopik.phlogbusiness.ui.commentimage.model.ReportModel;
import com.example.ddopik.phlogbusiness.ui.commentimage.model.ReportReason;
import com.example.ddopik.phlogbusiness.ui.commentimage.view.ImageCommentActivityView;
import com.example.ddopik.phlogbusiness.utiltes.CustomErrorUtil;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by abdalla_maged On Nov,2018
 */
public class ImageCommentActivityImpl implements ImageCommentActivityPresenter {

    private String TAG = ImageCommentActivityImpl.class.getSimpleName();
    private Context context;
    private ImageCommentActivityView imageCommentActivityView;

    public ImageCommentActivityImpl(Context context, ImageCommentActivityView imageCommentActivityView) {
        this.context = context;
        this.imageCommentActivityView = imageCommentActivityView;
    }

    @SuppressLint("CheckResult")
    @Override
    public void getImageComments(String imageId, String page) {
        imageCommentActivityView.viewImageProgress(true);
        BaseNetworkApi.getImageComments(imageId, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(albumImgCommentResponse -> {
                    imageCommentActivityView.viewPhotoComment(albumImgCommentResponse.data);
                    imageCommentActivityView.viewImageProgress(false);
                }, throwable -> {
                    CustomErrorUtil.Companion.setError(context, TAG, throwable);
                    imageCommentActivityView.viewImageProgress(false);
                });
    }


    @SuppressLint("CheckResult")
    @Override
    public void submitComment(String imageId, String comment) {
        imageCommentActivityView.viewImageProgress(true);
        BaseNetworkApi.submitImageComment(imageId, comment)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(submitImageCommentResponse -> {
                    imageCommentActivityView.viewMessage("Comment Submitted");
                    imageCommentActivityView.onImageCommented(submitImageCommentResponse.data);
                    imageCommentActivityView.viewImageProgress(false);
                }, throwable -> {
                    CustomErrorUtil.Companion.setError(context, TAG, throwable);
                    imageCommentActivityView.viewImageProgress(false);
                });

    }

    @SuppressLint("CheckResult")
    @Override
    public void likePhoto(BaseImage baseImage) {
        imageCommentActivityView.viewImageProgress(true);



            BaseNetworkApi.likeImage(String.valueOf(baseImage.id))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(likeImageResponse -> {
                        imageCommentActivityView.onImageLiked(likeImageResponse.data);
                        imageCommentActivityView.viewImageProgress(false);
                    }, throwable -> {
                        imageCommentActivityView.viewImageProgress(false);
                        CustomErrorUtil.Companion.setError(context, TAG, throwable);
                    });


    }

    @SuppressLint("CheckResult")
    @Override
    public void unLikePhoto(BaseImage baseImage) {
        BaseNetworkApi.unlikeImage(String.valueOf(baseImage.id))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(likeImageResponse -> {
                    imageCommentActivityView.onImageLiked(baseImage);
                    imageCommentActivityView.onImageLiked(likeImageResponse.data);
                }, throwable -> {
                    imageCommentActivityView.viewImageProgress(false);
                    CustomErrorUtil.Companion.setError(context, TAG, throwable);
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void rateImage(BaseImage baseImage, float rate) {
        imageCommentActivityView.viewImageProgress(true);
        BaseNetworkApi.rateImage(String.valueOf(baseImage.id), String.valueOf((int) Math.round(rate)))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(imageRateResponse -> {
                            imageCommentActivityView.viewOnImageRate(imageRateResponse.baseImage);
                            imageCommentActivityView.viewImageProgress(false);
                        }
                        , throwable -> {

                            imageCommentActivityView.viewImageProgress(false);
                            CustomErrorUtil.Companion.setError(context, TAG, throwable);
                        });


    }

    @SuppressLint("CheckResult")
    @Override
    public void addImageToCart(int imageId) {
        imageCommentActivityView.viewImageProgress(true);
        BaseNetworkApi.addImageToCart(String.valueOf(imageId))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(addImageToCartResponse -> {
                    imageCommentActivityView.viewOnImagedAddedToCart(true);
                    imageCommentActivityView.viewImageProgress(false);
                }, throwable -> {
                    imageCommentActivityView.viewOnImagedAddedToCart(true);
                    imageCommentActivityView.viewImageProgress(false);
                });


    }

    @Override
    public void getReportReasons(Consumer<List<ReportReason>> consumer) {
        imageCommentActivityView.viewImageProgress(true);
        BaseNetworkApi.getReportReasons()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(reportReasonsResponse -> {
                    imageCommentActivityView.viewImageProgress(false);
                    if (reportReasonsResponse != null && reportReasonsResponse.getData() != null)
                        consumer.accept(reportReasonsResponse.getData());
                }, throwable -> {
                    imageCommentActivityView.viewImageProgress(false);
                    CustomErrorUtil.Companion.setError(context, TAG, throwable);
                });
    }

    @Override
    public void submitReport(Consumer<Boolean> success, ReportModel model) {
        BaseNetworkApi.submitReport(model);
    }
}
