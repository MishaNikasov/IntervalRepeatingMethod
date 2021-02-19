package com.nikasov.intervalrepeatingmethod.common.extentions

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

const val PATTERN_CLIENT = "dd/MM/yyyy"
const val PATTERN_SERVER = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'"
const val PATTERN_TIME = "HH:mm"
const val PATTERN_DAY = "EEE, MMM d"
const val PATTERN_DATE = "d MMM, yyyy"
const val PATTERN_FILE = "d_MMM_yyyy"

@SuppressLint("SimpleDateFormat")
fun Date?.getFormatTime(): String {
    this?.let {
        return SimpleDateFormat(PATTERN_DAY).format(this)
    }
    return ""
}