package com.example.ddopik.phlogbusiness.ui.campaigns.addcampaign.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.example.ddopik.phlogbusiness.base.commonmodel.Industry;
import com.example.ddopik.phlogbusiness.base.commonmodel.Tag;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AddCampaignRequestModel implements Parcelable {


    public String campaignName;
    public File campaignCoverPhoto;
    public String coverUrl;
    public String campaignDescription;
    public String campaignPrize;
    public String campaignStartDate;
    public String campaignEndDate;
    public String winnersNumber;
    public String isDraft;
    public List<Tag> tagList;
    public boolean alreadyAdded;
    public int campaignId;

    public HashMap<String, String> getTags() {
        HashMap<String, String> tagsSelected = new HashMap<String, String>();
        for (int i = 0; i < tagList.size(); i++) {
            tagsSelected.put("tags[" + i + "]", tagList.get(i).name);
        }

        return tagsSelected;
    }

    public AddCampaignRequestModel() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.campaignName);
        dest.writeSerializable(this.campaignCoverPhoto);
        dest.writeString(coverUrl);
        dest.writeString(this.campaignDescription);
        dest.writeString(this.campaignPrize);
        dest.writeString(this.campaignStartDate);
        dest.writeString(this.campaignEndDate);
        dest.writeString(this.winnersNumber);
        dest.writeString(this.isDraft);
        dest.writeTypedList(this.tagList);
        dest.writeValue(alreadyAdded);
        dest.writeInt(campaignId);
    }

    protected AddCampaignRequestModel(Parcel in) {
        this.campaignName = in.readString();
        this.campaignCoverPhoto = (File) in.readSerializable();
        coverUrl = in.readString();
        this.campaignDescription = in.readString();
        this.campaignPrize = in.readString();
        this.campaignStartDate = in.readString();
        this.campaignEndDate = in.readString();
        this.winnersNumber = in.readString();
        this.isDraft = in.readString();
        this.tagList = in.createTypedArrayList(Tag.CREATOR);
        alreadyAdded = (boolean) in.readValue(Boolean.class.getClassLoader());
        campaignId = in.readInt();
    }

    public static final Creator<AddCampaignRequestModel> CREATOR = new Creator<AddCampaignRequestModel>() {
        @Override
        public AddCampaignRequestModel createFromParcel(Parcel source) {
            return new AddCampaignRequestModel(source);
        }

        @Override
        public AddCampaignRequestModel[] newArray(int size) {
            return new AddCampaignRequestModel[size];
        }
    };
}
