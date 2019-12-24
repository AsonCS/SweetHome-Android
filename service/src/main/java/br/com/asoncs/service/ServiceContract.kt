package br.com.asoncs.service

import android.annotation.SuppressLint
import br.com.asoncs.service.remote.node_mcu.NodeMcuContract

interface ServiceContract {

    fun action(action: ServiceActions, success: () -> Unit, failure: (throwable: Throwable) -> Unit)

    companion object {

        fun newInstance(endPoint: String): ServiceContract {
            val nodeMcuContract = NodeMcuContract.newInstance(endPoint)
            return object : ServiceContract {
                @SuppressLint("CheckResult")
                override fun action(
                    action: ServiceActions,
                    success: () -> Unit,
                    failure: (throwable: Throwable) -> Unit
                ) {
                    nodeMcuContract
                        .doAction(action)
                        .retry(2)
                        .subscribe(success, failure)
                }
            }
        }

    }

}