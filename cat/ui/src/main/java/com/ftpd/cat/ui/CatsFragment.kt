package com.ftpd.cat.ui

import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.ContextThemeWrapper
import android.view.Gravity
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView.OnScrollListener
import android.widget.LinearLayout
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ftpd.base.Cat
import com.ftpd.base.DataState
import com.ftpd.cat.domain.GetCatsObject
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.MaterialToolbar
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.ceil

@AndroidEntryPoint
class CatsFragment : Fragment() {

    private val catsViewModel: CatsViewModel by viewModels()
    private lateinit var rootView: LinearLayout
    private lateinit var catsAdapter: CatsAdapter
    private lateinit var recyclerView: RecyclerView
    private var placesList: List<Cat> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        catsViewModel.getPostList(GetCatsObject.GetPostsObjectRequest(limit = CatListConstants.LIST_LIMIT, page = CatListConstants.page))

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
        catsAdapter = CatsAdapter()
        recyclerView.adapter = catsAdapter
        recyclerView.addItemDecoration(
            MarginItemDecoration(8.dp())
        )
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                    val lastVisiblePosition =
                        (recyclerView.layoutManager as GridLayoutManager?)!!.findLastVisibleItemPosition()
                    if (lastVisiblePosition >= 5) {
                        catsViewModel.getPostList(GetCatsObject.GetPostsObjectRequest(limit = CatListConstants.LIST_LIMIT, page = CatListConstants.page++))
                    }


            }
        })

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        catsViewModel.catsLiveData.observe(
            viewLifecycleOwner
        ) {
            when (it) {
                is DataState.Data -> {
                    val response = it.data as GetCatsObject.GetPostsObjectResponse
                    placesList += response.cats
                    catsAdapter.submitList(placesList)
                }

                is DataState.Error -> {

                }
            }
        }
    }

}

fun Int.dp(): Int {
    return if (this == 0) {
        0
    } else ceil((Resources.getSystem().displayMetrics.density * this).toDouble()).toInt()
}