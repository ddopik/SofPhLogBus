package com.example.ddopik.phlogbusiness.ui.search.mainSearchView.model;

import com.example.ddopik.phlogbusiness.base.commonmodel.Filter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by abdalla_maged on 10/30/2018.
 */
public class SearchFiltersResponse {

    @SerializedName("data")
    @Expose
    public List<Filter> data = null;

}
