package br.com.asoncs.widget

import android.app.Activity
import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.RemoteViews
import androidx.preference.PreferenceManager
import br.com.asoncs.service.ServiceActions
import br.com.asoncs.service.ServiceContract
import br.com.asoncs.service.doVibration


/**
 * The configuration screen for the [Light] AppWidget.
 */

class WidgetConfigureActivity : Activity() {

    companion object {

        private const val CLICK_ACTION = "br.com.asoncs.widget.CLICK_ACTION"

        private fun getPendingIntent(
            context: Context,
            appWidgetId: Int,
            serviceAction: ServiceActions,
            originClass: Class<*>
        ) : PendingIntent {
            val intent = Intent(context, originClass).apply {
                action = CLICK_ACTION
                putExtra("action", serviceAction)
                putExtra("appWidgetId", appWidgetId)
            }
            return PendingIntent.getBroadcast(
                context,
                0,
                intent,
                0
            )
        }

        private fun doAction(
            context: Context,
            action: ServiceActions,
            result: () -> Unit
        ) {
            doVibration(context)
            ServiceContract
                .newInstance(
                    String.format(
                        "http://%s",
                        PreferenceManager
                            .getDefaultSharedPreferences(context)
                            .getString(
                                "ip_route",
                                context.resources.getString(R.string.mod_widget_pattern_url)
                            )!!
                    )
                )
                .action(
                    action,
                    {
                        result()
                    },
                    {
                        Log.e("SweetHome", "Action Failure\n${it.message}")
                        result()
                    }
                )
        }

        fun onUpdateHelper(
            context: Context,
            appWidgetManager: AppWidgetManager,
            appWidgetIds: IntArray,
            originClass: Class<*>,
            widgetType: WidgetType
        ) {
            // There may be multiple widgets active, so update all of them
            appWidgetIds.forEach { appWidgetId ->
                val views = RemoteViews(context.packageName, widgetType.layout )

                views.setOnClickPendingIntent(
                    widgetType.imageView,
                    getPendingIntent(
                        context,
                        appWidgetId,
                        widgetType.action,
                        originClass
                    )
                )

                // Instruct the widget manager to update the widget
                appWidgetManager.updateAppWidget(appWidgetId, views)
            }
        }

        fun onReceiveHelper(
            context: Context?,
            intent: Intent?,
            originClass: Class<*>,
            widgetType: WidgetType
        ) {
            context?.let { ctx ->
                intent
                    ?.takeIf { it.action == CLICK_ACTION }
                    ?.getSerializableExtra("action")
                    ?.takeIf { action -> action is ServiceActions }
                    ?.let { action ->
                        action as ServiceActions
                        val appWidgetManager = AppWidgetManager.getInstance(ctx)
                        val componentName = ComponentName(ctx, originClass)
                        val views = RemoteViews(ctx.packageName, widgetType.layout )
                        appWidgetManager.updateAppWidget(
                            componentName,
                            views.apply {
                                setImageViewResource(
                                    widgetType.imageView,
                                    widgetType.imageActivate
                                )
                            }
                        )
                        doAction(
                            context,
                            action
                        ) {
                            appWidgetManager.updateAppWidget(
                                componentName,
                                views.apply {
                                    setImageViewResource(
                                        widgetType.imageView,
                                        widgetType.image
                                    )
                                }
                            )
                        }
                }
            }
        }
    }

    public override fun onCreate(icicle: Bundle?) {
        super.onCreate(icicle)

        var appWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID

        // Find the widget id from the intent.
        val intent = intent
        val extras = intent.extras
        if (extras != null) {
            appWidgetId = extras.getInt(
                AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID
            )
        }

        // If this activity was started with an intent without an app widget ID, finish with an error.
        if (appWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish()
            return
        }

        // Make sure we pass back the original appWidgetId
        val resultValue = Intent()
        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
        setResult(RESULT_OK, resultValue)
        finish()
    }

}