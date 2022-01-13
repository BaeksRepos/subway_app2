package com.tictoccroc.subway_app.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tictoccroc.subway_app.databinding.SubwaySelectItemBinding
import com.tictoccroc.subway_app.entitiy.SubwayLineEntity
import com.tictoccroc.subway_app.entitiy.SubwayStationEntity
import com.tictoccroc.subway_app.model.SubwayLine
import com.tictoccroc.subway_app.model.SubwayStation
import com.tictoccroc.subway_app.viewholder.ResultViewHolder

class ResultAdapter(context:Context, station: ArrayList<SubwayStation>, lines:ArrayList<SubwayLine>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val _station = station;
    private val _lines = lines;
    private val _context = context;
    override fun getItemCount(): Int {
        return _station.size;
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val fragmentBinding = SubwaySelectItemBinding.inflate(LayoutInflater.from(parent.context), parent, false);
        return ResultViewHolder(fragmentBinding, _lines);
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is ResultViewHolder){
            val item = _station.get(position);
            holder.bind(_context, item);
        }
    }

}
