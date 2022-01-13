package com.tictoccroc.subway_app.listener

import android.text.Editable
import android.text.TextWatcher
import com.tictoccroc.subway_app.adapter.SubwayAdapter

class KeywordFilterListener(adapter:SubwayAdapter, keyword:String) : TextWatcher{
    private val _adapter = adapter;
    private val _keyword = keyword;
    override fun afterTextChanged(p0: Editable?) {
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        _adapter.filter?.filter(p0.toString())
    }
}