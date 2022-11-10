package com.example.foodtour2.base

import android.os.Bundle
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.example.foodtour2.bus.Event

interface AbsViewModel {

    /**
     * Call only one time after viewmodel is created
     */
    fun onCreate()

    /**
     * Called when viewmodel binds to scene
     */
    fun onBind(args: Bundle?)

    /**
     * Call when viewmodel attach to scene's lifecycle
     *
     * @param scene
     */
    fun onAttachScene(scene: Scene)

    /**
     * Call when viewmodel is completely created, This can be used for fetching initial-data, initial request from scene, etc
     */
    fun onReady()

    /**
     * Call when viewmodel detach to scene's lifecycle
     *
     * @param scene
     */
    fun onDetachScene(scene: Scene)

    /**
     * Call when the viewmodel is unbound from scene
     */
    fun onUnbind()

    fun getDirections(): LiveData<Event<Direction>>

    fun getRunningTaskCount(): LiveData<Int>

    fun getConfirmEvent(): LiveData<Event<ConfirmRequest>>

    fun getToastEvent(): LiveData<Event<String>>
}

interface Scene {

    fun lifecycleOwner(): LifecycleOwner

    fun <I, O> registerForResultCallback(
        contract: ActivityResultContract<I, O>,
        callback: ActivityResultCallback<O>
    ): ActivityResultLauncher<I>
