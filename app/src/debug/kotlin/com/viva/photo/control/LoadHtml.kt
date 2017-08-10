package com.viva.photo.control

import com.viva.photo.utils.LogUtils
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class LoadHtml {

    private var cancel = false
    private var dispose: Disposable? = null

    fun cancel() {
        cancel = true
        dispose?.dispose()
    }

    fun load() {
        Observable.create(ObservableOnSubscribe<Any> {
            it ->
            run {
                var paraser = YeskyParser()
                var result = paraser.stringOfHtml()
                if (result != null) {
                    it.onNext(result)
                } else {
                    it.onNext("")
                    it.onComplete()
                }
            }
        })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Any> {
                    override fun onNext(t: Any?) {
                        LogUtils.v("sgc " + t as String)
                    }

                    override fun onComplete() {
                    }

                    override fun onError(e: Throwable?) {

                    }

                    override fun onSubscribe(d: Disposable?) {
                        if (cancel) {
                            d?.dispose()
                        } else {
                            dispose = d
                        }
                    }

                })
    }

}