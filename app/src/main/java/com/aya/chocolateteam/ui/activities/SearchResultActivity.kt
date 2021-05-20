package com.aya.chocolateteam.ui.activities

import android.view.LayoutInflater
import com.aya.chocolateteam.databinding.SearchResultBinding

class SearchResultActivity: BaseActivity<SearchResultBinding>() {
    override val LOG_TAG: String="SEARCH_RESULT"
    override val bindingInflater: (LayoutInflater) -> SearchResultBinding= SearchResultBinding::inflate
    override fun setup() {

    }

    override fun addCallBack() {

    }
}