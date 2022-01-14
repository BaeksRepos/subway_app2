package com.tictoccroc.subway_app.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.tictoccroc.subway_app.databinding.SubwaySearchItemBinding
import com.tictoccroc.subway_app.listener.StationListClickListener
import com.tictoccroc.subway_app.model.Subway
import com.tictoccroc.subway_app.model.SubwayLine
import com.tictoccroc.subway_app.model.SubwayStation
import com.tictoccroc.subway_app.viewholder.SubwayViewHolder

class SubwayAdapter(context: Context, subwayList: Subway) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {
    private val _subwayList:Subway = subwayList;
    private var stations = _subwayList.subway_stations
    private val lines = _subwayList.subway_lines;
    private val context = context


    override fun getItemCount(): Int {
       return stations.size;
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val fragmentBinding = SubwaySearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false);
        return SubwayViewHolder(fragmentBinding, lines);
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = stations.get(position);

        if(holder is SubwayViewHolder){
            holder.bind(context,item);
            holder.itemView.setOnClickListener{
                stationListClickListener.onStationListClick(item, position, lines);
            }
        }
    }

    // 검색된 역 리스트 클릭 리스너
    private lateinit var stationListClickListener:StationListClickListener

    fun setStationListClickListener(clickListener:StationListClickListener){
        this.stationListClickListener = clickListener;
    }

    // 검색정보 키워드입력에 따른 필터링 기능
    override fun getFilter(): Filter? {
        return FilterClass();
    }
    
    inner class FilterClass : Filter(){
        override fun performFiltering(p0: CharSequence?): FilterResults {
            val keyword = p0.toString();

            //resultFilterList
            if(keyword.equals(""))
                stations = _subwayList.subway_stations;
            else{
                val filterList = ArrayList<SubwayStation>();
                if(_subwayList.subway_stations != null){
                    _subwayList.subway_stations.forEach{
                        if(it.name.contains(keyword))
                            filterList.add(it);
                    }
                    stations = filterList;
                }
            }

            val filterResults = FilterResults();
            filterResults.values = stations;
            return filterResults;
        }

        override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
            stations = p1!!.values as ArrayList<SubwayStation>;
            notifyDataSetChanged();
        }
    }
}