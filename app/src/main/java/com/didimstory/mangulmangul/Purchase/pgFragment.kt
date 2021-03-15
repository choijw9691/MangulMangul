package com.didimstory.mangulmangul.Purchase

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.didimstory.mangul.Client
import com.didimstory.mangulmangul.MainActivity
import com.didimstory.mangulmangul.PreferenceManager
import com.didimstory.mangulmangul.R
import kotlinx.android.synthetic.main.activity_purchase.*
import kr.co.bootpay.Bootpay
import kr.co.bootpay.BootpayAnalytics
import kr.co.bootpay.enums.Method
import kr.co.bootpay.enums.PG
import kr.co.bootpay.enums.UX
import kr.co.bootpay.model.BootExtra
import kr.co.bootpay.model.BootUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass.
 * Use the [pgFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class pgFragment : FragmentActivity() {
var resultmsg:String?=null
    val application_id = "604b709cd8c1bd002bf4c16e"
var titleresult="선택한 상품이 없음"
    var priceresult=1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_pg)

        BootpayAnalytics.init(this, application_id)
        Log.d("done",intent.getLongArrayExtra("artKitList").toString())
        titleresult= intent.getStringExtra("titleresult").toString()
        priceresult=   intent.getIntExtra("priceresult",1)
        Log.d("pgcheck", titleresult)
        Log.d("pgcheck", priceresult.toString())
        goBootpayRequest()

    }



    fun goBootpayRequest() {
        val bootUser = BootUser().setPhone("010-2662-9691")
        val bootExtra = BootExtra().setQuotas(intArrayOf(0, 2, 3))

        val stuck = 1 //재고 있음

        Bootpay.init(this)
            .setApplicationId(application_id) // 해당 프로젝트(안드로이드)의 application id 값
            .setContext(this)
            .setBootUser(bootUser)
            .setBootExtra(bootExtra)
            .setUX(UX.PG_DIALOG)
            .setPG(PG.KCP)
            .setMethod(Method.CARD)
//                .setUserPhone("010-1234-5678") // 구매자 전화번호
            .setName(titleresult) // 결제할 상품명

            .setOrderId("1234") // 결제 고유번호expire_month
            .setPrice(priceresult) // 결제할 금액
            .addItem("마우's 스", 1, "ITEM_CODE_MOUSE", 100) // 주문정보에 담길 상품정보, 통계를 위해 사용
            .addItem("키보드", 1, "ITEM_CODE_KEYBOARD", 200, "패션", "여성상의", "블라우스") // 주문정보에 담길 상품정보, 통계를 위해 사용
            .onConfirm { message ->
                if (0 < stuck) Bootpay.confirm(message); // 재고가 있을 경우.
                else Bootpay.removePaymentWindow(); // 재고가 없어 중간에 결제창을 닫고 싶을 경우
                Log.d("confirm", message);
            }
            .onDone { message ->

resultmsg="결제를 성공하였습니다."
                intent.getStringExtra("name")
                intent.getStringExtra("addressText")
                intent.getStringExtra("addr2")
                intent.getStringExtra("email")
                intent.getLongArrayExtra("artKitList")
                intent.getLongArrayExtra("countList")

                Log.d("purchasesend", PreferenceManager.getLong(applicationContext,"PrefIDIndex").toString()+"///"+ intent.getStringExtra("addressText").toString()+"/////"+intent.getStringExtra("addr2").toString()+"/////"+intent.getIntegerArrayListExtra("artKitList")?.toList().toString()+""+ intent.getIntegerArrayListExtra("countList")?.toString());
                Client.retrofitService.boastBuy(
                    PreferenceManager.getLong(applicationContext,"PrefIDIndex"), intent.getStringExtra("addressText").toString(),
                    intent.getStringExtra("addr2").toString(),intent.getIntegerArrayListExtra("artKitList"),    intent.getIntegerArrayListExtra("countList"))
                    .enqueue(object :
                        Callback<Void> {
                        override fun onFailure(call: Call<Void>, t: Throwable) {

                        }

                        override fun onResponse(call: Call<Void>, response: Response<Void>) {
                            Log.d("구매성공","구매성공")
                        }


                    })



            }
            .onReady { message ->
                Log.d("ready", message)
            }
            .onCancel { message ->
                Log.d("cancel", message)
                resultmsg="결제를 실패하였습니다."
            }
            .onError{ message ->
                Log.d("error", message)
            }
            .onClose { message ->
                Log.d("close", "closeclosecloseclose")
                Toast.makeText(this, resultmsg, Toast.LENGTH_SHORT).show()
                intent=Intent(this,MainActivity::class.java)
                intent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                intent?.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

                startActivity(intent)
                finish()
            }
            .request();
    }
}