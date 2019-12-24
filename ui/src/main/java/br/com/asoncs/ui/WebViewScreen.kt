package br.com.asoncs.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import br.com.asoncs.service.WebViewContract
import kotlinx.android.synthetic.main.webview_screen.*

class WebViewScreen : AppCompatActivity() {

    val TAG = "SweetHome"
    private lateinit var url: String
    private lateinit var webViewContract: WebViewContract

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.webview_screen)

        val prefs = PreferenceManager.getDefaultSharedPreferences(application)

        webViewContract = WebViewContract.newInstance(webView)

        url = String.format(
            "http://%s",
            prefs.getString(
                "ip_route",
                application.resources.getString(R.string.pattern_url)
            )!!
        )

        btnMenu.setOnClickListener {
            startActivity(Intent(baseContext, PrefsScreen::class.java))
        }

        btnRefresh.setOnClickListener {
            loadWebViewContract(url)
        }

        loadWebViewContract(url)

    }

    @SuppressLint("CheckResult")
    private fun loadWebViewContract(url: String)
    {
        progress.visibility = View.VISIBLE
        webViewContract
            .setLocation(
                url,
                {
                    progress.visibility = View.GONE
                    it.apply {
                        putJS(
                            "document.getElementsByTagName('body')[0].innerHTML += \"<br/><br/><br/><br/><br/><br/><br/><br/><br/>\""
                        )
                    }
                },
                { throwable, webViewContract ->
                    Log.e(TAG, "Error Load", throwable)
                    progress.visibility = View.GONE
                    webViewContract.loadData(htmlError())
                }
            )
    }

    private fun htmlError(): String {
        return "" +
                "\n<html>\n" +
                "   <body style=\"color: green; background: rgb(0,0,77);\">\n" +
                "       <div style=\"position: absolute; top: 40%; left: 45%;\">\n" +
                "           <div style=\"position: relative; top: -60px; left: -60px; font-size: 5em; text-align: center; font-weight: 800;\">\n" +
                "               <svg xmlns=\"http://www.w3.org/2000/svg\" style=\"width: 120px; height: 120px\" viewBox=\"0 0 24 24\"><path fill=\"green\" d=\"M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm0 11c-.55 0-1-.45-1-1V8c0-.55.45-1 1-1s1 .45 1 1v4c0 .55-.45 1-1 1zm1 4h-2v-2h2v2z\"/></svg>\n" +
                "               <br/>404\n" +
                "           </div>\n" +
                "       </div>\n" +
                "   </body>\n" +
                "</html>"
    }
}

/*

val TAG = "SweetHome"
    var loading: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.webview_screen)

        webView.settings.javaScriptEnabled = true

        btnMenu.setOnClickListener {
            startActivity(Intent(baseContext, PrefsScreen::class.java))
        }

        btnRefresh.setOnClickListener {
            Loading().execute()
        }

        webView.webViewClient = object : WebViewClient(){

            override fun onPageFinished(view: WebView?, url: String?) {
                loading = false
                progress.visibility = View.GONE
                webView.clearHistory()
                putJS("document.getElementsByTagName('body')[0].innerHTML += \"<br/><br/><br/><br/><br/><br/><br/><br/><br/>\"")
                super.onPageFinished(view, url)
            }

            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                webView.loadData(
                    htmlError(),
                    "text/html",
                    "utf-8"
                )
            }

        }

        Loading().execute()

    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }

    private fun setLocation(url: String) {
        webView.loadUrl(url)
    }

    fun putJS(script: String) {
        val run = Runnable { webView.evaluateJavascript(script, null) }
        webView.post(run)
    }

    fun putJS(script: String, resultCallback: ValueCallback<String>) {
        val run = Runnable { webView.evaluateJavascript(script, resultCallback) }
        webView.post(run)
    }

    inner class Loading : AsyncTask<Void?, Void?, Void?>() {

        override fun onPreExecute() {
            val prefs = PreferenceManager.getDefaultSharedPreferences(application)
            setLocation(
                String.format(
                    "http://%s",
                    prefs.getString(
                        "ip_route",
                        application.resources.getString(R.string.pattern_url)
                    )!!
                )
            )
            loading = true
            progress.visibility = View.VISIBLE
            super.onPreExecute()
        }

        override fun onPostExecute(result: Void?) {
            progress.visibility = View.GONE
            if(loading){
                webView.loadData(
                    htmlError(),
                    "text/html",
                    "utf-8"
                )
            }
            super.onPostExecute(result)
        }

        override fun doInBackground(vararg p0: Void?): Void? {
            try {
                Thread.sleep(8000)
            }catch (e: Exception){}
            return null
        }
    }

 */