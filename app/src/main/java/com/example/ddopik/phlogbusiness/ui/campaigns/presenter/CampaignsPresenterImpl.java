package com.example.ddopik.phlogbusiness.ui.campaigns.presenter;


import android.annotation.SuppressLint;
import android.content.Context;

import com.example.ddopik.phlogbusiness.Utiltes.ErrorUtil;
import com.example.ddopik.phlogbusiness.network.BaseNetworkApi;
import com.example.ddopik.phlogbusiness.ui.campaigns.completed.view.CompleteCampaignsFragmentView;
import com.example.ddopik.phlogbusiness.ui.campaigns.completed.presenter.CompleteCampaignPresenter;
import com.example.ddopik.phlogbusiness.ui.campaigns.draft.view.DraftCampaignsFragmentView;
import com.example.ddopik.phlogbusiness.ui.campaigns.draft.presenter.DraftCampaignsPresenter;
import com.example.ddopik.phlogbusiness.ui.campaigns.running.presenter.RunningCampaignPresenter;
import com.example.ddopik.phlogbusiness.ui.campaigns.running.view.RunningCampaignFragmentView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by abdalla_maged on 12/25/2018.
 */
public class CampaignsPresenterImpl implements DraftCampaignsPresenter,CompleteCampaignPresenter,RunningCampaignPresenter {


    private static final String TAG = CampaignsPresenterImpl.class.getSimpleName();
    private Context context;
    public CampaignsPresenterImpl(Context context) {
        this.context = context;
    }


    @SuppressLint("CheckResult")
    public void joinCampaign(String campaignID) {
//        BaseNetworkApi.followCampaign(campaignID)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(followCampaignResponse -> {
//                    campaignFragmentView.showMessage(context.getResources().getString(R.string.campaign_followed));
//                }, throwable -> {
//                    ErrorUtil.Companion.setError(context, TAG, throwable);
//                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void getCompletedCampaign(int page,CompleteCampaignsFragmentView completeCampaignsFragmentView) {
        completeCampaignsFragmentView.showAllCompletedCampaignProgress(true);
        BaseNetworkApi.getAllCompleteCampaign(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(campaignResponse -> {
                    completeCampaignsFragmentView.showAllCompletedCampaignProgress(false);
                    completeCampaignsFragmentView.viewAllCompletedCampaign(campaignResponse.data.data);
                 }, throwable -> {
                    completeCampaignsFragmentView.showAllCompletedCampaignProgress(false);
                    ErrorUtil.Companion.setError(context,TAG,throwable);
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void getDraftCampaign(int page, DraftCampaignsFragmentView draftCampaignsFragmentView) {
        draftCampaignsFragmentView.showDraftCampaignProgress(true);
        BaseNetworkApi.getAllDraftCampaign(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(campaignResponse -> {
                    draftCampaignsFragmentView.showDraftCampaignProgress(false);
                    draftCampaignsFragmentView.viewDraftCampaign(campaignResponse.data.data);

                }, throwable -> {
                    draftCampaignsFragmentView.showDraftCampaignProgress(false);
                    ErrorUtil.Companion.setError(context, TAG, throwable);
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void getRunningCampaign(int page,RunningCampaignFragmentView runningCampaignFragmentView) {
        runningCampaignFragmentView.showAllCampaignProgress(true);
        BaseNetworkApi.getAllRunningCampaign(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(campaignResponse -> {
                    runningCampaignFragmentView.showAllCampaignProgress(false);
                    runningCampaignFragmentView.viewAllCampaign(campaignResponse.data.data);

                }, throwable -> {
                    runningCampaignFragmentView.showAllCampaignProgress(false);
                    ErrorUtil.Companion.setError(context, TAG, throwable);
                });
    }


}