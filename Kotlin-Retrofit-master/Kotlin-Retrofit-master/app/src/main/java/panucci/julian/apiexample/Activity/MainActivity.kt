package panucci.julian.apiexample.Activity

import android.os.Bundle
import android.util.Log
import io.reactivex.schedulers.Schedulers
import panucci.julian.apiexample.GithubApiService
import panucci.julian.apiexample.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val service = GithubApiService.Factory.create()

        button.setOnClickListener {
            service.search("julp04")
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe ({ result ->
                        Log.d("Result", "There are ${result.items.size} Java developers in Lagos")
                    }, {
                        error -> error.printStackTrace()
                    })
        }


    }
}
