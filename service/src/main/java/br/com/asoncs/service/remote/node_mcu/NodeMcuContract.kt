package br.com.asoncs.service.remote.node_mcu

import br.com.asoncs.service.ServiceActions
import br.com.asoncs.service.ServiceActions.*
import io.reactivex.Completable
import io.reactivex.CompletableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

interface NodeMcuContract {

    fun doAction(action: ServiceActions) : Completable

    companion object {
        fun newInstance(endPoint: String) : NodeMcuContract {
            val nodeMcuApi = NodeMcuApi.newInstance(endPoint)
            return object :
                NodeMcuContract {
                override fun doAction(action: ServiceActions): Completable {
                    return when ( action ) {
                        LIGHT_TOGGLE -> nodeMcuApi.lightToggle().compose(applyObservableAsync<Completable>())
                        TV_C_TOGGLE -> nodeMcuApi.tvcToggle().compose(applyObservableAsync<Completable>())
                        TV_CHANNEL_DOWN -> nodeMcuApi.tvChannelDown().compose(applyObservableAsync<Completable>())
                        TV_CHANNEL_UP -> nodeMcuApi.tvChannelUp().compose(applyObservableAsync<Completable>())
                        TV_VOLUME_DOWN -> nodeMcuApi.tvVolumeDown().compose(applyObservableAsync<Completable>())
                        TV_VOLUME_UP -> nodeMcuApi.tvVolumeUp().compose(applyObservableAsync<Completable>())
                        TV_C_TOOLS -> nodeMcuApi.tvcTools().compose(applyObservableAsync<Completable>())
                        TV_C_APPS -> nodeMcuApi.tvcApps().compose(applyObservableAsync<Completable>())
                        TV_C_ENTER -> nodeMcuApi.tvcEnter().compose(applyObservableAsync<Completable>())
                        TV_C_MENU -> nodeMcuApi.tvcMenu().compose(applyObservableAsync<Completable>())
                        TV_C_RETURN -> nodeMcuApi.tvcReturn().compose(applyObservableAsync<Completable>())
                        TV_C_UP -> nodeMcuApi.tvcUp().compose(applyObservableAsync<Completable>())
                        TV_C_LEFT -> nodeMcuApi.tvcLeft().compose(applyObservableAsync<Completable>())
                        TV_C_RIGHT -> nodeMcuApi.tvcRight().compose(applyObservableAsync<Completable>())
                        TV_C_DOWN -> nodeMcuApi.tvcDown().compose(applyObservableAsync<Completable>())
                    }
                }
            }
        }

        fun <T> applyObservableAsync(): CompletableTransformer {
            return CompletableTransformer { completable ->
                completable
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
            }
        }
    }
}