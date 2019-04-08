package br.com.rms.gitconsult.di.model

import br.com.rms.gitconsult.ui.home.HomeFragment
import br.com.rms.gitconsult.ui.login.LoginFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeLoginFragment(): LoginFragment

    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment


}
