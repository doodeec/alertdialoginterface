package test

import android.content.Context
import android.content.DialogInterface
import android.util.Log
import android.widget.ListAdapter
import com.doodeec.alertdialog.AlertBuilderInterface
import com.doodeec.alertdialog.AlertBuilderInterface.AlertBuilder
import com.doodeec.alertdialog.DialogButtonType

/**
 * @author Dusan Bartos
 * Created on 21.06.2017.
 */
class MockAlertBuilder : AlertBuilderInterface {

    val shownMap = hashSetOf<MockAlert>()
//    val shownMap = hashMapOf<String, MockAlert>()

    override fun init(context: Context, theme: Int): AlertBuilder {
        val st = Thread.currentThread().stackTrace[2]
        val owner = "${st.className}#${st.methodName}"
        Log.i("MockAlertBuilder", "init from $owner")
        return MockAlert(onShow = { shownMap.add(it) }, onDismiss = { shownMap.remove(it) })
//        return MockAlert(onShow = { shownMap.put(owner, it) }, onDismiss = { shownMap.remove(owner) })
    }

    fun getFirstVisible(): MockAlert? = shownMap.firstOrNull()
//    fun get(owner: String): MockAlert? = shownMap[owner]

    class MockAlert internal constructor(onShow: (MockAlert) -> Unit, onDismiss: (MockAlert) -> Unit) : AlertBuilder, DialogInterface {
        private var listenerMap = hashMapOf<@DialogButtonType Int, DialogInterface.OnClickListener>()
        private var adapterListener: DialogInterface.OnClickListener? = null
        private var dismissListener: DialogInterface.OnDismissListener? = null

        private var onShowAction: ((MockAlert) -> Unit)? = onShow
        private var onDismissAction: ((MockAlert) -> Unit)? = onDismiss

        override fun title(title: Int): AlertBuilder = this
        override fun title(title: String): AlertBuilder = this
        override fun message(msg: Int): AlertBuilder = this
        override fun message(msg: String): AlertBuilder = this
        override fun adapter(adapter: ListAdapter, listener: DialogInterface.OnClickListener?): AlertBuilder {
            adapterListener = listener
            return this
        }
        override fun button(type: Int, btn: Int, listener: DialogInterface.OnClickListener): AlertBuilder {
            listenerMap[type] = listener
            return this
        }
        override fun button(type: Int, btn: Int, listener: DialogInterface.OnClickListener, color: Int): AlertBuilder {
            listenerMap[type] = listener
            return this
        }

        override fun onDismiss(listener: DialogInterface.OnDismissListener): AlertBuilder {
            dismissListener = listener
            return this
        }

        override fun show() {
            onShowAction?.invoke(this)
        }

        override fun cancel() {
            dismissListener?.onDismiss(this)
            onDismissAction?.invoke(this)
        }

        override fun dismiss() {
            dismissListener?.onDismiss(this)
            onDismissAction?.invoke(this)
        }

        fun click(@DialogButtonType type: Int) {
            listenerMap[type]?.onClick(this, type)
            dismiss()
        }

        fun adapterClick(which: Int) {
            adapterListener?.onClick(this, which)
            dismiss()
        }

    }
}