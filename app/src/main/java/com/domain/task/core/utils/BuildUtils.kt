package com.domain.task.core.utils

import android.os.Build


object BuildUtils {


    @JvmStatic val isAtLeast23Api: Boolean
        get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M


}