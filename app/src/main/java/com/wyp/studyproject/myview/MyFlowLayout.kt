package com.wyp.studyproject.myview

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import kotlin.math.max


class MyFlowLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): ViewGroup(context,attrs,defStyleAttr) {
    val Int.dp: Int
        get() = (this * Resources.getSystem().displayMetrics.density).toInt()
    val widthSpace = 16.dp
    val heightSpace = 8.dp
    var totalWidth = 0
    var totalHeight = 0
    var alllines: MutableList<List<View>> = mutableListOf()
    val lineHeights: MutableList<Int> = mutableListOf()
    // 参数是viewgroup的坐标
    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        // 遍历确定每一个孩子的坐标
        val lineCount = lineHeights.size
        var pl = paddingLeft
        var pt = paddingTop
        Log.d("test","lineCount = $lineCount,listView.size = ${alllines[1].size}")
         for (i in (0..lineCount - 1)) {
             val listView = alllines[i]
             val lineHeight = lineHeights[i]
             for (j in (0..listView.size - 1)) {
                 // 拿到每一个view
                 val view = listView[j]
                 // 对view进行layout
                 val left = pl
                 val top = pt
                 Log.d("test","${view.measuredWidth},${view.measuredHeight}")
                 val right = left + view.measuredWidth
                 val bottom = top + view.measuredHeight
                 Log.d("test","$left,$top,$right,$bottom")
                 view.layout(left,top,right,bottom)
                 pl += view.measuredWidth + widthSpace
             }
             pl = paddingLeft
             pt += (lineHeight + heightSpace)
         }
    }
    // measure 确定子view大小  确定子view坐标
    // 度量孩子的大小，通过度量孩子的大小，度量自己的大小
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        alllines.clear()
        lineHeights.clear()
        totalWidth = 0
        totalHeight = 0

        val selfWidth = MeasureSpec.getSize(widthMeasureSpec)
        val selfHeight = MeasureSpec.getSize(heightMeasureSpec)

        val childCount = childCount
        val pl = paddingLeft
        val pr = paddingRight
        val pt = paddingTop
        val pb = paddingBottom

        val lineViews: MutableList<View> = mutableListOf()
        var lineWidthUsed = 0
        var lintHeight = 0

        // 度量孩子
        for (i in (0..childCount - 1)) {
            val childView = getChildAt(i)
            val childLP = childView.layoutParams
            val childMeasureSpeWidth = getChildMeasureSpec(widthMeasureSpec,pl + pr,childLP.width)
            val childMeasureSpeHeight = getChildMeasureSpec(heightMeasureSpec,pt + pb,childLP.height)
            childView.measure(childMeasureSpeWidth,childMeasureSpeHeight)
            // 确定自己的大小
            // 宽怎么确定？所有行的宽度的最大值
            // 高怎么确定？所有行的高度相加
            val childWidth = childView.measuredWidth
            val childHeight = childView.measuredHeight

            // 如果需要换行
            if (lineWidthUsed + childWidth > selfWidth) {
                alllines.add(ArrayList(lineViews))
                lineViews.clear()
                lineHeights.add(lintHeight)
                totalWidth = max(totalWidth,lineWidthUsed + widthSpace)
                totalHeight += (lintHeight + heightSpace)
                lineWidthUsed = 0
                lintHeight = 0
            }
            lineViews.add(childView)

            lineWidthUsed = lineWidthUsed + childWidth + widthSpace
            lintHeight = max(lintHeight,childHeight)
        }
        // 处理最后一行
        alllines.add(ArrayList(lineViews))
        lineHeights.add(lintHeight)
        lineViews.clear()
        totalWidth = max(totalWidth,lineWidthUsed + widthSpace)
        totalHeight += (lintHeight + heightSpace)

        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val realWidth = if(widthMode == MeasureSpec.EXACTLY) selfWidth else totalWidth
        val realHeight = if (heightMode == MeasureSpec.EXACTLY) selfHeight else totalHeight

        setMeasuredDimension(realWidth,realHeight)
    }
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
    }
}