package jp.co.cyberagent.dojo2019

import android.app.Application
import jp.co.cyberagent.dojo2019.data.db.AppDatabase

class App: Application() {
    //dbを最初のみ作成
    override fun onCreate() {
        super.onCreate()
        AppDatabase.init(this)
    }
}