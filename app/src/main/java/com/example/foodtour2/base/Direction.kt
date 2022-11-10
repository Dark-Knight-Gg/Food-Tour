package com.example.foodtour2.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions

open class Direction(val finish: Boolean = false)

/**
 * Dedicate for a back navigate direction (simply like back pressed)
 */
object Backward : Direction()

/**
 * Dedicate for a finish (close activity, dismiss dialog)
 */
object Finish : Direction(true)

/**
 * Dedicate for a finish (close activity, dismiss dialog) with result
 */
class SetResult(val resultCode: Int, val data: Intent? = null, finish: Boolean = false) :
    Direction(finish) {

    constructor(resultCode: Int, data: Bundle? = null, finish: Boolean = false) : this(
        resultCode,
        data?.toDataIntent(),
        finish
    )
}

/**
 * Dedicate for an intent navigation
 */
class IntentDirection(val intent: Intent, finish: Boolean = false) : Direction(finish)

/**
 * Dedicate for an intent navigation with result is required. [Activity.startActivityForResult]
 */
@Deprecated(level = DeprecationLevel.WARNING, message = "use Scene#registerForResultCallback")
class IntentForResultDirection(
    val intent: Intent,
    val requestCode: Int,
    val options: Bundle? = null,
    finish: Boolean = false,
) : Direction(finish)

/**
 * Dedicate for an customize navigation which cannot be created from inside of viewmodel (Google SignIn,...)
 */
@Suppress("unused")
class ActionDirection(val actionId: Int, val args: Bundle? = null, finish: Boolean = false) :
    Direction(finish)

/**
 * Dedicate for navigation via nav-graph action
 */
class NavGraphDirection(
    @IdRes val actionId: Int,
    val args: Bundle? = null,
    finish: Boolean = false
) : Direction(finish)

/**
 * Dedicate for navigation via nav-graph [NavDirections]
 */
class NavActionDirection(
    val direction: NavDirections,
    val options: NavOptions? = null,
    finish: Boolean = false
) : Direction(finish)

@Deprecated(level = DeprecationLevel.WARNING, message = "use Scene#registerForResultCallback")
class PermissionDirection(val permission: Array<out String>, val reqCode: Int) :
    Direction(finish = false)