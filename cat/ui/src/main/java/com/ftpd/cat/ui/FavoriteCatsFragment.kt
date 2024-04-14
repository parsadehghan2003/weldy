package com.ftpd.cat.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ftpd.base.Cat
import com.ftpd.base.DataState
import com.ftpd.cat.domain.GetCatsObject
import com.ftpd.cat.domain.GetFavoriteCatsObject
import com.ftpd.weldy.navigator.DestinationFragment
import com.ftpd.weldy.navigator.NavigationHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteCatsFragment : Fragment() {

    private val catsViewModel: CatsViewModel by viewModels()
    private lateinit var rootView: LinearLayout
    private lateinit var catsAdapter: CatsAdapter
    private lateinit var recyclerView: RecyclerView
    private var placesList: List<Cat> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        catsViewModel.getFavoriteCatList()

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        rootView = LinearLayout(requireContext())
        rootView.layoutDirection = View.LAYOUT_DIRECTION_LTR
        rootView.orientation = LinearLayout.VERTICAL
        recyclerView = RecyclerView(requireContext())
        rootView.addView(recyclerView, -1, -1)
        recyclerView.layoutManager = GridLayoutManager(context, 3, RecyclerView.VERTICAL, false)
        catsAdapter = CatsAdapter(object : CatsAdapterEventListener{
            override fun onItemClickListener(cat: Cat) {
                val args = HashMap<String, Any>()
                    args[CatConstants.CAT_KEY] = cat
                    NavigationHelper.navigateToDestination(
                        DestinationFragment.CAT_FRAGMENT,
                        replace = false,
                        addToBackStack = true,
                        arg = args
                    )

            }

        })
        recyclerView.adapter = catsAdapter
        recyclerView.addItemDecoration(
            MarginItemDecoration(8.dp())
        )


        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        catsViewModel.favoriteCatsLiveData.observe(
            viewLifecycleOwner
        ) {
            when (it) {
                is DataState.Data -> {
                    val response = it.data as GetFavoriteCatsObject.GetFavoriteCatsObjectResponse
                    placesList += response.cats
                    catsAdapter.submitList(placesList)
                }

                is DataState.Error -> {

                }
            }
        }
    }

}
