package com.viva.photo.control

import com.viva.photo.control.ys.MainParser
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

    fun load(parser: BaseParser?, onLoadListener: OnLoadListener?) {
        Observable.create(ObservableOnSubscribe<Any> {
            it ->
            run {
                parser?.connect()
                var result = parser?.parser()
                if (result != null) {
                    it.onNext(result)
                } else {
                    it.onComplete()
                }
            }
        })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Any> {
                    override fun onNext(t: Any?) {
                        onLoadListener?.onFinish(t)
                    }

                    override fun onComplete() {
                        onLoadListener?.onFinish(null)
                    }

                    override fun onError(e: Throwable?) {
                        onLoadListener?.onFinish(null)
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

interface OnLoadListener {
    fun onFinish(any: Any?)
}