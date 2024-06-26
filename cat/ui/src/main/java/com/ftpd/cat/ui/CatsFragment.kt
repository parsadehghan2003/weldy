package com.ftpd.cat.ui

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ftpd.base.Cat
import com.ftpd.base.DataState
import com.ftpd.cat.domain.GetCatsObject
import com.ftpd.weldy.navigator.DestinationFragment
import com.ftpd.weldy.navigator.NavigationHelper
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
        catsViewModel.getCatList(GetCatsObject.GetPostsObjectRequest(limit = CatConstants.LIST_LIMIT, page = CatConstants.page))

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        rootView = LinearLayout(requireContext())
        rootView.layoutDirection = View.LAYOUT_DIRECTION_LTR
        rootView.orientation = LinearLayout.VERTICAL
        val favoriteButton = ImageButton(requireContext())
        favoriteButton.setImageResource(android.R.drawable.btn_star_big_on)
        rootView.addView(favoriteButton,50.dp(),50.dp())
        favoriteButton.setOnClickListener {
            NavigationHelper.navigateToDestination(
                DestinationFragment.FAVORITE_CATS_FRAGMENT,
                replace = true,
                addToBackStack = true,
            )
        }
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
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                    val lastVisiblePosition =
                        (recyclerView.layoutManager as GridLayoutManager?)!!.findLastVisibleItemPosition()
                    if (lastVisiblePosition >= 5) {
                        catsViewModel.getCatList(GetCatsObject.GetPostsObjectRequest(limit = CatConstants.LIST_LIMIT, page = CatConstants.page++))
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