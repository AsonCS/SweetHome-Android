package br.com.asoncs.service

import android.webkit.ValueCallback
import android.webkit.WebView
import br.com.asoncs.service.remote.webview.BaseWebView

interface WebViewContract {

    companion object {
        fun newInstance(
            webView: WebView
        ) : BaseWebView = BaseWebView.newInstance(webView)
    }

    fun setLocation(url: String, success: (BaseWebView) -> Unit, failure: (Throwable, BaseWebView) -> Unit)

    fun putJS(script: String)

    fun putJS(script: String, resultCallback: ValueCallback<String>)

    fun loadData(data: String, mimeType: String, encoding: String)

    fun loadData(data: String)

}