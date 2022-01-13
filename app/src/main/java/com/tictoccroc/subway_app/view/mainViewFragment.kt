package com.tictoccroc.subway_app.view

import android.drm.DrmConvertedStatus
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.tictoccroc.subway_app.Converters
import com.tictoccroc.subway_app.adapter.ResultAdapter
import com.tictoccroc.subway_app.database.BaseDatabase
import com.tictoccroc.subway_app.databinding.FragmentMainViewBinding
import com.tictoccroc.subway_app.entitiy.SubwayDAO
import com.tictoccroc.subway_app.listener.SubwaySearchClickListener
import com.tictoccroc.subway_app.model.SubwayLine
import com.tictoccroc.subway_app.model.SubwayStation

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [mainViewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class mainViewFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null;
    private var param2: String? = null;
    private var fragmentBinding:FragmentMainViewBinding? = null;

    private var lines:ArrayList<SubwayLine> = ArrayList<SubwayLine>();

    private val totalStationList = ArrayList<SubwayStation>();

    private  var subwayDAO: SubwayDAO? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = "station"
            param2 = "lines"
        }

        val database = BaseDatabase.createRoomDatabase(requireContext());

        subwayDAO = database!!.subwayDAO();
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentBinding = FragmentMainViewBinding.inflate(inflater, container, false);
        return fragmentBinding!!.root;
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment mainViewFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            mainViewFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navSubwayFrag = Navigation.findNavController(view);
        val btnSearch = fragmentBinding!!.cvSearch;


        val stationList = subwayDAO!!.getSubwayStations();

        stationList.forEach{
            val stationLine = Converters().jsonToArrayLit(it.lines);
            val subwayStation = SubwayStation(it.idx, it.name, stationLine as ArrayList<Int>)
            totalStationList.add(subwayStation)
        }


        val linesList = Converters().jsonToLineList(subwayDAO!!.getSubwayLines()[0].lineJson) as ArrayList<SubwayLine>


        val recyclerView = fragmentBinding!!.recyclerResults;
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 3);
        recyclerView.setHasFixedSize(true);
        recyclerView.adapter = ResultAdapter(requireContext(), totalStationList, linesList);


        btnSearch.setOnClickListener(SubwaySearchClickListener(navSubwayFrag));
    }
}