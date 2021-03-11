package com.didimstory.mangulmangul

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.view.ViewGroup
import android.webkit.*
import androidx.appcompat.app.AlertDialog

import androidx.appcompat.app.AppCompatActivity
import com.didimstory.mangulmangul.Entity.apiResultItem
import com.didimstory.mangulmangul.Purchase.purchaseActivity
import com.didimstory.mangulmangul.boast.BoastChildFragment
import com.didimstory.mangulmangul.databinding.ActivityWebviewAPIBinding
import kotlinx.android.synthetic.main.activity_webview_a_p_i.*

class webviewAPI : AppCompatActivity() {
var result: String? =null
    lateinit var webView:WebView



    companion object {
        var mcontext:Activity?=null
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview_a_p_i)
initWebView()

mcontext=this
        // WebView의 setWebChromeClient 셋팅 및 Input 선택기 구현
        webView.webChromeClient = object : WebChromeClient() {


            override fun onCreateWindow(
                view: WebView?,
                isDialog: Boolean,
                isUserGesture: Boolean,
                resultMsg: Message?
            ): Boolean {

                val newWebView = WebView(this@webviewAPI)
                val webSettings = newWebView.getSettings()
                webSettings.javaScriptEnabled=true
                val dialog = Dialog(this@webviewAPI)
                dialog.setContentView(newWebView)

                val params =      dialog.window?.attributes
                params?.width = ViewGroup.LayoutParams.MATCH_PARENT
                params?.height = ViewGroup.LayoutParams.MATCH_PARENT
                dialog.window?.attributes = params as android.view.WindowManager.LayoutParams
                dialog.show()
                newWebView.webChromeClient = object:WebChromeClient() {
                    override fun onCloseWindow(window:WebView) {
                        dialog.dismiss()
                    }
                }
                newWebView.webViewClient = object:WebViewClient() {
                    override fun shouldOverrideUrlLoading(view:WebView, request:WebResourceRequest):Boolean {
                        return false
                    }
                }
                (resultMsg?.obj as WebView.WebViewTransport).setWebView(newWebView)
                resultMsg.sendToTarget()
                return true


            }

            // 자바스크립트의 alert창
            override fun onJsAlert(view: WebView, url: String, message: String, result: JsResult): Boolean {
                AlertDialog.Builder(view.context)
                    .setTitle("Alert")
                    .setMessage(message)
                    .setPositiveButton(android.R.string.ok
                    ) { dialog, which -> result.confirm() }
                    .setCancelable(false)
                    .create()
                    .show()
                return true
            }

            override fun onJsConfirm(view: WebView, url: String, message: String,
                                     result: JsResult
            ): Boolean {
                AlertDialog.Builder(view.context)
                    .setTitle("Confirm")
                    .setMessage(message)
                    .setPositiveButton("Yes"
                    ) { dialog, which -> result.confirm() }
                    .setNegativeButton("No"
                    ) { dialog, which -> result.cancel() }
                    .setCancelable(false)
                    .create()
                    .show()
                return true
            }






        }


    }




    fun initWebView() { // WebView 설정
        webView = findViewById(R.id.webview) as WebView

        webView.setWebChromeClient(WebChromeClient())
        // "TestApp" 이름이 중요합니다.
        // javascript 에서 호출되는 이름과 동일해야 합니다.
        webView.addJavascriptInterface(AndroidBridge(), "TestApp");
        webView.loadUrl("http://jiwoungftp.dothome.co.kr/serchAPI.php")
        webView.settings.javaScriptEnabled=true
        webView.settings.javaScriptCanOpenWindowsAutomatically=true
        webView.settings.setSupportMultipleWindows(true)
        webView.settings.javaScriptCanOpenWindowsAutomatically=true

    }

class AndroidBridge() {

        @JavascriptInterface
        fun setAddress(param1:String, param2:String, param3:String) {

            val handler = Handler(Looper.getMainLooper())
            handler.post(object:Runnable {
                public override fun run() {

                    Log.d("333",String.format("(%s) %s %s", param1, param2, param3))

                    val intent=Intent(mcontext,purchaseActivity::class.java)
                    intent.putExtra("result",String.format("(%s) %s %s", param1, param2, param3))
                    mcontext?.setResult(Activity.RESULT_OK,intent)

                    val intent1=Intent(mcontext,SignUpActivity::class.java)
                    intent1.putExtra("result",String.format("(%s) %s %s", param1, param2, param3))
                    mcontext?.setResult(Activity.RESULT_OK,intent)

mcontext?.finish()

                }


            })

        }

}



    }