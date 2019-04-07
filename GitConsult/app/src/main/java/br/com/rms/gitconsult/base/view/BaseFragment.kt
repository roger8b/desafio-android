package br.com.rms.gitconsult.base.view

import android.content.Context
import android.graphics.Point
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import br.com.rms.gitconsult.base.mvp.BaseContract
import br.com.rms.gitconsult.base.mvp.BaseView
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class BaseFragment<V : BaseContract.View, P : BaseContract.Presenter<V>> : Fragment(), BaseView<V, P> {

    val TAG = this.javaClass.simpleName
    private lateinit var root: View

    @Inject
    lateinit var presenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }

    fun getWindowSize(): Point {
        val display = activity?.windowManager?.defaultDisplay
        val size = Point()
        display?.getSize(size)
        return size
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(getLayoutId(), container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //presenter = newPresenterInstance()
        presenter.attach(getViewInstance())
        initViews()
    }

    fun showToastShort(textRes: Int) {
        showToast(textRes, Toast.LENGTH_SHORT)
    }

    fun showToastLong(textRes: Int) {
        showToast(textRes, Toast.LENGTH_LONG)
    }

    fun showToast(textRes: Int, lenght: Int) {
        Toast.makeText(context, textRes, lenght).show()
    }


}