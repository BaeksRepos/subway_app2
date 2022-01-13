package com.tictoccroc.subway_app.viewholder

import android.content.Context
import android.graphics.Color
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tictoccroc.subway_app.R
import com.tictoccroc.subway_app.databinding.SubwaySelectItemBinding
import com.tictoccroc.subway_app.entitiy.SubwayLineEntity
import com.tictoccroc.subway_app.entitiy.SubwayStationEntity
import com.tictoccroc.subway_app.model.SubwayLine
import com.tictoccroc.subway_app.model.SubwayStation

class ResultViewHolder(view:SubwaySelectItemBinding, subwayLines:ArrayList<SubwayLine>) : RecyclerView.ViewHolder(view.root){
    private val _view = view;
    private val _lines = subwayLines;

    fun bind(context: Context, item:SubwayStation){
        val mainLayout = _view.mainLayout;

        val newLinear = LinearLayout(context);
        newLinear.orientation = LinearLayout.HORIZONTAL;
        newLinear.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        if(mainLayout.childCount > 0)
            mainLayout.removeAllViews();

        val titleTextView = TextView(context);
        titleTextView.setText(item.name);
        val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,  LinearLayout.LayoutParams.WRAP_CONTENT );
        layoutParams.weight = 9.0f;

        mainLayout.addView(titleTextView);

        val lines = item.subwayLines;

        lines.forEach{
            val lineID = it;
            _lines.forEach{
                if(lineID == it.idx){
                    val title = it.name;
                    var color_code = it.color_code;

                    val textView = TextView(context);
                    textView.setText(title);
                    textView.setTextColor(Color.parseColor(color_code.toString()));
                    val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,  LinearLayout.LayoutParams.WRAP_CONTENT );
                    params.setMargins(5, 0, 0, 0);
                    textView.layoutParams = params;
                    newLinear.addView(textView);
                }
            }
        }

        mainLayout.addView(newLinear);

        val imageView = ImageView(context);
        imageView.setImageResource(R.drawable.ic_close);
        val imgParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,  LinearLayout.LayoutParams.WRAP_CONTENT );
        imageView.layoutParams = imgParams;

        mainLayout.addView(imageView);
    }
}