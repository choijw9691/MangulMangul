package com.didimstory.mangulmangul.MyService

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.didimstory.mangulmangul.MainActivity
import com.didimstory.mangulmangul.R
import kotlinx.android.synthetic.main.activity_question_detail.*

class QuestionDetailActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_detail)


      statue.setText(intent.getStringExtra("statue"))
        title1.setText(intent.getStringExtra("title"))
        date.setText(intent.getStringExtra("date"))
        question.setText(intent.getStringExtra("question"))
        answer.setText(intent.getStringExtra("answer"))



registerForContextMenu(removeBtn)

removeBtn.setOnClickListener(View.OnClickListener {
//it.showContextMenu(it.x,it.y)
    it.showContextMenu(it.x+it.getWidth()/2,it.y+it.getHeight()/2)
})

        backbtn.setOnClickListener(View.OnClickListener {
onBackPressed()


        })

    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        var inflater:MenuInflater=menuInflater

        inflater.inflate(R.menu.context_menu,menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.remove -> Log.d("삭제완료","삭제완료")

        }
        return super.onContextItemSelected(item);
    }

}