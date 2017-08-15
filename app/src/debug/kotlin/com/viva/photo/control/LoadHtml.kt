package com.viva.photo.control

import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class LoadHtml {

    private var stop = false
    private var dispose: Disposable? = null

    fun cancel() {
        stop = true
        dispose?.dispose()
    }

    fun isStop(): Boolean {
        return stop
    }

    private fun reset() {
        stop = false
        dispose = null
    }

    fun load(parser: BaseParser?, onLoadListener: OnLoadListener?) {
        reset()
        Observable.create(ObservableOnSubscribe<Any> {
            it ->
            run {
                parser?.clear()
                parser?.connect()
                var result = parser?.parser()
                if (result != null) {
                    it.onNext(result)
                }
                it.onComplete()
            }
        })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Any> {
                    override fun onNext(t: Any?) {
                        onLoadListener?.onNext(t)
                    }

                    override fun onComplete() {
                        stop = true
                        onLoadListener?.onFinish()
                    }

                    override fun onError(e: Throwable?) {
                        stop = true
                        onLoadListener?.onFinish()
                    }

                    override fun onSubscribe(d: Disposable?) {
                        if (stop) {
                            d?.dispose()
                        } else {
                            dispose = d
                        }
                    }

                })
    }

}

interface OnLoadListener {
    fun onNext(any: Any?)
    fun onFinish()
}