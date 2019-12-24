package br.com.asoncs.widget.widgets

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import br.com.asoncs.service.ServiceActions
import br.com.asoncs.widget.WidgetConfigureActivity
import br.com.asoncs.widget.getWidgetType

open class ActionWidget(
    private val action: ServiceActions
) : AppWidgetProvider()
{
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        WidgetConfigureActivity
            .onUpdateHelper(
                context = context,
                appWidgetManager = appWidgetManager,
                appWidgetIds = appWidgetIds,
                originClass = javaClass,
                widgetType = getWidgetType(action)
            )
    }

    override fun onReceive(
        context: Context?,
        intent: Intent?
    ) {
        WidgetConfigureActivity
            .onReceiveHelper(
                context = context,
                intent = intent,
                originClass = javaClass,
                widgetType = getWidgetType(action)
            )
        super.onReceive(context, intent)
    }
}