package com.example.ilovecats.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ilovecats.Helpers.ApiStatus
import com.example.ilovecats.Helpers.GridSpacingItemDecoration
import com.example.ilovecats.MainActivity
import com.example.ilovecats.R
import com.example.ilovecats.adapters.CatImageAdapter
import com.example.ilovecats.databinding.HomeFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }
    val homeViewModel: HomeViewModel by viewModels()
    private lateinit var binding: HomeFragmentBinding
    lateinit var homeActivity: MainActivity
    private lateinit var adapter: CatImageAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false)
        binding.lifecycleOwner = this
        adapter = CatImageAdapter(CatImageAdapter.CatImageListener {
            var bundle = Bundle()
            bundle.putParcelable("cat",it)
            this.findNavController().navigate(R.id.action_homeFragment_to_catImageViewFragment,bundle)
        })
        homeActivity = activity as MainActivity
        homeViewModel.status.observe(viewLifecycleOwner){
            when (it) {
                ApiStatus.ERROR -> {
                    homeActivity.closeLoadingDialog()

                    homeActivity.showErrorDialog(
                        "Error",
                        homeViewModel.errorMessage
                    )
                }
                ApiStatus.DONE -> {
                    homeActivity.closeLoadingDialog()

                }
                ApiStatus.LOADING -> {
                    homeActivity.showLoadingDialog("Loading....")
                }
                ApiStatus.OFFLINE -> {}
            }
        }
        val manager = GridLayoutManager(activity, 2)
        binding.catList.layoutManager = manager
        binding.catList.addItemDecoration(GridSpacingItemDecoration(10,2))
        binding.catList.adapter = adapter
        lifecycleScope.launchWhenCreated {
            homeViewModel.getCatImages().collectLatest {
                adapter.submitData(it)
            }

        }

        return binding.root
    }



}