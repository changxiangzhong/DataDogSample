package com.example.datadogsample

import android.app.Application
import android.util.Log
import com.datadog.android.Datadog
import com.datadog.android.DatadogSite
import com.datadog.android.core.configuration.Configuration
import com.datadog.android.core.configuration.Credentials
import com.datadog.android.privacy.TrackingConsent
import com.datadog.android.rum.GlobalRum
import com.datadog.android.rum.RumMonitor
import com.datadog.android.rum.tracking.FragmentViewTrackingStrategy

class SampleApp: Application() {

    override fun onCreate() {
        super.onCreate()

        val clientToken = "pub1XXXXXXXX"
        val applicationId = "3caXXXXXXX"

        val environmentName = "production"
        val appVariantName = "sample-sample"

        val configuration = Configuration.Builder(
            rumEnabled = true,
            crashReportsEnabled = true,
            logsEnabled = true,
            tracesEnabled = true
        )
            .trackInteractions()
            .trackLongTasks(1000)
            .useViewTrackingStrategy(FragmentViewTrackingStrategy(true))
            .useSite(DatadogSite.EU1)
            .build()
        val credentials = Credentials(clientToken,environmentName,appVariantName,applicationId)
        Datadog.initialize(this, credentials, configuration, TrackingConsent.GRANTED)
        GlobalRum.registerIfAbsent(RumMonitor.Builder().build())
        Log.d("===>", "DataDog init() done")
    }

}