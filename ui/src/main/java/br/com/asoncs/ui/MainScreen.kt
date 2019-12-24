package br.com.asoncs.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import br.com.asoncs.service.ServiceActions.*
import br.com.asoncs.service.ServiceContract
import br.com.asoncs.service.doVibration
import kotlinx.android.synthetic.main.main_controler.*

class MainScreen : Fragment() {

    private lateinit var serviceContract: ServiceContract

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = View.inflate(context, R.layout.main_controler, container)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val endPoint = context
            ?.let { ctx ->
                PreferenceManager
                    .getDefaultSharedPreferences(ctx)
                    .getString(
                        "ip_route",
                        ctx.getString(R.string.pattern_url
                        )
                    )
            } ?: ""
        serviceContract = ServiceContract.newInstance(
            String.format(
                "http://%s",
                endPoint
            )
        )

        btnMenu.setOnClickListener { startActivity(Intent(context, PrefsScreen::class.java)) }

        btnWebView.setOnClickListener { startActivity(Intent(context, WebViewScreen::class.java)) }

        light_toggle.setOnClickListener { doVibration(context); doVibration(context); lightToggle(it) }
        tv_c_toggle.setOnClickListener { doVibration(context); tvcToggle(it) }
        tv_channel_down.setOnClickListener { doVibration(context); tvChannelDown(it) }
        tv_channel_up.setOnClickListener { doVibration(context); tvChannelUp(it) }
        tv_volume_down.setOnClickListener { doVibration(context); tvVolumeDown(it) }
        tv_volume_up.setOnClickListener { doVibration(context); tvVolumeUp(it) }
        tv_c_tools.setOnClickListener { doVibration(context); tvcTools(it) }
        tv_c_apps.setOnClickListener { doVibration(context); tvcApps(it) }
        tv_c_enter.setOnClickListener { doVibration(context); tvcEnter(it) }
        tv_c_menu.setOnClickListener { doVibration(context); tvcMenu(it) }
        tv_c_return.setOnClickListener { doVibration(context); tvcReturn(it) }
        tv_c_up.setOnClickListener { doVibration(context); tvcUp(it) }
        tv_c_left.setOnClickListener { doVibration(context); tvcLeft(it) }
        tv_c_right.setOnClickListener { doVibration(context); tvcRight(it) }
        tv_c_down.setOnClickListener { doVibration(context); tvcDown(it) }
    }

    fun lightToggle(imageView: View) {
        (imageView as ImageView)
            .apply { 
                setImageResource(R.drawable.light_toggle_ac)
                serviceContract
                    .action(
                        LIGHT_TOGGLE,
                        { setImageResource(R.drawable.light_toggle) },
                        { setImageResource(R.drawable.light_toggle) }
                    )
            }
    }
    
    fun tvcToggle(imageView: View) {
        (imageView as ImageView)
            .apply {
                setImageResource(R.drawable.tv_c_toggle_ac)
                serviceContract
                    .action(
                        TV_C_TOGGLE,
                        { setImageResource(R.drawable.tv_c_toggle) },
                        { setImageResource(R.drawable.tv_c_toggle) }
                    )
            }
    }

    fun tvChannelDown(imageView: View) {
        (imageView as ImageView)
            .apply {
                setImageResource(R.drawable.tv_channel_down_ac)
                serviceContract
                    .action(
                        TV_CHANNEL_DOWN,
                        { setImageResource(R.drawable.tv_channel_down) },
                        { setImageResource(R.drawable.tv_channel_down) }
                    )
            }
    }

    fun tvChannelUp(imageView: View) {
        (imageView as ImageView)
            .apply {
                setImageResource(R.drawable.tv_channel_up_ac)
                serviceContract
                    .action(
                        TV_CHANNEL_UP,
                        { setImageResource(R.drawable.tv_channel_up) },
                        { setImageResource(R.drawable.tv_channel_up) }
                    )
            }
    }

    fun tvVolumeDown(imageView: View) {
        (imageView as ImageView)
            .apply {
                setImageResource(R.drawable.tv_volume_down_ac)
                serviceContract
                    .action(
                        TV_VOLUME_DOWN,
                        { setImageResource(R.drawable.tv_volume_down) },
                        { setImageResource(R.drawable.tv_volume_down) }
                    )
            }
    }

    fun tvVolumeUp(imageView: View) {
        (imageView as ImageView)
            .apply {
                setImageResource(R.drawable.tv_volume_up_ac)
                serviceContract
                    .action(
                        TV_VOLUME_UP,
                        { setImageResource(R.drawable.tv_volume_up) },
                        { setImageResource(R.drawable.tv_volume_up) }
                    )
            }
    }

    fun tvcTools(imageView: View) {
        (imageView as ImageView)
            .apply {
                setImageResource(R.drawable.tv_c_tools_ac)
                serviceContract
                    .action(
                        TV_C_TOOLS,
                        { setImageResource(R.drawable.tv_c_tools) },
                        { setImageResource(R.drawable.tv_c_tools) }
                    )
            }
    }

    fun tvcApps(imageView: View) {
        (imageView as ImageView)
            .apply {
                setImageResource(R.drawable.tv_c_apps_ac)
                serviceContract
                    .action(
                        TV_C_APPS,
                        { setImageResource(R.drawable.tv_c_apps) },
                        { setImageResource(R.drawable.tv_c_apps) }
                    )
            }
    }

    fun tvcEnter(imageView: View) {
        (imageView as ImageView)
            .apply {
                setImageResource(R.drawable.tv_c_enter_ac)
                serviceContract
                    .action(
                        TV_C_ENTER,
                        { setImageResource(R.drawable.tv_c_enter) },
                        { setImageResource(R.drawable.tv_c_enter) }
                    )
            }
    }

    fun tvcMenu(imageView: View) {
        (imageView as ImageView)
            .apply {
                setImageResource(R.drawable.tv_c_menu_ac)
                serviceContract
                    .action(
                        TV_C_MENU,
                        { setImageResource(R.drawable.tv_c_menu) },
                        { setImageResource(R.drawable.tv_c_menu) }
                    )
            }
    }

    fun tvcReturn(imageView: View) {
        (imageView as ImageView)
            .apply {
                setImageResource(R.drawable.tv_c_return_ac)
                serviceContract
                    .action(
                        TV_C_RETURN,
                        { setImageResource(R.drawable.tv_c_return) },
                        { setImageResource(R.drawable.tv_c_return) }
                    )
            }
    }

    fun tvcUp(imageView: View) {
        (imageView as ImageView)
            .apply {
                setImageResource(R.drawable.tv_c_up_ac)
                serviceContract
                    .action(
                        TV_C_UP,
                        { setImageResource(R.drawable.tv_c_up) },
                        { setImageResource(R.drawable.tv_c_up) }
                    )
            }
    }

    fun tvcLeft(imageView: View) {
        (imageView as ImageView)
            .apply {
                setImageResource(R.drawable.tv_c_left_ac)
                serviceContract
                    .action(
                        TV_C_LEFT,
                        { setImageResource(R.drawable.tv_c_left) },
                        { setImageResource(R.drawable.tv_c_left) }
                    )
            }
    }

    fun tvcRight(imageView: View) {
        (imageView as ImageView)
            .apply {
                setImageResource(R.drawable.tv_c_right_ac)
                serviceContract
                    .action(
                        TV_C_RIGHT,
                        { setImageResource(R.drawable.tv_c_right) },
                        { setImageResource(R.drawable.tv_c_right) }
                    )
            }
    }

    fun tvcDown(imageView: View) {
        (imageView as ImageView)
            .apply {
                setImageResource(R.drawable.tv_c_down_ac)
                serviceContract
                    .action(
                        TV_C_DOWN,
                        { setImageResource(R.drawable.tv_c_down) },
                        { setImageResource(R.drawable.tv_c_down) }
                    )
            }
    }
}
