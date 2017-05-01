package com.sdoward.preference.setup

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import com.google.firebase.database.FirebaseDatabase

import com.sdoward.preference.R
import com.sdoward.preference.data.RepositoryProvider
import kotlinx.android.synthetic.main.setup_activity.*

class SetupActivity : AppCompatActivity(), SetUpContract {

    companion object {

        fun start(context: Context) {
            context.startActivity(Intent(context, SetupActivity::class.java))
        }
    }

    private val presenter = SetupPresenter(RepositoryProvider.get(), this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.setup_activity)
        setSupportActionBar(toolbar)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        recyclerView.layoutManager = LinearLayoutManager(this@SetupActivity)
        presenter.start()
    }

    override fun onDestroy() {
        presenter.stop()
        super.onDestroy()
    }

    override fun render(setupViewState: SetupViewState) {
        recyclerView.adapter = UserAdapter(LayoutInflater.from(this@SetupActivity), setupViewState.users)
        recyclerView.visibility = if (setupViewState.loading) GONE else VISIBLE
        progressBar.visibility = if (setupViewState.loading) VISIBLE else GONE
    }

}
