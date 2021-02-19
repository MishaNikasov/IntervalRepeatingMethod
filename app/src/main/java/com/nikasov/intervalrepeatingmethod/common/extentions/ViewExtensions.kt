package com.nikasov.intervalrepeatingmethod.common.extentions

import android.view.View
import android.widget.PopupMenu

fun View.showPopUpMenu(menuRes: Int, clickListener: PopupMenu.OnMenuItemClickListener?) {
    val popupMenu = PopupMenu(this.context, this)
    popupMenu.inflate(menuRes)
    clickListener?.let { popupMenu.setOnMenuItemClickListener(clickListener) }
    popupMenu.show()
}