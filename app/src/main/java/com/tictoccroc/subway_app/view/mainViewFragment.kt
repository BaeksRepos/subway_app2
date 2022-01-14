package com.tictoccroc.subway_app.view

import android.drm.DrmConvertedStatus
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.tictoccroc.subway_app.Converters
import com.tictoccroc.subway_app.adapter.ResultAdapter
import com.tictoccroc.subway_app.database.BaseDatabase
import com.tictoccroc.subway_app.databinding.FragmentMainViewBinding
import com.tictoccroc.subway_app.entitiy.SubwayDAO
import com.tictoccroc.subway_app.listener.DeleteAllClickListener
import com.tictoccroc.subway_app.listener.DeleteForIdxClickListener
import com.tictoccroc.subway_app.listener.SubwaySearchClickListener
import com.tictoccroc.subway_app.model.SubwayLine
import com.tictoccroc.subway_app.model.SubwayStation
import com.tictoccroc.subway_app.repository.SubwayRepository
import com.tictoccroc.subway_app.viewModel.SubwayViewModel
import com.tictoccroc.subway_app.viewModelFactory.SubwayViewModelFactory

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

        // viewModel 선언
        val viewModelFactory = SubwayViewModelFactory(SubwayRepository(requireContext()));
        val viewModel = ViewModelProvider(this, viewModelFactory).get(SubwayViewModel::class.java);

        // Room DB에 데이터 GET
        viewModel.selectSubwayStation();
        viewModel.selectSubwayLine();


        val btnDeleteAll = fragmentBinding!!.btnAllDelete;
        val navSubwayFrag = Navigation.findNavController(view);
        val btnSearch = fragmentBinding!!.cvSearch;


        viewModel.stationLiveData.observe(viewLifecycleOwner, Observer {
            val stations = it;
            stations.forEach{
                var line = ArrayList<Int>();

                // 호선정보가 2개이상이면 Arraylist로 형변환 1개일경우 add
                if(!it.lines.equals("")) {
                    val result = Converters().jsonToArrayLit(it.lines);
                    if (result.size >= 2)
                        line = result as ArrayList<Int>;
                    else
                        line.add(result[0])
                }

                val station = SubwayStation(it.idx, it.name, line);
                totalStationList.add(station);
            }
        })

        var linesList = ArrayList<SubwayLine>();

        viewModel.lineLiveData.observe(viewLifecycleOwner, Observer {
            if(it.size != 0)
                linesList = Converters().jsonToLineList(it[0].lineJson) as ArrayList<SubwayLine>;

            val recyclerView = fragmentBinding!!.recyclerResults;
            recyclerView.layoutManager = GridLayoutManager(requireContext(), 3);
            recyclerView.setHasFixedSize(true);
            val resultAdapter = ResultAdapter(requireContext(), totalStationList, linesList);
            resultAdapter.setImageClickListener(onDeleteForIdxClickListener(viewModel, totalStationList, resultAdapter));
            recyclerView.adapter = resultAdapter;

            btnDeleteAll.setOnClickListener(DeleteAllClickListener(viewModel, totalStationList, resultAdapter));
            btnSearch.setOnClickListener(SubwaySearchClickListener(navSubwayFrag));
        })
    }

    // 선택된 역정보 삭제 클릭 리스너
    inner class onDeleteForIdxClickListener(viewModel:SubwayViewModel, totalStationList:ArrayList<SubwayStation>, adapter: ResultAdapter) : DeleteForIdxClickListener{
        private val _viewModel = viewModel;
        private val _totalList = totalStationList;
        private val _adapter = adapter;
        override fun onStationForIdxClick(item: SubwayStation) {

            val checkFlag = _totalList.contains(item);
            if(checkFlag)
            {
                _totalList.remove(item);
                _viewModel.deleteIdxSubwayStation(item.idx);
                _adapter.notifyDataSetChanged();
            }
        }
    }

}