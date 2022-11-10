package com.example.foodtour2.base

import android.app.Application
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import com.example.foodtour2.bus.Event

open class BaseAndroidViewModel(application: Application) : AndroidViewModel(application),
    AbsViewModel {

    private val mDirectionPublisher = MutableLiveData<Event<Direction>>()

    private val mRunningTaskCount = RefCountLiveData()

    private val mConfirmEvent = MutableLiveData<Event<ConfirmRequest>>()

    private val mToastEvent = MutableLiveData<Event<String>>()

    override fun onCreate() {
    }

    override fun onBind(args: Bundle?) {
    }

    override fun onAttachScene(scene: Scene) {
        //intentionally blank: default implementation
    }

    override fun onReady() {
    }

    override fun onDetachScene(scene: Scene) {
        //intentionally blank: default implementation
    }

    override fun onUnbind() {
    }

    override fun getDirections(): LiveData<Event<Direction>> = mDirectionPublisher

    override fun getRunningTaskCount(): LiveData<Int> = mRunningTaskCount

    override fun getConfirmEvent(): LiveData<Event<ConfirmRequest>> = mConfirmEvent

    override fun getToastEvent(): LiveData<Event<String>> = mToastEvent

    protected open fun redirect(intent: Intent) {
        mDirectionPublisher.value = IntentDirection(intent).asEvent()
    }

    protected open fun redirect(
        direction: NavDirections,
        options: NavOptions? = null,
        finish: Boolean = false
    ) {
        mDirectionPublisher.value = NavActionDirection(direction, options, finish).asEvent()
    }

    protected open fun redirect(direction: Direction) {
        mDirectionPublisher.value = direction.asEvent()
    }

    protected open fun notifyTaskStart() {
        mRunningTaskCount.postIncrease()
    }

    protected open fun notifyTaskFinish() {
        mRunningTaskCount.postDecrease()
    }

    protected open fun requestConfirm(request: ConfirmRequest) {
        mConfirmEvent.postValue(request.asEvent())
    }

    protected open fun toast(@StringRes resId: Int) {
        mToastEvent.postValue(resource.getString(resId).asEvent())
    }

    protected open fun toast(text: String) {
        mToastEvent.postValue(text.asEvent())
    }

    protected val resource: Resources = application.resources
}