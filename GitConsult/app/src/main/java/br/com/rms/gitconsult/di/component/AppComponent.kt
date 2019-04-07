package br.com.rms.gitconsult.di.component

import android.app.Application
import br.com.rms.gitconsult.MyApplication
import br.com.rms.gitconsult.di.model.ApiModule
import br.com.rms.gitconsult.di.model.DataBaseModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(
    modules = [
        ApiModule::class,
        DataBaseModule::class,
        AndroidSupportInjectionModule::class
    ]
)
@Singleton
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(myApplication: MyApplication)
}