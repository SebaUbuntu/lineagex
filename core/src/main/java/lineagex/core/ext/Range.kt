/*
 * SPDX-FileCopyrightText: 2024 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package lineagex.core.ext

import android.util.Range
import kotlin.math.roundToInt

fun Range<Int>.map(percentage: Float): Int {
    return (((upper - lower) * percentage) + lower).roundToInt()
}
