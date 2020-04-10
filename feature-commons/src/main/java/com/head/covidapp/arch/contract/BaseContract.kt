package com.head.covidapp.arch.contract

interface BaseContract {

    interface View

    interface Presenter<V : View> {

        var view: V?

        fun attachView(view: V) {
            this.view = view
        }

        fun detachView() {
            view = null
        }
    }
}
