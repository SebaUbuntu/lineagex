/*
 * SPDX-FileCopyrightText: 2023-2024 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package lineagex.core.ext

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable
import kotlin.reflect.KClass
import kotlin.reflect.safeCast

fun <T : Parcelable> Parcel.readParcelable(clazz: KClass<T>, loader: ClassLoader? = null) =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        readParcelable(loader, clazz.java)
    } else {
        @Suppress("DEPRECATION")
        readParcelable(loader)
    }

fun <T : Serializable> Parcel.readSerializable(clazz: KClass<T>, loader: ClassLoader? = null) =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        readSerializable(loader, clazz.java)
    } else {
        @Suppress("DEPRECATION")
        clazz.safeCast(readSerializable())
    }
