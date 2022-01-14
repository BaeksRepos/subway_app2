package com.tictoccroc.subway_app.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.tictoccroc.subway_app.adapter.SubwayAdapter
import com.tictoccroc.subway_app.database.BaseDatabase
import com.tictoccroc.subway_app.databinding.FragmentSearchViewBinding
import com.tictoccroc.subway_app.entitiy.SubwayDAO
import com.tictoccroc.subway_app.listener.KeywordFilterListener
import com.tictoccroc.subway_app.listener.KeywordRemoveListener
import com.tictoccroc.subway_app.listener.SearchClickListstener
import com.tictoccroc.subway_app.repository.SubwayRepository
import com.tictoccroc.subway_app.viewModel.SubwayViewModel
import com.tictoccroc.subway_app.viewModelFactory.SubwayViewModelFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [searchViewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class searchViewFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var fragmentBinding:FragmentSearchViewBinding? = null;

    private  var subwayDAO: SubwayDAO? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        val database = BaseDatabase.createRoomDatabase(requireContext());

        subwayDAO = database!!.subwayDAO();
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this
        fragmentBinding = FragmentSearchViewBinding.inflate(inflater, container, false)
        return fragmentBinding!!.root;
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment searchViewFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            searchViewFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModelFactory = SubwayViewModelFactory(SubwayRepository(requireContext()));
        val viewModel = ViewModelProvider(this, viewModelFactory).get(SubwayViewModel::class.java);

        val recyclerView = fragmentBinding!!.rvSubwayList;
        recyclerView.layoutManager = LinearLayoutManager(requireContext());
        recyclerView.setHasFixedSize(true);


        val removeKeyword = fragmentBinding!!.removeKeyword;
        val editKeyword = fragmentBinding!!.editKeyword;

        // 키워드 입력 후 옆에 X 누를 경우 텍스트 지움
        removeKeyword.setOnClickListener(KeywordRemoveListener(editKeyword));

        viewModel.requestSearchSubway();

        viewModel.subwayLiveData.observe(viewLifecycleOwner, Observer {
           subway -> subway?.let {
                val viewAdapter = SubwayAdapter(requireContext(), it);
                viewAdapter.setStationListClickListener(SearchClickListstener(view, subwayDAO!!));
                recyclerView.adapter = viewAdapter;
                editKeyword.addTextChangedListener(KeywordFilterListener(viewAdapter, editKeyword.text.toString()))
            }
        });
    }
}