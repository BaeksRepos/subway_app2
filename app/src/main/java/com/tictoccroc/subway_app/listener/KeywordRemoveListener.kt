package com.tictoccroc.subway_app.listener

import android.view.View
import android.widget.EditText

class KeywordRemoveListener(editText: EditText) : View.OnClickListener {
    private val _eidtText = editText;
    override fun onClick(p0: View?) {
        _eidtText.text.clear();
    }

}