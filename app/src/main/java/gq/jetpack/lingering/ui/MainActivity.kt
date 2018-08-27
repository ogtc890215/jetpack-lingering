package gq.jetpack.lingering.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import gq.jetpack.lingering.R
import gq.jetpack.lingering.databinding.ActivityMainBinding
import gq.jetpack.lingering.viewmodel.UserViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var userViewModel: UserViewModel

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.setLifecycleOwner(this)

        userViewModel = ViewModelProviders.of(this, viewModelFactory).get(UserViewModel::class.java)
        binding.viewmodel = userViewModel

        navController = Navigation.findNavController(this, R.id.nav_fragment_host)
        NavigationUI.setupWithNavController(binding.navigationView, navController)

        userViewModel.defaultLogin(this)
    }

    fun setupActionBar(view: Toolbar) {
        setSupportActionBar(view)
        NavigationUI.setupActionBarWithNavController(this, navController, binding.drawerLayout)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(binding.drawerLayout, navController)
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
