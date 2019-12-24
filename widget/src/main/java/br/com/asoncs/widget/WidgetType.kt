package br.com.asoncs.widget

import br.com.asoncs.service.ServiceActions

data class WidgetType(
    val layout: Int,
    val action: ServiceActions,
    val imageView: Int,
    val image: Int,
    val imageActivate: Int
)

fun getWidgetType( action: ServiceActions ) : WidgetType {

    when ( action ) {
        ServiceActions.LIGHT_TOGGLE -> {
            return WidgetType(
                layout = R.layout.light_toggle,
                action = action,
                imageView = R.id.light_toggle,
                image = R.drawable.light_toggle,
                imageActivate = R.drawable.light_toggle_ac
            )
        }
        ServiceActions.TV_C_TOGGLE -> {
            return WidgetType(
                layout = R.layout.tv_c_toggle,
                action = action,
                imageView = R.id.tv_c_toggle,
                image = R.drawable.tv_c_toggle,
                imageActivate = R.drawable.tv_c_toggle_ac
            )
        }
        ServiceActions.TV_CHANNEL_DOWN -> {
            return WidgetType(
                layout = R.layout.tv_channel_down,
                action = action,
                imageView = R.id.tv_channel_down,
                image = R.drawable.tv_channel_down,
                imageActivate = R.drawable.tv_channel_down_ac
            )
        }
        ServiceActions.TV_CHANNEL_UP -> {
            return WidgetType(
                layout = R.layout.tv_channel_up,
                action = action,
                imageView = R.id.tv_channel_up,
                image = R.drawable.tv_channel_up,
                imageActivate = R.drawable.tv_channel_up_ac
            )
        }
        ServiceActions.TV_VOLUME_DOWN -> {
            return WidgetType(
                layout = R.layout.tv_volume_down,
                action = action,
                imageView = R.id.tv_volume_down,
                image = R.drawable.tv_volume_down,
                imageActivate = R.drawable.tv_volume_down_ac
            )
        }
        ServiceActions.TV_VOLUME_UP -> {
            return WidgetType(
                layout = R.layout.tv_volume_up,
                action = action,
                imageView = R.id.tv_volume_up,
                image = R.drawable.tv_volume_up,
                imageActivate = R.drawable.tv_volume_up_ac
            )
        }
        ServiceActions.TV_C_TOOLS -> {
            return WidgetType(
                layout = R.layout.tv_c_tools,
                action = action,
                imageView = R.id.tv_c_tools,
                image = R.drawable.tv_c_tools,
                imageActivate = R.drawable.tv_c_tools_ac
            )
        }
        ServiceActions.TV_C_APPS -> {
            return WidgetType(
                layout = R.layout.tv_c_apps,
                action = action,
                imageView = R.id.tv_c_apps,
                image = R.drawable.tv_c_apps,
                imageActivate = R.drawable.tv_c_apps_ac
            )
        }
        ServiceActions.TV_C_ENTER -> {
            return WidgetType(
                layout = R.layout.tv_c_enter,
                action = action,
                imageView = R.id.tv_c_enter,
                image = R.drawable.tv_c_enter,
                imageActivate = R.drawable.tv_c_enter_ac
            )
        }
        ServiceActions.TV_C_MENU -> {
            return WidgetType(
                layout = R.layout.tv_c_menu,
                action = action,
                imageView = R.id.tv_c_menu,
                image = R.drawable.tv_c_menu,
                imageActivate = R.drawable.tv_c_menu_ac
            )
        }
        ServiceActions.TV_C_RETURN -> {
            return WidgetType(
                layout = R.layout.tv_c_return,
                action = action,
                imageView = R.id.tv_c_return,
                image = R.drawable.tv_c_return,
                imageActivate = R.drawable.tv_c_return_ac
            )
        }
        ServiceActions.TV_C_UP -> {
            return WidgetType(
                layout = R.layout.tv_c_up,
                action = action,
                imageView = R.id.tv_c_up,
                image = R.drawable.tv_c_up,
                imageActivate = R.drawable.tv_c_up_ac
            )
        }
        ServiceActions.TV_C_LEFT -> {
            return WidgetType(
                layout = R.layout.tv_c_left,
                action = action,
                imageView = R.id.tv_c_left,
                image = R.drawable.tv_c_left,
                imageActivate = R.drawable.tv_c_left_ac
            )
        }
        ServiceActions.TV_C_RIGHT -> {
            return WidgetType(
                layout = R.layout.tv_c_right,
                action = action,
                imageView = R.id.tv_c_right,
                image = R.drawable.tv_c_right,
                imageActivate = R.drawable.tv_c_right_ac
            )
        }
        ServiceActions.TV_C_DOWN -> {
            return WidgetType(
                layout = R.layout.tv_c_down,
                action = action,
                imageView = R.id.tv_c_down,
                image = R.drawable.tv_c_down,
                imageActivate = R.drawable.tv_c_down_ac
            )
        }
    }

}