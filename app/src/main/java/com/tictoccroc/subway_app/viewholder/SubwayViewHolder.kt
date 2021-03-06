package com.tictoccroc.subway_app.viewholder

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.tictoccroc.subway_app.databinding.SubwaySearchItemBinding
import com.tictoccroc.subway_app.model.SubwayLine
import com.tictoccroc.subway_app.model.SubwayStation

class SubwayViewHolder(view:SubwaySearchItemBinding, subwayLines:ArrayList<SubwayLine>) : RecyclerView.ViewHolder(view.root)  {
    private val _view = view;
    private val _subwayLines = subwayLines;
    fun bind(context: Context, item:SubwayStation){
        val lines = item.subwayLines;

        val mainLayout = _view.mainLayout;

        val newLinear = LinearLayout(context);
        newLinear.orientation = LinearLayout.HORIZONTAL;
        newLinear.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        if(mainLayout.childCount > 0){
            mainLayout.removeAllViews();
        }
        val titleTextView = TextView(context);
        titleTextView.setText(item.name);
        val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,  LinearLayout.LayoutParams.WRAP_CONTENT );
        layoutParams.weight = 9.0f;
        titleTextView.layoutParams = layoutParams;

        mainLayout.addView(titleTextView)

        lines.forEach{
            val lineID = it;
            _subwayLines.forEach{
                if(lineID == it.idx){
                    val textView = TextView(context);
                    textView.setText(it.name);
                    val color_code = it.color_code
                    textView.setTextColor(Color.parseColor(color_code.toString()))
                    val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,  LinearLayout.LayoutParams.WRAP_CONTENT );
                    params.setMargins(5, 0, 0, 0);
                    textView.layoutParams = params;
                    newLinear.addView(textView);
                }
            }
        }
        mainLayout.addView(newLinear);
    }
}