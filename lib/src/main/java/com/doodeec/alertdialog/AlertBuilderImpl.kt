package com.doodeec.alertdialog

import android.content.Context
import android.content.DialogInterface
import android.support.annotation.ColorRes
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.widget.ListAdapter
import com.doodeec.alertdialog.AlertBuilderInterface.AlertBuilder

/**
 * @author Dusan Bartos
 * Created on 21.06.2017.
 */
class AlertBuilderImpl : AlertBuilderInterface {

    override fun init(context: Context, theme: Int): AlertBuilder {
        return AlertImpl(context, theme)
    }

    class AlertImpl internal constructor(context: Context, theme: Int) : AlertBuilder {
        val builder: AlertDialog.Builder = AlertDialog.Builder(context, theme)
        private var colorMap = hashMapOf<@DialogButtonType Int, @ColorRes Int>()

        override fun title(title: Int): AlertBuilder {
            builder.setTitle(title)
            return this
        }

        override fun title(title: String): AlertBuilder {
            builder.setTitle(title)
            return this
        }

        override fun message(msg: Int): AlertBuilder {
            builder.setMessage(msg)
            return this
        }

        override fun message(msg: String): AlertBuilder {
            builder.setMessage(msg)
            return this
        }

        override fun adapter(adapter: ListAdapter, listener: DialogInterface.OnClickListener?): AlertBuilder {
            builder.setAdapter(adapter, listener)
            return this
        }

        override fun button(@DialogButtonType type: Int, btn: Int, listener: DialogInterface.OnClickListener): AlertBuilder {
            when (type) {
                DialogInterface.BUTTON_POSITIVE -> builder.setPositiveButton(btn, listener)
                DialogInterface.BUTTON_NEGATIVE -> builder.setNegativeButton(btn, listener)
                DialogInterface.BUTTON_NEUTRAL -> builder.setNeutralButton(btn, listener)
            }
            return this
        }

        override fun button(@DialogButtonType type: Int, btn: Int, listener: DialogInterface.OnClickListener, color: Int): AlertBuilder {
            colorMap[type] = color
            when (type) {
                DialogInterface.BUTTON_POSITIVE -> builder.setPositiveButton(btn, listener)
                DialogInterface.BUTTON_NEGATIVE -> builder.setNegativeButton(btn, listener)
                DialogInterface.BUTTON_NEUTRAL -> builder.setNeutralButton(btn, listener)
            }
            return this
        }

        override fun onDismiss(listener: DialogInterface.OnDismissListener): AlertBuilder {
            builder.setOnDismissListener(listener)
            return this
        }

        override fun show() {
            val d = builder.show()
            colorMap.forEach { type, color -> d.getButton(type)?.setTextColor(ContextCompat.getColor(d.context, color)) }
        }
    }
}