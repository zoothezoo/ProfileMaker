package jp.co.cyberagent.dojo2019

import android.app.Application
import jp.co.cyberagent.dojo2019.db.AppDatabase

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        AppDatabase.init(this)
    }
}