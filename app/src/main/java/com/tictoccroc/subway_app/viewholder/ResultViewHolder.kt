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
import com.tictoccroc.subway_app.listener.DeleteForIdxClickListener
import com.tictoccroc.subway_app.model.SubwayLine
import com.tictoccroc.subway_app.model.SubwayStation

class ResultViewHolder(view:SubwaySelectItemBinding, subwayLines:ArrayList<SubwayLine>) : RecyclerView.ViewHolder(view.root){
    private val _view = view;
    private val _lines = subwayLines;

    fun bind(context: Context, item:SubwayStation){
        val mainLayout = _view.mainLayout;
        
        // 메인 레이아웃 안에 자식 레이아웃이 존재할 경우 초기화
        if(mainLayout.childCount > 0)
            mainLayout.removeAllViews();

        // 역이름 동적 텍스트뷰 생성
        val titleTextView = TextView(context);
        titleTextView.setText(item.name);
        val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,  LinearLayout.LayoutParams.WRAP_CONTENT );
        titleTextView.layoutParams = layoutParams;

        mainLayout.addView(titleTextView);

        // 역에 존재하는 호선정보 텍스트뷰 생성
        val lines = item.subwayLines;
        lines.forEach{
            val lineID = it;
            _lines.forEach{
                if(lineID == it.idx){
                    val title = it.sub_name;
                    var color_code = it.color_code;

                    val textView = TextView(context);
                    textView.setText(title);
                    textView.setTextColor(Color.parseColor(color_code.toString()));
                    val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,  LinearLayout.LayoutParams.WRAP_CONTENT );
                    params.setMargins(0, 0, 5, 0);
                    textView.layoutParams = params;
                    mainLayout.addView(textView);
                }
            }
        }

        // 삭제 이미지뷰 생성
        val imageView = ImageView(context);
        imageView.setImageResource(R.drawable.ic_close);
        imageView.setOnClickListener{
            imageClickListener.onStationForIdxClick(item);
        }
        val imgParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,  LinearLayout.LayoutParams.WRAP_CONTENT );
        imageView.layoutParams = imgParams;

        mainLayout.addView(imageView);
    }
    
    // 이미지뷰 선택시 선택된 역정보 삭제
    private lateinit var imageClickListener : DeleteForIdxClickListener

    fun setImageClickListener(imageClickListener:DeleteForIdxClickListener){
        this.imageClickListener = imageClickListener;
    }
}