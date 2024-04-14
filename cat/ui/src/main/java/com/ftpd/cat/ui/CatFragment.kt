package com.ftpd.cat.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.ftpd.base.Cat
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CatFragment : Fragment() {

    private lateinit var mainRootView: LinearLayout
    private lateinit var rootView: ConstraintLayout
    private lateinit var cat: Cat
    private val catsViewModel: CatsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cat = arguments?.getSerializable(CatConstants.CAT_KEY) as Cat

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mainRootView = LinearLayout(requireContext())
        mainRootView.layoutDirection = View.LAYOUT_DIRECTION_LTR
        mainRootView.orientation = LinearLayout.VERTICAL
        mainRootView.setBackgroundColor(Color.parseColor("#80000000"))
        mainRootView.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }
        rootView = ConstraintLayout(requireContext()).apply {
            id = R.id.rootView
            layoutParams =
                ConstraintLayout.LayoutParams(-1, -1)
        }
        rootView.layoutDirection = View.LAYOUT_DIRECTION_LTR
        val layoutParams = LinearLayout.LayoutParams(-1, -1, 1f)
        layoutParams.setMargins(
            24.dp(),
            60.dp(),
            24.dp(),
            50.dp()
        )
        rootView.setOnClickListener { }
        mainRootView.addView(rootView, layoutParams)

        val imageView = ImageView(context).apply { id = R.id.imageView }

        addViewToConstraintLayout(rootView, imageView)

        addConstraintSet(imageView.id, height = -1, width = -1, parentView = rootView)
        Glide.with(requireContext()).load(cat.url).apply(
            RequestOptions().transform(
                CenterCrop(),
                RoundedCorners(50)
            )
        ).into(imageView)
        val likeImageView = ImageView(context).apply { id = R.id.likeImageView }
        likeImageView.setImageResource(android.R.drawable.ic_menu_add)
        addViewToConstraintLayout(rootView, likeImageView)
        addConstraintSet(
            likeImageView.id,
            height = 50.dp(),
            width = 50.dp(),
            topToTop = rootView.id,
            bottomToBottom = rootView.id,
            endToEnd = rootView.id,
            startToStart = rootView.id,
            verticalBias = 1f,
            marginBottom = 12.dp(),
            parentView = rootView
        )
        likeImageView.setOnClickListener {
            catsViewModel.addFavoriteCat(cat)

        }
        return mainRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}

fun Fragment.addViewToConstraintLayout(
    parentView: ConstraintLayout,
    childView: View
) {
    parentView.addView(childView)
}


fun Fragment.addConstraintSet(
    childViewId: Int,
    height: Int,
    width: Int,
    topToTop: Int? = null,
    topToBottom: Int? = null,
    bottomToTop: Int? = null,
    bottomToBottom: Int? = null,
    startToStart: Int? = null,
    startToEnd: Int? = null,
    endToEnd: Int? = null,
    endToStart: Int? = null,
    marginTop: Int = 0,
    marginBottom: Int = 0,
    marginStart: Int = 0,
    marginEnd: Int = 0,
    horizontalBias: Float = 0.5f,
    verticalBias: Float = 0.5f,
    verticalWeight: Float = ConstraintSet.UNSET.toFloat(),
    horizontalWeight: Float = 0f,
    parentView: ConstraintLayout
) {
    val set = ConstraintSet()
    set.constrainHeight(childViewId, height)
    set.constrainWidth(childViewId, width)
    set.setHorizontalBias(childViewId, horizontalBias)
    set.setVerticalBias(childViewId, verticalBias)
    set.setVerticalWeight(childViewId, verticalWeight)
    topToTop?.let {
        set.connect(
            childViewId,
            ConstraintSet.TOP,
            topToTop,
            ConstraintSet.TOP,
            marginTop
        )
    }
    if (topToBottom != null) {
        set.connect(childViewId, ConstraintSet.TOP, topToBottom, ConstraintSet.BOTTOM, marginTop)
    }
    if (bottomToTop != null) {
        set.connect(childViewId, ConstraintSet.BOTTOM, bottomToTop, ConstraintSet.TOP, marginBottom)
    }
    if (bottomToBottom != null) {
        set.connect(
            childViewId,
            ConstraintSet.BOTTOM,
            bottomToBottom,
            ConstraintSet.BOTTOM,
            marginBottom
        )
    }
    if (startToStart != null) {
        set.connect(
            childViewId,
            ConstraintSet.START,
            startToStart,
            ConstraintSet.START,
            marginStart
        )
    }
    if (startToEnd != null) {
        set.connect(childViewId, ConstraintSet.START, startToEnd, ConstraintSet.END, marginStart)
    }
    if (endToEnd != null) {
        set.connect(childViewId, ConstraintSet.END, endToEnd, ConstraintSet.END, marginEnd)
    }
    if (endToStart != null) {
        set.connect(childViewId, ConstraintSet.END, endToStart, ConstraintSet.START, marginEnd)
    }

    set.applyTo(parentView)

}
