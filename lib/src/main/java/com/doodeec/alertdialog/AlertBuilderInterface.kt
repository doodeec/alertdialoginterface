package com.doodeec.alertdialog

import android.content.Context
import android.content.DialogInterface
import android.support.annotation.ColorRes
import android.support.annotation.StringRes
import android.support.annotation.StyleRes
import android.widget.ListAdapter

/**
 * @author Dusan Bartos
 * Created on 21.06.2017.
 */
interface AlertBuilderInterface {
    fun init(context: Context, @StyleRes theme: Int): AlertBuilder

    interface AlertBuilder {
        fun title(@StringRes title: Int): AlertBuilder
        fun title(title: String): AlertBuilder
        fun message(@StringRes msg: Int): AlertBuilder
        fun message(msg: String): AlertBuilder
        fun adapter(adapter: ListAdapter, listener: DialogInterface.OnClickListener?): AlertBuilder
        fun button(@DialogButtonType type: Int, @StringRes btn: Int, listener: DialogInterface.OnClickListener?): AlertBuilder
        fun button(@DialogButtonType type: Int, @StringRes btn: Int, listener: DialogInterface.OnClickListener?, @ColorRes color: Int): AlertBuilder
        fun onDismiss(listener: DialogInterface.OnDismissListener): AlertBuilder
        fun show()
    }
}