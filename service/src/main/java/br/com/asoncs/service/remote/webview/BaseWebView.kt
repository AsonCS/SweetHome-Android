package br.com.asoncs.service.remote.webview

import android.annotation.SuppressLint
import android.util.Log
import android.webkit.*
import br.com.asoncs.service.WebViewContract
import io.reactivex.Completable
import io.reactivex.CompletableEmitter
import io.reactivex.CompletableOnSubscribe
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.operators.completable.CompletableCreate
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executors

class BaseWebView private constructor(
    private val webView: WebView
) : WebViewContract {

    companion object {
        @SuppressLint("SetJavaScriptEnabled")
        fun newInstance(
            webView: WebView
        ) : BaseWebView = BaseWebView(webView).apply {
            webView.settings.javaScriptEnabled = true
        }
    }

    @SuppressLint("CheckResult")
    override fun setLocation(
        url: String,
        success: (BaseWebView) -> Unit,
        failure: (Throwable, BaseWebView) -> Unit
    ) {
        load(url).subscribe(
            { success(this) },
            { failure(it, this) }
        )
    }

    private fun load(url: String) : Completable
    {
        return CompletableCreate { emitter ->
            var loading = true
            webView.webViewClient = object : WebViewClient(){

                override fun onPageFinished(view: WebView?, url: String?) {
                    if (loading) {
                        loading = false
                        emitter.onComplete()
                    }
                    super.onPageFinished(view, url)
                }

                override fun onReceivedError(
                    view: WebView?,
                    request: WebResourceRequest?,
                    error: WebResourceError?
                ) {
                    if (loading) {
                        loading = false
                        emitter.onError(Throwable("Not Finished"))
                    } else {
                        super.onReceivedError(view, request, error)
                    }
                }
            }
            webView.loadUrl(url)
            sleep().subscribe(
                {
                    if (loading) {
                        loading = false
                        emitter.onError(Throwable("TimeOut"))
                    }
                },
                {
                    if (loading) {
                        loading = false
                        emitter.onError(it)
                    }
                }
            )
        }
    }

    private fun sleep() : Completable
    {
        return CompletableCreate {
            Thread.sleep(8000)
            it.onComplete()
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun putJS(script: String)
    {
        val run = Runnable { webView.evaluateJavascript(script, null) }
        webView.post(run)
    }

    override fun putJS(script: String, resultCallback: ValueCallback<String>)
    {
        val run = Runnable { webView.evaluateJavascript(script, resultCallback) }
        webView.post(run)
    }

    override fun loadData(
        data: String,
        mimeType: String,
        encoding: String
    ) { webView.loadData(data, mimeType, encoding) }

    override fun loadData(
        data: String
    ) { webView.loadData(data, "text/html", "utf-8") }

}